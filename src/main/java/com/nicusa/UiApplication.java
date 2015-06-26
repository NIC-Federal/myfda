package com.nicusa;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@PropertySources({
    @PropertySource(value="file:${user.home}/.nic/unikitty.properties", ignoreResourceNotFound = true) })
public class UiApplication {
    
  private static final Logger log = LoggerFactory.getLogger(UiApplication.class);

  private static final String KEYSTORE_FILE = "keystoreFile";
  private static final String KEYSTORE_ALIAS = "keystoreAlias";
  private static final String KEYSTORE_PASSWORD = "keyPwd";
  
  @Autowired
  Environment env;
  
  
  public static void main(String[] args) {
    SpringApplication.run(UiApplication.class, args);
  }

  @RequestMapping("/resource")
  public Map<String, Object> home() {
    Map<String, Object> model = new HashMap<>();
    model.put("id", UUID.randomUUID().toString());
    model.put("content", "Hello World");
    return model;
  }
  
  @Bean
  public EmbeddedServletContainerCustomizer containerCustomizer() throws FileNotFoundException
  {
      String absoluteKeystoreFile = null;
      String keyfileAlias = null;
      String keyfilePassword = null;
      
      try {
          String absoluteKeystoreFileName = env.getProperty("absolute.file.keystore");
          keyfileAlias = env.getProperty("keyfile.alias");
          keyfilePassword = env.getProperty("keyfile.password");
          
          absoluteKeystoreFile = ResourceUtils.getFile(absoluteKeystoreFileName).getAbsolutePath();
      } catch (Exception e)
      {
          log.warn("Keystore not defined ");
      }
  

      final TomcatConnectorCustomizer customizer = new SslTomcatConnectionCustomizer(
          absoluteKeystoreFile, keyfilePassword, "PKCS12", keyfileAlias); 

      return new EmbeddedServletContainerCustomizer() {

        @Override
        public void customize(ConfigurableEmbeddedServletContainer container) {
          if(container instanceof TomcatEmbeddedServletContainerFactory) {
            TomcatEmbeddedServletContainerFactory containerFactory = (TomcatEmbeddedServletContainerFactory) container;
            containerFactory.addConnectorCustomizers(customizer);
          }
        };
      };
  }

  @Configuration
  @EnableWebSecurity
  @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
  @PropertySources({ 
    @PropertySource(value = "file:${sys:user.home}/.nic/unikitty.properties", ignoreResourceNotFound = true),
    @PropertySource(value = "file:${user.home}/.nic/unikitty.properties", ignoreResourceNotFound = true) })
  protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.httpBasic().and().authorizeRequests()
        .antMatchers("/","/index.html"
                ,"/recalls"
                ,"/drug/recalls"
                ,"/drug/enforcements").permitAll()
        .anyRequest().fullyAuthenticated()
        .and().csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
      auth
        .inMemoryAuthentication()
        .withUser("user").password("password").roles("USER");
    }
  }

}
