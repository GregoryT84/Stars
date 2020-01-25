package pl.coderslab.STARS;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("Adam")
                .password("user1")
                .roles("L2")
                .build();
        UserDetails starsUser = User.withDefaultPasswordEncoder()
                .username("Grzesiek")
                .password("admin1")
                .roles("STARS")
                .build();
        return new InMemoryUserDetailsManager(user, starsUser);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/clarification/add", "/clarification/all","/payment/add", "/payment/pending").hasAnyRole("STARS" , "L2")
                .anyRequest().hasRole("STARS")
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll();
//                .and()
//                .exceptionHandling().accessDeniedPage("accessDenied");

    }
}
