//package com.todolist.todolist.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//public class SecSecurityConfig {
//  @Bean
//  public SecurityFilterChain filterChain(HttpSecurity http)
//      throws Exception {
//    http.csrf().disable().authorizeRequests()
//        //...
//        .antMatchers(
//            HttpMethod.GET,
//            "/index*", "/static/**", "/*.js", "/*.json", "/*.ico")
//        .permitAll()
//        .anyRequest().authenticated()
//        .and()
//        .formLogin().loginPage("/index.html")
//        .loginProcessingUrl("/perform_login")
//        .defaultSuccessUrl("/homepage.html",true)
//        .failureUrl("/index.html?error=true")
//    //...
//  }
//}
