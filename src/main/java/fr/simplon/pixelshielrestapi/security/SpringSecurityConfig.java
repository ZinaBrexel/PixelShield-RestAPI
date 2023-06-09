package fr.simplon.pixelshielrestapi.security;

import org.springframework.beans.factory.annotation.Autowired;
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

public class SpringSecurityConfig {
    @Autowired
    private javax.sql.DataSource dataSource;

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
                .requestMatchers(HttpMethod.GET,"/profil/**", "/password/**").authenticated()
                .requestMatchers(HttpMethod.GET,"/dashboard", "/client/details/**", "/edit/client/**", "/clients").hasAnyRole("ADMIN", "MANAGER")
                .requestMatchers(HttpMethod.GET,   "/employees", "/dashboard/moderation/**", "/employe/details/**", "/edit/employe/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET,   "/sondages" , "/sinistres", "/sinistre", "/sondages/vote/**" ).hasRole("MANAGER")
                .requestMatchers(HttpMethod.GET, "/mes_sinistres", "/mon_materiel", "/mon_materiel/delete/**").hasRole("USER")
                .requestMatchers(HttpMethod.POST, "/password/**", "/edit/profil/**").hasAnyRole("ADMIN", "MANAGER", "USER")
                .requestMatchers(HttpMethod.POST,"/edit/client/**").hasAnyRole("ADMIN", "MANAGER")
                .requestMatchers(HttpMethod.POST, "/createManager", "/dashboard/moderation/**", "/edit/employe/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/createSurvey", "/sondages/vote/**").hasRole("MANAGER")
                .requestMatchers(HttpMethod.POST, "/mes_sinistres", "/mon_materiel", "/mon_materiel/delete/**").hasRole("USER")
                .requestMatchers(HttpMethod.POST, "/login", "/souscription", "/contact", "/createUser").permitAll()
                .requestMatchers(HttpMethod.GET,"/**").permitAll()
                .and().formLogin()
                .loginPage("/login") // Ici on spécifie la page de login
                .and().build();
    }

}