package k15.k15dcpm03.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import k15.k15dcpm03.demo.jwt.JwtAuthenticationFilter;
import k15.k15dcpm03.demo.user.UserService;



/*
 
 Gia config cho jwt
 
  
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired
    UserService userService;
	AuthenticationConfiguration configuration;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Autowired
    void configure(AuthenticationManagerBuilder builder) throws Exception {
    	
    	System.out.println("---------AuthenticationManagerBuilder------------");
        builder.userDetailsService(userService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Password encoder, để Spring Security sử dụng mã hóa mật khẩu người dùng
        return new BCryptPasswordEncoder();
    }
    
    // OR use
    // Remove or comment out: private final AuthenticationConfiguration configuration;
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    
    
    
    @SuppressWarnings("deprecation")   	
    protected void configure(HttpSecurity http) throws Exception 
    {
    	System.out.println("---------configure------------");
    	http
    	.cors()
        .and()
        .csrf()
        .disable()       
        .httpBasic()
        .and()
         .authorizeRequests()
        .requestMatchers("/login").permitAll()
        .requestMatchers("/authenticate").permitAll() // Cho phép tất cả mọi người truy cập vào 2 địa chỉ này
        .anyRequest().authenticated(); // Tất cả các request khác đều cần phải xác thực mới được truy cập

        // Thêm một lớp Filter kiểm tra jwt	   	        
		
		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		
    }
    
    
    @SuppressWarnings("deprecation")
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest()
//				 
//			      .permitAll())
//			      .csrf(AbstractHttpConfigurer::disable);
//			    return http.build();
		
    	http
    	.cors().and().csrf().disable()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .httpBasic()
        .and()
        .authorizeHttpRequests(authorize -> authorize
//				.requestMatchers("/user/**").hasRole("USER")       
//				.requestMatchers("/swagger-ui/**").hasRole("USER") 
//				.requestMatchers("/api/**").hasRole("USER") 
        		.requestMatchers("/authenticate").permitAll() 
        		.requestMatchers("/authenticate1").permitAll() 
//        		.requestMatchers("/login").permitAll()
        		.requestMatchers(HttpMethod.POST).hasRole("USER")
        		
        		.requestMatchers("/login").anonymous()
        		
				.anyRequest().authenticated()                      
			);		
		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		return http.build();
		
	}

   

	

}
