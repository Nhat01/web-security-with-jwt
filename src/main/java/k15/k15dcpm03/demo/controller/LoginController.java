package k15.k15dcpm03.demo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import k15.k15dcpm03.demo.jwt.JwtTokenProvider;
import k15.k15dcpm03.demo.payload.*;
import k15.k15dcpm03.demo.user.CustomUserDetails;


@RestController
@CrossOrigin
public class LoginController {
	 @Autowired
	    AuthenticationManager authenticationManager;

	    @Autowired
	    private JwtTokenProvider tokenProvider;
	    
	    @PostMapping("/authenticate")
	    public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
	    	System.out.println("-------------LoginResponse--------------------");
	        // Xác thực thông tin người dùng Request lên
	    	System.out.println("loginRequest.getUsername():"+loginRequest.getUsername());
	    	System.out.println("loginRequest.getPassword():"+loginRequest.getPassword());
	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        loginRequest.getUsername(),
	                        loginRequest.getPassword()
	                )
	        );   
	        System.out.println("authentication:"+authentication);
	        // Nếu không xảy ra exception tức là thông tin hợp lệ
	        // Set thông tin authentication vào Security Context
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	     
	        System.out.println("Qua SecurityContextHolder:");
	        
	        CustomUserDetails userdetail=(CustomUserDetails) authentication.getPrincipal();
	        System.out.println("userdetail:"+userdetail);
	        // Trả về jwt cho người dùng.
	        String jwt = tokenProvider.generateToken(userdetail);
	        System.out.println("jwt:"+jwt);
	        
	        return new LoginResponse(jwt);
	    }
	    
	    @GetMapping("/authenticate1")
	    public String test()
	    {
	    	return "hello";
	    }
}
