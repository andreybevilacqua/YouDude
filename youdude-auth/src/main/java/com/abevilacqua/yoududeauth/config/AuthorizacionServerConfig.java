package com.abevilacqua.yoududeauth.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizacionServerConfig extends AuthorizationServerConfigurerAdapter  {

  @Value("${user.username}")
  private String username;

  @Value("${user.password}")
  private String password;

  private final TokenStore tokenStore;

  private final PasswordEncoder passwordEncoder;

  @Qualifier("authenticationManagerBean")
  private final AuthenticationManager authenticationManager;

  public AuthorizacionServerConfig(PasswordEncoder passwordEncoder,
                                   AuthenticationManager authenticationManager,
                                   TokenStore tokenStore) {
    this.passwordEncoder = passwordEncoder;
    this.authenticationManager = authenticationManager;
    this.tokenStore = tokenStore;
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
  	clients.inMemory()
			.withClient(username)
			.authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
			.authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT","USER")
			.scopes("read","write")
			.autoApprove(true)
			.secret(passwordEncoder.encode(password));
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
    endpoints
        .authenticationManager(authenticationManager)
        .tokenStore(tokenStore);
	 }
}
