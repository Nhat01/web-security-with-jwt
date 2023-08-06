package k15.k15dcpm03.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tblStudents")
public class StudentInfo {
	
	
	public StudentInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public StudentInfo(String studentNumber, String fullName, String address, String email) {
		super();
	
		StudentNumber = studentNumber;
		FullName = fullName;
		Address = address;
		this.email = email;
	}
	
	


	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStudentNumber() {
		return StudentNumber;
	}
	public void setStudentNumber(String studentNumber) {
		StudentNumber = studentNumber;
	}
	public String getFullName() {
		return FullName;
	}
	public void setFullName(String fullName) {
		FullName = fullName;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	private String StudentNumber;
	private String FullName;
	private String Address;
	private String email;
	

}
