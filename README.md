
# Backend User - Cosméticos Belleza Infinita

Este backend está desarrollado en **Spring Boot 3.4.4**, utilizando **Java 21**, **Spring WebFlux**, **Spring Cloud Config**, **MapStruct**, **Lombok** y despliegue en **AWS ECS** con integración a **Amazon Cognito** para autenticación y autorización.

## Estructura del Proyecto

```
backendCosmeticosBellezaInfinita/
├── config/                  # Configuración general del backend y OpenAPI
├── controller/              # Controladores REST
├── dto/                     # Objetos de transferencia de datos
├── entities/                # Entidades JPA
├── mappers/                 # MapStruct mappers para DTOs y entidades
├── repository/              # Repositorios JPA
├── services/                # Lógica de negocio y servicios
├── exceptions/              # Manejo de excepciones personalizadas
├── application.yaml         # Configuración principal con Spring Config
```

## Funcionalidades Implementadas

### 1. Autenticación con Amazon Cognito
- Integración vía `CognitoIdentityProviderClient` usando `DefaultCredentialsProvider` (IAM).
- Cambiar contraseña en el primer inicio de sesión (`NEW_PASSWORD_REQUIRED`).
- Validación y manejo de sesiones y atributos.
- Configuración declarada como `@Bean` reutilizable.

### 2. Arquitectura Reactive (Spring WebFlux)
- Todos los endpoints usan programación reactiva (`Mono`, `Flux`).
- Integración con base de datos no bloqueante.

### 3. Documentación Swagger / OpenAPI
- Configuración con `springdoc-openapi-webflux-ui`.
- Exposición automática del endpoint: `/v3/api-docs` y documentación Swagger UI.
- Uso del bean `OpenAPI` y `GroupedOpenApi`.

### 4. Seguridad y Buenas Prácticas
- Exclusión de datos sensibles desde el frontend.
- Manejo de validaciones y errores controlados.
- Separación de responsabilidades en servicios y controladores.

### 5. Despliegue en AWS ECS (Fargate)
- Imagen construida con `amazoncorretto:21-alpine` y Podman.
- Repositorio ECR por microservicio.
- Uso de API Gateway como punto único de acceso.

### 6. Integración con AWS Secrets Manager (opcional)
- Posibilidad de cargar credenciales de base de datos de forma segura.

### 7. Control de acceso basado en roles y módulos
- Usuarios autenticados cargan su lista de roles y módulos autorizados.
- Integración con el frontend para restringir rutas por módulo.

### 8. Manejo de Entidades y DTOs
- Uso de MapStruct para transformación limpia y eficiente.
- Separación clara entre capa de persistencia y API.

## Endpoints Clave

- `/api/auth/login`
- `/api/usuario/confirmacion-clave`
- `/api/productos`
- `/api/ventas`
- `/api/clientes`

## Despliegue en AWS ECR

### 1. Crear repositorio en ECR (si no existe)
```bash
aws ecr create-repository --repository-name backend-cosmeticos --region us-east-1
```

### 2. Iniciar sesión en ECR
```bash
aws ecr get-login-password --region us-east-1 | podman login --username AWS --password-stdin <aws_account_id>.dkr.ecr.us-east-1.amazonaws.com
```

### 3. Construir la imagen
```bash
podman build -t backend-cosmeticos .
```

### 4. Etiquetar la imagen
```bash
podman tag backend-cosmeticos:latest <aws_account_id>.dkr.ecr.us-east-1.amazonaws.com/backend-cosmeticos:latest
```

### 5. Subir imagen al repositorio
```bash
podman push <aws_account_id>.dkr.ecr.us-east-1.amazonaws.com/backend-cosmeticos:latest
```

> Reemplaza `<aws_account_id>` por tu ID de cuenta de AWS.

---
