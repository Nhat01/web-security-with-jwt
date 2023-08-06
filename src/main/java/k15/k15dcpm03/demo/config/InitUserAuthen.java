package k15.k15dcpm03.demo.config;

import org.slf4j.LoggerFactory;
//import org.apache.logging.log4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import k15.k15dcpm03.demo.user.UserRepository;


@Component
public class InitUserAuthen implements ApplicationRunner {
	public static final org.slf4j.Logger logger=LoggerFactory.getLogger(InitUserAuthen.class);
	@Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("-------------BAT DAU TAO USER--------------------");
		k15.k15dcpm03.demo.user.User user = new k15.k15dcpm03.demo.user.User();
        user.setUsername("hktin");
        user.setFirstName("Kim Tin");
        user.setLastName("Hoang");
        user.isApproved();
        user.setEmail("tin@gmail.com");
        user.setPassword(passwordEncoder.encode("12345678"));
        //password("{bcrypt}$2y$10$GCbwaDs5NpSBFFItT9aGmuYiSNjdH6lHVUPfqctbIbZTprx3Q7Lfi")
        //user.setPassword("{bcrypt}$2y$10$GCbwaDs5NpSBFFItT9aGmuYiSNjdH6lHVUPfqctbIbZTprx3Q7Lfi");
        System.out.println("name="+user.getUsername()+"password="+user.getPassword());
        logger.info("insert user:"+userRepository.save(user));
    
        System.out.println("-------------HET TAO USER--------------------");
        System.out.println(user);
		
	}

}
