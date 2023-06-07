package fr.simplon.pixelshielrestapi.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {

    private final  javax.sql.DataSource dataSource;

    @Bean
    public UserDetailsManager users(DataSource dataSource)
    {
        return new JdbcUserDetailsManager(dataSource);
    }
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        return http
                .csrf().disable() // Pour l'instant on désactive la protection CSRF
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET,"/profil", "/password").authenticated()
                .requestMatchers("/dashboard", "/clients", "/client_details", "/client_edit", "/profil_client").hasAnyRole("ADMIN", "MANAGER")
                .requestMatchers(HttpMethod.GET,  "/employe_details","/employe_edit", "/employees").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET,   "/sondages" , "/sinistres", "/sinistre" ).hasRole("MANAGER")
                .requestMatchers(HttpMethod.GET,"/materiel", "/mes_sinistres" ).hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/", "/about", "/login", "/faq", "/nosvaleurs","/nostarifs", "/souscription", "/contact", "/mentionslegales").permitAll()
                .requestMatchers(HttpMethod.POST, "/login", "/souscription", "/contact", "/password").permitAll()
                .requestMatchers(HttpMethod.GET,"/**").permitAll()
                .and().formLogin()
                .and().build();
    }

}
