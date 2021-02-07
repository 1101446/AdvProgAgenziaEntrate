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
		http.authorizeRequests().antMatchers("/registration/").permitAll().antMatchers("/registration/**").permitAll();
		http.authorizeRequests().antMatchers("/**").hasAnyRole("ADMIN","UTENTE")
			.and().formLogin().loginPage("/login").defaultSuccessUrl("/")
			.failureUrl("/login?error=true").permitAll().and().logout().logoutSuccessUrl("/") 
			.invalidateHttpSession(true).permitAll().and().csrf().disable();
		http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/roles/**").hasRole("ADMIN");
		http.authorizeRequests().antMatchers("/institution/**").hasAnyRole("ADMIN","ENTE", "UTENTE");
		http.authorizeRequests().antMatchers("/realestates/**").hasAnyRole("ADMIN","CATASTO", "UTENTE");
		http.authorizeRequests().antMatchers("/vehicles/**").hasAnyRole("ADMIN","MOTORIZZAZIONE", "UTENTE");
		http.authorizeRequests().antMatchers("/isee/**").hasAnyRole("ADMIN","ENTRATE", "UTENTE");
		http.authorizeRequests().antMatchers("/users/**").hasAnyRole("ADMIN","ENTRATE");
		http.authorizeRequests().antMatchers("/users/profile/**").hasAnyRole("UTENTE");
	}
}