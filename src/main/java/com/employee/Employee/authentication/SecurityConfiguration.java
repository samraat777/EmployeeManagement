package com.employee.Employee.authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
/*
@EnableMethodSecurity
The above annotation enable method level role scanning
In the rest method controller we can add the annotation to allow only certaim roles user to access it
Eg: the controller method should look like this

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/new")
    public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody EmployeeDTO employeeDTO)
    {
        employeeService.saveEmployee(employeeDTO);
        return new ResponseEntity<>(employeeDTO,HttpStatus.CREATED);
    }
 */
public class SecurityConfiguration {
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password(encoder().encode("user"))
                .roles("USER")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password((encoder().encode("admin")))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/emp/employee").hasRole("ADMIN")
                        .requestMatchers("/emp/remove/**").hasRole("ADMIN")
                )
                .httpBasic(withDefaults())
                .formLogin(withDefaults())
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();


         /*

          http.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest()
                .authenticated())
                .httpBasic(withDefaults())
                .formLogin(withDefaults())
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
       */

    }

}
