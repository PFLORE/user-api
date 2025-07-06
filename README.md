# API de Registro de Usuarios

Esta es una API RESTful desarrollada con Java 11 y Spring Boot que permite registrar usuarios y almacenar sus datos junto con un token JWT.

## Características

- Registro de usuario con nombre, correo, contraseña y teléfonos.
- Validación de email y contraseña mediante expresiones regulares.
- Persistencia en base de datos en memoria H2.
- Generación y persistencia de token JWT.
- Validaciones con `@Valid` y manejo de errores estandarizado.
- Swagger para documentación interactiva.
- Pruebas de integración con Spring Boot y MockMvc.

---

## Tecnologías utilizadas

- Java 11
- Spring Boot
- Spring Data JPA
- H2 Database
- JWT (JJWT)
- Maven
- Swagger (Springdoc OpenAPI)
- JUnit 5 + Mockito

---

## Requisitos previos

- Java 11+
- Maven 3.6+

---

## Instalación y ejecución

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/PFLORE/user-api.git
   cd user-api
2. **Compilar y ejecutar la aplicación:**
    ```bash
    mvn clean install
    mvn spring-boot:run
3. **Acceder a la API:**
   - URL base: http://localhost:9090/api/users
4. **Consola de base de datos H2 (opcional):**
   - http://localhost:9090/h2-console
   - JDBC URL: jdbc:h2:mem:userdb
   - Usuario: sa
   - Contraseña: (dejar en blanco)

---

## Uso de la API
### Registrar un nuevo usuario

**Endpoint:** `POST /api/users`

---

### Cuerpo de la solicitud:

```json
{
  "name": "Juan Rodriguez",
  "email": "juan@rodriguez.org",
  "password": "Hunter123",
  "phones": [
    {
      "number": "1234567",
      "citycode": "1",
      "contrycode": "57"
    }
  ]
}
```
### Respuesta esperada:
```json
{
  "id": "07467c7c-9efc-4a3c-89ce-b1739efa964b",
  "created": "2025-07-06T17:30:14.8954665",
  "modified": "2025-07-06T17:30:14.8954665",
  "lastLogin": "2025-07-06T17:30:14.8954665",
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFuQHJvZHJpZ3Vlei5vcmciLCJpYXQiOjE3NTE4NDEwMTQsImV4cCI6MTc1MTkyNzQxNH0.H-NkHzP18qAG28_jIZfG_-iEqVyVgGBirOkOcFQiDjP1JlpPkxpHenkLYO9ITBxhl8Y_YrqH3TaH2uFWE-qYtQ",
  "active": true
}
```
### Ejemplo de error (correo ya registrado):
```json
{
  "mensaje": "El correo ya registrado"
}
```

---

## Documentación Swagger
Puedes probar todos los endpoints usando Swagger UI:

📄 http://localhost:9090/swagger-ui.html

---

## Pruebas
Ejecutar pruebas con:
```bash
mvn test