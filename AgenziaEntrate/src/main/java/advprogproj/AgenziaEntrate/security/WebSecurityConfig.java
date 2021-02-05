package advprogproj.AgenziaEntrate.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import advprogproj.AgenziaEntrate.services.UserServiceDefault;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserServiceDefault();
	};
	
	/**
	 * Configurazione dell'autenticazione
	 */
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {

// ruoli provvisori
//		auth.inMemoryAuthentication().withUser("imuser")
//			.password(this.passwordEncoder.encode("imuser"))
//			.roles("USER");
//		auth.inMemoryAuthentication().withUser("imadmin")
//			.password(this.passwordEncoder.encode("imadmin"))
//			.roles("USER", "ADMIN");

		auth.userDetailsService(userDetailsService()).passwordEncoder(this.passwordEncoder);
	}
	
	/**
	 * Configurazione dell'autorizzazione
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().
			antMatchers("/login").permitAll().
			antMatchers("/").permitAll().
			antMatchers("/institutions/**").hasAnyRole("ENTE", "USER").
			antMatchers("/realestates/**").hasAnyRole("CATASTO", "USER").
			antMatchers("/vehicles/**").hasAnyRole("MOTORIZZAZIONE", "USER").
			antMatchers("/isee/**").hasAnyRole("ENTRATE", "USER").
			antMatchers("/access/**").hasAnyRole("ADMIN").
			antMatchers("/**").hasAnyRole("USER").
				and().formLogin().loginPage("/login").defaultSuccessUrl("/")
					.failureUrl("/login?error=true").permitAll().
				and().logout().logoutSuccessUrl("/") // NB se commentiamo
														// questa riga,
														// viene richiamata
														// /login?logout
					.invalidateHttpSession(true).permitAll().
			and().csrf().disable();
		
//		http.authorizeRequests().antMatchers("/login").permitAll();
//		http.authorizeRequests().antMatchers("/").permitAll();
//		http.authorizeRequests().antMatchers("/instruments/**").hasAnyRole("ADMIN");
//		http.authorizeRequests().antMatchers("/**").hasAnyRole("USER");
		
//		http.formLogin().loginPage("/login");
//		http.formLogin().defaultSuccessUrl("/");
//		http.formLogin().failureUrl("/login?error=true");
//		http.formLogin().permitAll();
		
//		http.logout().logoutSuccessUrl("/");
//		http.logout().invalidateHttpSession(true);
//		http.logout().permitAll();
		
//		http.csrf().disable();

	}
}