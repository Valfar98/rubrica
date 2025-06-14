package com.corso.rubrica.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity()
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()) // Disabilita CSRF per API RESTful (attento in produzione)
				.cors(cors -> cors.configurationSource(corsConfigurationSource())) // Abilita CORS
				.authorizeHttpRequests(authorize -> authorize
						// Permetti a tutti l'accesso alla pagina di login e registrazione (se la
						// implementi)
						.requestMatchers("/auth/**", "/login", "/register").permitAll() // Esempio: endpoint di
																						// login/registrazione
						// Permetti l'accesso non autenticato a risorse statiche (se presenti)
						.requestMatchers("/css/**", "/js/**", "/images/**", "/").permitAll()
						// Richiedi autenticazione per tutti gli altri endpoint della rubrica
						.requestMatchers("/rubrica/**").authenticated() // Tutti gli endpoint della rubrica richiedono
																		// autenticazione
						// Permetti tutte le altre richieste (o metti una regola più restrittiva)
						.anyRequest().authenticated())
				.formLogin(form -> form.loginPage("/login") // Specifica una pagina di login personalizzata (se ne hai
															// una)
						.loginProcessingUrl("/perform_login") // URL a cui inviare le credenziali di login
						.defaultSuccessUrl("/rubrica/getAll", true) // URL di reindirizzamento dopo login successo
						.failureUrl("/login?error=true") // URL di reindirizzamento dopo login fallito
						.permitAll() // Permetti l'accesso alla pagina di login
				).logout(logout -> logout.logoutUrl("/logout") // URL per il logout
						.logoutSuccessUrl("/login?logout=true") // URL dopo logout successo
						.permitAll());
		return http.build();
	}
}
