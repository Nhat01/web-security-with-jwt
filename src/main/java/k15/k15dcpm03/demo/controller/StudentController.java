package k15.k15dcpm03.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import k15.k15dcpm03.demo.models.*;

@RestController
//@RequestMapping("/api")
public class StudentController {

	@Autowired
	StudentJPA jpa;
	
	
	@GetMapping("listStudents")
	public List<StudentInfo> listStudents()
	{		

		return jpa.findAll();
		
	}
	
	@PostMapping("/insertStudent")
	public String insertStudent(@RequestBody StudentInfo newstudent)
	{
		try {
		jpa.save(newstudent);
		return "Update ok";
		}catch(Exception ex) {
			
			return "Update faild:"+ex.getMessage();
		}
		
	}
	
	@PutMapping("/updateStudent")
	public String updateStudent(@RequestBody StudentInfo student)
	{
		try {
			jpa.save(student);
		
		return "Em đã lưu thành công!";
		}catch(Exception ex)
		{
			return "Lưu bị lỗi:"+ex.getMessage();
		}
		
		
	}
	
	@DeleteMapping("/deleteStudent/{id}")
	public String deleteStudent(@PathVariable Long id)
	{
		
		try {
			jpa.deleteById(id);
			
			return "Em đã xóa thành công!";
			}catch(Exception ex)
			{
				return "Lưu bị lỗi:"+ex.getMessage();
			}
	}
	

}
