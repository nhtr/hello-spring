# A Demo Project

# Start Authorization Server (Keycloak)
- Option 1: Run authorization-service embedded in project with Environment variables:
  - SPRING_DATASOURCE_DRIVER_CLASS_NAME: mysql
  - SPRING_DATASOURCE_URL: mysql
  - SPRING_DATASOURCE_USERNAME: mysql username
  - SPRING_DATASOURCE_PASSWORD: mysql password
- Option 2: Install and Setup follow [document](https://www.keycloak.org/)

# Start Account Service
- Go to common-module run command: `mvn clean install`
- Go to spec-module run command: `mvn clean install`
- Environment variables:
  - SPRING_DATASOURCE_DRIVER_CLASS_NAME: mysql
  - SPRING_DATASOURCE_URL: mysql
  - SPRING_DATASOURCE_USERNAME: mysql username
  - SPRING_DATASOURCE_PASSWORD: mysql password
  - KEYCLOAK_URL: keycloak url
  - KEYCLOAK_REALM: keycloak realm id
  - KEYCLOAK_USERNAME: not use in this version
  - KEYCLOAK_PASSWORD: not use in this version
  - KEYCLOAK_CLIENT_ID: keycloak
  - KEYCLOAK_CLIENT_SECRET: keycloak
  - KEYCLOAK_ID_WEB_ADMIN: keycloak id of client web admin not client-id
- Go to account-service run command: `mvn clean compile`
  - Add folder `account-service/target/generated-sources/api/src/main/java` to Generated Source Root
- Run account-service
# Start Gateway Service
- Environment variables:
  - AUTH_ISSUER_URI
  - AUTH_JWK_URI
  - AUTH_ALGORITHM
- Run gateway-service
