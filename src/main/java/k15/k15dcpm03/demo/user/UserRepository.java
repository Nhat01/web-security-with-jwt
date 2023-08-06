package k15.k15dcpm03.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Long> {
	 User findByUsername(String username);

}
