package cl.sergiocarocc.portafolio_modulo_6.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

import cl.sergiocarocc.portafolio_modulo_6.service.CustomUserDetailsService;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests(auth -> auth
	        	.requestMatchers("/","/index","/galeria","/login","/registro").permitAll()	
	        	.requestMatchers("/assets/**").permitAll()
	        	.requestMatchers("/productos/**","/carrito/**","/pago/**","/reservas/**").authenticated()
	            .requestMatchers("/admin/**").hasRole("ADMIN")
	           
	        )
	        .userDetailsService(userDetailsService)
	        .formLogin(form -> form
	            .loginPage("/login")            
	            .defaultSuccessUrl("/productos", true)
	            .permitAll()
	        )
	        .logout(logout -> logout
	            .logoutSuccessUrl("/login?logout") 
	            .permitAll()
	        );

	    return http.build();
	}
	
	@Autowired
    private CustomUserDetailsService userDetailsService;

	@Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
	    return authConfig.getAuthenticationManager();
	}
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

}


