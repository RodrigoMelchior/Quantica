package br.com.web.pesquisas.configuration;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import br.com.web.pesquisas.security.AjaxLogoutSuccessHandler;
import br.com.web.pesquisas.security.AuthoritiesConstants;
import br.com.web.pesquisas.security.Http401UnauthorizedEntryPoint;

@Configuration
public class OAuth2ServerConfiguration {

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        @Inject
        private Http401UnauthorizedEntryPoint authenticationEntryPoint;

        @Inject
        private AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler;

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
            .and()
                .logout()
                .logoutUrl("/api/logout")
                .logoutSuccessHandler(ajaxLogoutSuccessHandler)
            .and()
                .csrf()
                .disable()
                .headers()
                .frameOptions().disable()
            .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/api/authenticate").permitAll()
                .antMatchers("/api/register").permitAll()
                .antMatchers("/api/logs/**").hasAnyAuthority(AuthoritiesConstants.ADMIN)
                
                .antMatchers("/metrics/**").hasAuthority(AuthoritiesConstants.ADMIN)
                .antMatchers("/health/**").hasAuthority(AuthoritiesConstants.ADMIN)
                .antMatchers("/trace/**").hasAuthority(AuthoritiesConstants.ADMIN)
                .antMatchers("/dump/**").hasAuthority(AuthoritiesConstants.ADMIN)
                .antMatchers("/activiti/**").hasAuthority(AuthoritiesConstants.ADMIN)
                .antMatchers("/shutdown/**").hasAuthority(AuthoritiesConstants.ADMIN)
                .antMatchers("/beans/**").hasAuthority(AuthoritiesConstants.ADMIN)
                .antMatchers("/configprops/**").hasAuthority(AuthoritiesConstants.ADMIN)
                .antMatchers("/info/**").hasAuthority(AuthoritiesConstants.ADMIN)
                .antMatchers("/autoconfig/**").hasAuthority(AuthoritiesConstants.ADMIN)
                .antMatchers("/env/**").hasAuthority(AuthoritiesConstants.ADMIN)
                .antMatchers("/trace/**").hasAuthority(AuthoritiesConstants.ADMIN)
                .antMatchers("/api-docs/**").hasAuthority(AuthoritiesConstants.ADMIN)
                .antMatchers("/protected/**").authenticated()
                
                // Configuracao do acessos a api de negocio.
                .regexMatchers(HttpMethod.GET, "/api/usuarios/autenticado").authenticated()
                .regexMatchers(HttpMethod.POST, "/api/usuarios/alteracaosenha").authenticated()
                .regexMatchers(HttpMethod.GET, "/api/ufs").authenticated()
                .regexMatchers(HttpMethod.GET, "/api/ufs/\\d+/municipios").authenticated();
                //.regexMatchers(HttpMethod.POST, "/api/clientes").hasAuthority(PermissionsConstants.CLIENTE_INSERT)
                //.regexMatchers(HttpMethod.PUT, "/api/clientes/\\d+").hasAuthority(PermissionsConstants.CLIENTE_EDIT)
                //.regexMatchers(HttpMethod.GET, "/api/clientes").hasAnyAuthority(PermissionsConstants.CLIENTE_VIEW, PermissionsConstants.CLIENTE_INSERT, PermissionsConstants.CLIENTE_EDIT, PermissionsConstants.CLIENTE_DELETE )
                //.regexMatchers(HttpMethod.DELETE, "/api/clientes/\\d+").hasAuthority(PermissionsConstants.CLIENTE_DELETE)
                //.regexMatchers(HttpMethod.GET, "/api/clientes/search").hasAnyAuthority(PermissionsConstants.CLIENTE_VIEW, PermissionsConstants.CLIENTE_INSERT, PermissionsConstants.CLIENTE_EDIT, PermissionsConstants.CLIENTE_DELETE)
                //.regexMatchers(HttpMethod.GET, "/api/clientes/validacao/cnpjcadastrado/\\d++").hasAuthority(PermissionsConstants.CLIENTE_INSERT);
                
        }
        
        
        
    }

    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {


        @Autowired
        private ApplicationProperties applicationProperties;

        
        @Autowired
        private TokenStore tokenStore;
        
        
        @Autowired
        @Qualifier("authenticationManagerBean")
        private AuthenticationManager authenticationManager;

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints)
                throws Exception {

            endpoints
                    .tokenStore(tokenStore)
                    .authenticationManager(authenticationManager);
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
            oauthServer.allowFormAuthenticationForClients();
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients
                .inMemory()
                .withClient(applicationProperties.getSecurity().getAuthentication().getOauth().getClientid())
                .scopes("read", "write")
                .authorities(AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER)
                .authorizedGrantTypes("password", "refresh_token", "authorization_code", "implicit")
                .secret(applicationProperties.getSecurity().getAuthentication().getOauth().getSecret())
                .accessTokenValiditySeconds(applicationProperties.getSecurity().getAuthentication().getOauth().getTokenValidityInSeconds());
        }
    }
}
