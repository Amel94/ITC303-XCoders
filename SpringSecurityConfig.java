package lk.dialog.ist.reslo.services.Config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;



@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
 
	 
	 @Autowired
	 @Qualifier("authenticationFailureHandler")
	 SimpleUrlAuthenticationFailureHandler authenticationFailureHandler;

	 @Override
	    protected void configure(HttpSecurity http) throws Exception {

	        http.csrf().disable()
	                .authorizeRequests()
	                .antMatchers("/","/test","/assets/**","/appJs/**").permitAll()
	                .antMatchers("/resources/**","/css/**","/js/**","/img/**","/**/favicon.ico","/font-awesome/**","/icons/**").permitAll()
	                .antMatchers("/UserManagement").hasAnyRole("SUPER_USER","CREATE_USER","UPDATE_USER","SEARCH_USER")
	                .antMatchers("/accountOwner").hasAnyRole("SUPER_USER","CREATE_ACCOUNT_OWNER","SEARCH_ACCOUNT_OWNER","UPDATE_ACCOUNT_OWNER")
	                .anyRequest().authenticated()
	                .and()
	                .formLogin().failureHandler(authenticationFailureHandler)
	                .loginPage("/login")
	                .defaultSuccessUrl("/newHome")
	                .permitAll()
	                .and()
	                .logout().logoutSuccessUrl("/?message="+ UserStatus.LOGOUT_SUCCESS.getMsg())
	                .permitAll()
	                .and()
	                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
	               
	    }
    
    
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
          .withUser("admin").password(passwordEncoder().encode("admin")).roles("SUPER_USER","CREATE_ACCOUNT_OWNER","SEARCH_ACCOUNT_OWNER","UPDATE_ACCOUNT_OWNER")
          .and()
          .withUser("amel").password(passwordEncoder().encode("amel")).roles("SUPER_USER","CREATE_ACCOUNT_OWNER","SEARCH_ACCOUNT_OWNER","UPDATE_ACCOUNT_OWNER")
          .and()
          .withUser("ather").password(passwordEncoder().encode("ather")).roles("SUPER_USER","CREATE_USER","UPDATE_USER","SEARCH_USER");
    }
       

}