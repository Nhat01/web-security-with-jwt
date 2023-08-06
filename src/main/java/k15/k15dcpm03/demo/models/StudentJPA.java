package k15.k15dcpm03.demo.models;

import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentJPA extends JpaRepository<StudentInfo,Long> {

}
