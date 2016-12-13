package org.pac4j.demo.spark;

import org.pac4j.cas.client.CasClient;
import org.pac4j.core.authorization.authorizer.RequireAnyRoleAuthorizer;
import org.pac4j.core.client.Clients;
import org.pac4j.core.config.Config;
import org.pac4j.core.config.ConfigFactory;
import org.pac4j.core.matching.ExcludedPathMatcher;
import org.pac4j.http.client.direct.DirectBasicAuthClient;
import org.pac4j.http.client.direct.ParameterClient;
import org.pac4j.http.client.indirect.FormClient;
import org.pac4j.http.client.indirect.IndirectBasicAuthClient;
import org.pac4j.http.credentials.authenticator.test.SimpleTestUsernamePasswordAuthenticator;
import org.pac4j.jwt.credentials.authenticator.JwtAuthenticator;
import org.pac4j.oauth.client.FacebookClient;
import org.pac4j.oauth.client.TwitterClient;
import org.pac4j.oidc.client.OidcClient;
import org.pac4j.saml.client.SAML2Client;
import org.pac4j.saml.client.SAML2ClientConfiguration;
import spark.TemplateEngine;

public class DemoConfigFactory implements ConfigFactory {

    private final String salt;

    private final TemplateEngine templateEngine;

    public DemoConfigFactory(final String salt, final TemplateEngine templateEngine) {
        this.salt = salt;
        this.templateEngine = templateEngine;
    }

    @Override
    public Config build() {

        //Google login
        final OidcClient oidcClient = new OidcClient();
        oidcClient.setClientID("343992089165-sp0l1km383i8cbm2j5nn20kbk5dk8hor.apps.googleusercontent.com");
        oidcClient.setSecret("uR3D8ej1kIRPbqAFaxIE3HWh");
        oidcClient.setDiscoveryURI("https://accounts.google.com/.well-known/openid-configuration");
        oidcClient.setUseNonce(true);

        //oidcClient.setPreferredJwsAlgorithm(JWSAlgorithm.RS256);
        oidcClient.addCustomParam("prompt", "consent");
        oidcClient.setAuthorizationGenerator(profile -> profile.addRole("ROLE_ADMIN"));


//        Facebook Login
        final FacebookClient facebookClient = new FacebookClient("145278422258960", "be21409ba8f39b5dae2a7de525484da8");

        // HTTP
        final FormClient formClient = new FormClient("http://localhost:8080/loginForm", new SimpleTestUsernamePasswordAuthenticator());
        final IndirectBasicAuthClient indirectBasicAuthClient = new IndirectBasicAuthClient(new SimpleTestUsernamePasswordAuthenticator());



        // REST authent with JWT for a token passed in the url as the token parameter
        ParameterClient parameterClient = new ParameterClient("token", new JwtAuthenticator(salt));
        parameterClient.setSupportGetRequest(true);
        parameterClient.setSupportPostRequest(false);

        // basic auth
        final DirectBasicAuthClient directBasicAuthClient = new DirectBasicAuthClient(new SimpleTestUsernamePasswordAuthenticator());

        final Clients clients = new Clients("http://localhost:8080/callback", oidcClient, facebookClient,
                 formClient, indirectBasicAuthClient, parameterClient, directBasicAuthClient);

        final Config config = new Config(clients);
        config.addAuthorizer("admin", new RequireAnyRoleAuthorizer("ROLE_ADMIN"));
        config.addAuthorizer("custom", new CustomAuthorizer());
        config.addMatcher("excludedPath", new ExcludedPathMatcher("^/facebook/notprotected$"));
        config.setHttpActionAdapter(new DemoHttpActionAdapter(templateEngine));
        return config;
    }
}
