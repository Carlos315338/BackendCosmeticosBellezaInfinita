package com.sena.backendCosmeticosBellezaInfinita.services;

public interface CognitoPasswordService {

    public void cambiarClaveEnPrimerLogin(String username, String tempPassword, String nuevaPassword);
    public void cambiarContrasena(String accessToken, String contrasenaActual, String contrasenaNueva);
    public void cerrarSesionesGlobales(String username);
}
