package com.nhtr.authorizationservice;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

@ConfigurationProperties(prefix = "keycloak.custom")
public class KeycloakCustomProperties {

    Server server = new Server();

    AdminUser adminUser = new AdminUser();

    Infinispan infinispan = new Infinispan();
    
    String importLocation = "hello-spring-realm.json";
    
    boolean createAdminUser = true;
    boolean createRealm = false;

    public Server getServer() {
        return this.server;
    }

    public AdminUser getAdminUser() {
        return this.adminUser;
    }

    public Infinispan getInfinispan() {
        return this.infinispan;
    }

    public String getImportLocation() {
        return this.importLocation;
    }

    public boolean isCreateAdminUser() {
        return this.createAdminUser;
    }

    public boolean isCreateRealm() {
        return this.createRealm;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public void setAdminUser(AdminUser adminUser) {
        this.adminUser = adminUser;
    }

    public void setInfinispan(Infinispan infinispan) {
        this.infinispan = infinispan;
    }

    public void setImportLocation(String importLocation) {
        this.importLocation = importLocation;
    }

    public void setCreateAdminUser(boolean createAdminUser) {
        this.createAdminUser = createAdminUser;
    }

    public void setCreateRealm(boolean createRealm) {
        this.createRealm = createRealm;
    }

    public class Server {

        String contextPath;

        public String getContextPath() {
            return this.contextPath;
        }

        public void setContextPath(String contextPath) {
            this.contextPath = contextPath;
        }
    }

    public class Infinispan {

        Resource configLocation;

        public Resource getConfigLocation() {
            return this.configLocation;
        }

        public void setConfigLocation(Resource configLocation) {
            this.configLocation = configLocation;
        }
    }

    public class AdminUser {

        String username;

        String password;

        public String getUsername() {
            return this.username;
        }

        public String getPassword() {
            return this.password;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
