# A Demo Project

# Start Authorization Server (Keycloak)
- Option 1: Run authorization-service embedded in project with Environment variables:
  - MYSQL_DATASOURCE_URL: mysql
  - MYSQL_DATASOURCE_USERNAME: mysql username
  - MYSQL_DATASOURCE_PASSWORD: mysql password
  - Open browner with url to config auth: [admin page](http://localhost:8095/auth/)
    - username: admin
    - password: admin
- Option 2: Install and Setup follow [document](https://www.keycloak.org/)

# Start Account Service
- Go to common-module run command: `mvn clean install`
- Go to spec-module run command: `mvn clean install`
- Environment variables:
  - MYSQL_DATASOURCE_URL: mysql url
  - MYSQL_DATASOURCE_USERNAME: mysql username
  - MYSQL_DATASOURCE_PASSWORD: mysql password
  - KEYCLOAK_URL: keycloak url `with authorization-service: http://localhost:8095/auth`
  - KEYCLOAK_REALM: keycloak realm id `with authorization-service: hello-spring`
  - KEYCLOAK_USERNAME: not use in this version `none`
  - KEYCLOAK_PASSWORD: not use in this version `none`
  - KEYCLOAK_CLIENT_ID: keycloak `with authorization-service: access-controll`
  - KEYCLOAK_CLIENT_SECRET: keycloak `with authorization-service: ee11a3a6-4a85-444d-a09f-402c23c508e8`
  - KEYCLOAK_ID_WEB_ADMIN: keycloak id of client web admin not client-id `with authorization-service: 204b0be4-ec53-4621-a86f-9e9585c29900`
- Go to account-service run command: `mvn clean compile`
  - Add folder `account-service/target/generated-sources/api/src/main/java` to Generated Source Root
- Run account-service
# Start Gateway Service
- Environment variables:
  - AUTH_ISSUER_URI `with authorization-service: http://localhost:8095/auth/realms/hello-spring`
  - AUTH_JWK_URI `with authorization-service: http://localhost:8095/auth/realms/hello-spring/protocol/openid-connect/certs`
  - AUTH_ALGORITHM `with authorization-service: RS256`
- Run gateway-service
