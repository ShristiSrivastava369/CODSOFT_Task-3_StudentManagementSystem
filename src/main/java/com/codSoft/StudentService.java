package com.codSoft;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class StudentService {
	
	@Autowired
	public StudentRepository studentRepository;
	
	public List<Student> getAllStudents() {
		return (List<Student>)studentRepository.findAll();
	}
	
	public Student getStudent(int id) {
		return studentRepository.findById(id);
	}
	
	public Student addStudent(Student s) {
		s.setName(s.getName());
		s.setAge(s.getAge());
		s.setAddress(s.getAddress());
		s.setSubject(s.getSubject());
		s.setCollegeName(s.getCollegeName());
		studentRepository.save(s);
		return s;
	}

	public void deleteStudent(int id) {
		studentRepository.deleteById(id);
	}
	
	public void updateStudent(Student s,int id) {
		Student existingStudent=studentRepository.findById(id);
		if(existingStudent!=null) {
			existingStudent.setName(s.getName());
			existingStudent.setAge(s.getAge());
			existingStudent.setAddress(s.getAddress());
			existingStudent.setSubject(s.getSubject());
			existingStudent.setCollegeName(s.getCollegeName());
			studentRepository.save(existingStudent);
		}
	}
}
