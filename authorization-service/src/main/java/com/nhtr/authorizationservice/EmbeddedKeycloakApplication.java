package com.nhtr.authorizationservice;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.NoSuchElementException;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

import org.keycloak.Config;
import org.keycloak.common.util.Resteasy;
import org.keycloak.models.KeycloakSession;
import org.keycloak.representations.idm.RealmRepresentation;
import org.keycloak.services.managers.ApplianceBootstrap;
import org.keycloak.services.managers.RealmManager;
import org.keycloak.services.resources.KeycloakApplication;
import org.keycloak.services.util.JsonConfigProviderFactory;
import org.keycloak.util.JsonSerialization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.nhtr.authorizationservice.KeycloakCustomProperties.AdminUser;

public class EmbeddedKeycloakApplication extends KeycloakApplication {

	private static final Logger LOG = LoggerFactory.getLogger(EmbeddedKeycloakApplication.class);

	static KeycloakCustomProperties customProperties;

	public EmbeddedKeycloakApplication(@Context ServletContext context) {

		Resteasy.pushContext(ServletContext.class, augmentToRedirectContextPath(context));
		if (customProperties.isCreateAdminUser()) {
			tryCreateMasterRealmAdminUser();
		}
		if (customProperties.isCreateRealm()) {
			importLssoRealm();
		}
	}

	protected void loadConfig() {
		// Config.init(configProvider);
		JsonConfigProviderFactory factory = new RegularJsonConfigProviderFactory();
        Config.init(factory.create()
            .orElseThrow(() -> new NoSuchElementException("No value present")));
	}

	private void tryCreateMasterRealmAdminUser() {

		KeycloakSession session = getSessionFactory().create();

		ApplianceBootstrap applianceBootstrap = new ApplianceBootstrap(session);

		AdminUser admin = customProperties.getAdminUser();

		try {
			session.getTransactionManager().begin();
			applianceBootstrap.createMasterRealmUser(admin.getUsername(), admin.getPassword());
			session.getTransactionManager().commit();
		} catch (Exception ex) {
			LOG.warn("Couldn't create keycloak master admin user: {}", ex.getMessage());
			session.getTransactionManager().rollback();
		}

		session.close();
	}

	private void importLssoRealm() {

		KeycloakSession session = getSessionFactory().create();

		try {
			session.getTransactionManager().begin();

			RealmManager manager = new RealmManager(session);
			Resource lessonRealmImportFile = new ClassPathResource(customProperties.getImportLocation());

			manager.importRealm(
					JsonSerialization.readValue(lessonRealmImportFile.getInputStream(), RealmRepresentation.class));

			session.getTransactionManager().commit();
		} catch (Exception ex) {
			LOG.warn("Failed to import Realm json file: {}", ex.getMessage());
			session.getTransactionManager().rollback();
		}

		session.close();

		LOG.info("Keycloak realm configuration import finished.");
	}

	private static ServletContext augmentToRedirectContextPath(ServletContext servletContext) {

		ClassLoader classLoader = servletContext.getClassLoader();
		Class<?>[] interfaces = { ServletContext.class };
		KeycloakCustomProperties.Server server = customProperties.getServer();

		InvocationHandler invocationHandler = (proxy, method, args) -> {

			if ("getContextPath".equals(method.getName())) {
				return server.getContextPath();
			}

			if ("getInitParameter".equals(method.getName()) && args.length == 1
					&& "keycloak.embedded".equals(args[0])) {
				return "true";
			}

			LOG.info("Invoke on ServletContext: method=[{}] args=[{}]", method.getName(), Arrays.toString(args));

			return method.invoke(servletContext, args);
		};

		return (ServletContext) Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
	}
}
