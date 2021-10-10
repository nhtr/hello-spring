package com.nhtr.authorizationservice.support;

import org.keycloak.Config;
import org.keycloak.cluster.ManagedCacheManagerProvider;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class InfinispanCacheManagerProvider implements ManagedCacheManagerProvider {

    public static final String JNDI_NAME = "spring/infinispan/cacheManager";

    @Override
    @SuppressWarnings("unchecked")
    public <C> C getCacheManager(Config.Scope config) {
        try {
            return (C) new InitialContext().lookup(JNDI_NAME);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}
