package com.sena.backendCosmeticosBellezaInfinita.services.impl;

import com.sena.backendCosmeticosBellezaInfinita.services.CognitoPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.*;

import java.util.HashMap;
import java.util.Map;

@Service
public class CognitoPasswordServiceImpl implements CognitoPasswordService {

    @Autowired
    private CognitoIdentityProviderClient cognitoClient;

    @Value("userPoolId")
    private String userPoolId;
    @Value("clientId")
    private String clientId;

    public void cambiarClaveEnPrimerLogin(String username, String tempPassword, String nuevaPassword) {
        try {

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

            cognitoClient.adminRespondToAuthChallenge(
                    AdminRespondToAuthChallengeRequest.builder()
                            .challengeName(ChallengeNameType.NEW_PASSWORD_REQUIRED)
                            .userPoolId(userPoolId)
                            .clientId(clientId)
                            .challengeResponses(parameters)
                            .session(authResponse.session())
                            .build()
            );

        } catch (CognitoIdentityProviderException e) {
            throw new RuntimeException("Error de Cognito: " + e.awsErrorDetails().errorMessage(), e);
        }
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
}

