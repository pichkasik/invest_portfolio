package ru.akapich.invest_portfolio.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ru.akapich.invest_portfolio.configuration.handlers.MyAuthenticationFailureHandler;
import ru.akapich.invest_portfolio.configuration.handlers.MyAuthenticationSuccessHandler;
import ru.akapich.invest_portfolio.configuration.handlers.MyLogoutSuccessHandler;
import ru.akapich.invest_portfolio.service.user.impl.UserDetailsServiceImpl;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Security Configuration.
 * This class сonfigures authorization and navigate user after login
 *
 * @author Aleksandr Marakulin
 **/

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter implements WebMvcConfigurer{

	private static final int STRENGTH_PASSWORD = 10;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Bean
	public  BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder(STRENGTH_PASSWORD);
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider(){
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
		return daoAuthenticationProvider;
	}

	private AuthenticationSuccessHandler successHandler() {
		return new MyAuthenticationSuccessHandler();
	}

	private AuthenticationFailureHandler failureHandler() {
		return new MyAuthenticationFailureHandler() ;
	}

	private LogoutSuccessHandler logoutSuccessHandler(){
		return new MyLogoutSuccessHandler();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedHeaders("*")
				.allowedOriginPatterns("http://localhost:3000")
				.allowedMethods("POST", "GET", "DELETE", "PUT")
				.allowCredentials(true).maxAge(3600);
	}

	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.cors(withDefaults())
				.csrf().disable()
				.authorizeRequests().antMatchers("/**").permitAll()
				.anyRequest().authenticated()
					.and()
				.formLogin().loginProcessingUrl("/api/auth/login")
				.usernameParameter("email")
				.passwordParameter("password")
				.successHandler(successHandler())
				.failureHandler(failureHandler())
					.and()
				.logout().logoutUrl("/api/auth/logout").logoutSuccessHandler(logoutSuccessHandler())
				.invalidateHttpSession(true).deleteCookies("JSESSIONID").permitAll();
	}
}
