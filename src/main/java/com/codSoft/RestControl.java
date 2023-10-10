package com.codSoft;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RestControl {

	@Autowired
	public StudentService studentService;
	
	//Get All Students
	@GetMapping("/students")
	public List<Student> getBooks() {
		
		return this.studentService.getAllStudents();
	}
	
	//Get student By Id
	@GetMapping("/student/{id}")
	public Student getBook(@PathVariable("id") int id) {
		Student s=this.studentService.getStudent(id);
		return s;
	}
	
	@PostMapping("/addStudent")
	public Student addStudent(@RequestBody Student s) {
		Student std=studentService.addStudent(s);
		return std;
	}
	
	
	@DeleteMapping("/student/{studentId}")
	public void deleteStudent(@PathVariable("studentId") int id) {
		studentService.deleteStudent(id);
	}
	
	@PutMapping("/student/{studentId}")
	public Student updateStudent(@RequestBody Student s,@PathVariable("studentId") int studentId) {
		studentService.updateStudent(s, studentId);
		return s;
	}
}
