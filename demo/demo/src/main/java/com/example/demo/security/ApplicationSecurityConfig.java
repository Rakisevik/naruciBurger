package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.concurrent.TimeUnit;

import static com.example.demo.security.ApplicationCustomerRole.ADMIN;
import static com.example.demo.security.ApplicationCustomerRole.CUSTOMER;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","index","/css/*","/js/*").permitAll()
                .antMatchers("/api/**").hasRole(ADMIN.name())
//                .antMatchers(HttpMethod.DELETE,"/management/api/**").hasAuthority(CUSTOMER_WRITE.getPermission())
//                .antMatchers(HttpMethod.POST,"/management/api/**").hasAuthority(CUSTOMER_WRITE.getPermission())
//                .antMatchers(HttpMethod.PUT,"/management/api/**").hasAuthority(CUSTOMER_WRITE.getPermission())
//                .antMatchers(HttpMethod.GET,"/management/api/**").hasRole(ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .defaultSuccessUrl("/order",true)
                    .passwordParameter("password")
                    .usernameParameter("username")
                .and()
                    .rememberMe().tokenValiditySeconds((int)TimeUnit.DAYS.toSeconds(21))
                    .key("Secured")
                    .rememberMeParameter("remember_me")
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout","GET"))
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID", "remember-me")
                    .logoutSuccessUrl("/login")
        ;
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails stefanRakicevicCustomer = User.builder()
                .username("Rakicevic")
                .password(passwordEncoder.encode("Stefan"))
//                .roles(CUSTOMER.name())
                .authorities(CUSTOMER.getGrantedAuthorities())
                .build();

        UserDetails nikolaVeljicCustomer = User.builder()
                .username("Veljic")
                .password(passwordEncoder.encode("password123"))
//                .roles(ADMIN.name())
                .authorities(ADMIN.getGrantedAuthorities())
                .build();


        return new InMemoryUserDetailsManager(
                stefanRakicevicCustomer,
                nikolaVeljicCustomer
        );
    }
}
