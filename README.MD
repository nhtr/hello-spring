# A Demo Project

# Start
- Go to common-module run command: `mvn clean install`
- Go to spec-module run command: `mvn clean install`
- Go to account-service run command: `mvn clean compile`
  - Add folder account-service/src/gen/java to Generated Source Root
- Run account-service
- Test: Run GET request to [feature service](http://localhost:9091/account-service/api/feature)

# Integration with [client](https://github.com/nhtr/hello-spring-client/tree/demo-open-api)
- Copy files in folder account-service/target/generated-sources/angular to [client data generated](https://github.com/nhtr/hello-spring-client/tree/demo-open-api/libs/data-generated/src/lib/account)
- Run client from [the repository](https://github.com/nhtr/hello-spring-client/tree/demo-open-api) with command to view result: `npm start`