package com.sena.userCosmeticosBellezaInfinita.services.impl;

import com.sena.userCosmeticosBellezaInfinita.services.CognitoPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.*;

import java.util.HashMap;

@Service
public class CognitoPasswordServiceImpl implements CognitoPasswordService {

    @Autowired
    private CognitoIdentityProviderClient cognitoClient;

    @Value("${userPoolId}")
    private String userPoolId;

    @Value("${clientId}")
    private String clientId;

    public void cambiarClaveEnPrimerLogin(String username, String tempPassword, String nuevaPassword) {

        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("USERNAME", username);
        parameters.put("PASSWORD", tempPassword);

        AdminInitiateAuthResponse authResponse = cognitoClient.adminInitiateAuth(
            AdminInitiateAuthRequest.builder()
                .authFlow(AuthFlowType.ADMIN_NO_SRP_AUTH)
                .userPoolId(userPoolId)
                .clientId(clientId)
                .authParameters(parameters)
                .build()
        );

        HashMap<String, String> challengeResponses = new HashMap<>();
        challengeResponses.put("USERNAME", username);
        challengeResponses.put("NEW_PASSWORD", nuevaPassword);

        cognitoClient.adminRespondToAuthChallenge(
            AdminRespondToAuthChallengeRequest.builder()
                .challengeName(ChallengeNameType.NEW_PASSWORD_REQUIRED)
                .userPoolId(userPoolId)
                .clientId(clientId)
                .challengeResponses(challengeResponses)
                .session(authResponse.session())
                .build()
        );
    }

    public void cambiarContrasena(String accessToken, String contrasenaActual, String contrasenaNueva) {
        try {
            ChangePasswordRequest request = ChangePasswordRequest.builder()
                    .accessToken(accessToken)
                    .previousPassword(contrasenaActual)
                    .proposedPassword(contrasenaNueva)
                    .build();

            cognitoClient.changePassword(request);
        } catch (CognitoIdentityProviderException e) {
            throw new RuntimeException("No se pudo cambiar la contraseña: " + e.awsErrorDetails().errorMessage(), e);
        }
    }

    public void cerrarSesionesGlobales(String username) {
        cognitoClient.adminUserGlobalSignOut(AdminUserGlobalSignOutRequest.builder()
                .userPoolId(userPoolId)
                .username(username)
                .build());
    }

    public void cambiarClaveComoAdmin(String username, String nuevaContrasena) {
        try {
            AdminSetUserPasswordRequest request = AdminSetUserPasswordRequest.builder()
                    .username(username)
                    .userPoolId(userPoolId)
                    .password(nuevaContrasena)
                    .permanent(false)
                    .build();

            AdminSetUserPasswordResponse response = cognitoClient.adminSetUserPassword(request);
            System.out.println("Contraseña modificada exitosamente");

        } catch (CognitoIdentityProviderException e) {
            System.err.println("Error al cambiar contraseña como admin: " + e.awsErrorDetails().errorMessage());
            throw e;
        }
    }

    public void crearUsuario(String idUser, String email, String name, String phoneNumber) {

        AdminCreateUserRequest.Builder requestBuilder = AdminCreateUserRequest.builder()
            .userPoolId(userPoolId)
            .username(idUser)
            .userAttributes(
                AttributeType.builder().name("email").value(email).build(),
                AttributeType.builder().name("email_verified").value("true").build(),

                AttributeType.builder().name("phone_number").value(phoneNumber).build(),
                AttributeType.builder().name("phone_number_verified").value("true").build(),

                AttributeType.builder().name("custom:idUser").value(idUser).build(),
                AttributeType.builder().name("name").value(name).build()
            )
            .desiredDeliveryMediums(DeliveryMediumType.EMAIL)
            .messageAction(MessageActionType.RESEND);

        AdminCreateUserResponse response = cognitoClient.adminCreateUser(requestBuilder.build());

        System.out.println("Usuario creado: " + response.user().username());
    }
}

