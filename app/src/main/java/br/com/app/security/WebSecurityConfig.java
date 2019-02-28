package br.com.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import br.com.app.security.auth.CustomAuthenticationProvider;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomAuthenticationProvider custom;

	@Override
    protected void configure(HttpSecurity http) throws Exception {
		//http.authorizeRequests().anyRequest().permitAll();
		
        http.authorizeRequests()
		        .antMatchers("/h2-console/**").permitAll()
		        .antMatchers("/rest/**").permitAll()
	            .antMatchers("/ativacao/**").permitAll()
            	.antMatchers("/**").authenticated()
                .and()
                
            .formLogin()
            .loginPage("/login")
                .permitAll()
                .and()
             .httpBasic()
             	.and()
             .csrf().ignoringAntMatchers("/api/**", "/h2-console/**", "/rest/**")
             	.and()
             .headers().frameOptions().disable()
             	.and()
             .logout()
                .permitAll();
    }
	
	

    @Override
	public void configure(WebSecurity web) throws Exception {
    	web
	        .ignoring()
	        .antMatchers("/assets/**")
	        .antMatchers("/css/**")
	        .antMatchers("/images/**")
	        .antMatchers("/fonts/**")
	        .antMatchers("/js/**");
	}



	@Autowired
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.authenticationProvider(custom);
		
        /*auth.inMemoryAuthentication()
            	.withUser("contabilidade").password("123").roles("CONTABILIDADE")
            	.and()
            	.withUser("empresa").password("123").roles("EMPRESA")
            	.and()
            	.withUser("ze").password("123").roles("ZE");*/
    }
}