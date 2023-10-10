package com.codSoft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@Validated
public class MainController {

	@Autowired
	public StudentService studentService;
	@GetMapping("/home")
	public String home(Model model) {
		List<Student> students= this.studentService.getAllStudents();
		model.addAttribute("students",students);
		return "index";
	}
	
	@RequestMapping("/about")
	public String about() {
		return "About";
	}
	
	@RequestMapping("/contact")
	public String contact() {
		return "contact";
	}
	
	@GetMapping("/All-Students")
	public String allStudents(Model model) {
		List<Student> students=studentService.getAllStudents();
		model.addAttribute("students", students);
		return "allStudents";
	}
	
	
	
	@GetMapping("/Student-Details/{id}")
	public String studentDetail(Model model,@PathVariable int id) {
		Student student=studentService.getStudent(id);
		model.addAttribute("student", student);
		return "dynamicStudentDetail";
	}
	
	
	@GetMapping("/search")
	public String searching(@RequestParam("query") String search, Model m) {
	    List<Student> students = studentService.getAllStudents();
	    List<Student> searchStudents = new ArrayList<>();

	    for (Student s : students) {
	        if (s.getName() != null && containsPartial(s.getName(), search)
	                || s.getSubject() != null && containsPartial(s.getSubject(), search)
	                || s.getCollegeName() != null && containsPartial(s.getCollegeName(), search)
	                || s.getAddress() != null && containsPartial(s.getAddress(), search)
	                || (String.valueOf(s.getAge()) != null && containsPartial(String.valueOf(s.getAge()), search))) {
	            searchStudents.add(s);
	        }
	    }

	    m.addAttribute("s", searchStudents);
	    return "searchStudent";
	}


	private boolean containsPartial(String original, String partial) {
	    return original != null && original.toLowerCase().contains(partial.toLowerCase());
	}


	
	@GetMapping("/Add-Student")
	public String AddStudent(Model model) {
		model.addAttribute("student", new Student());
		return "newStudent";
	}
	

	@PostMapping("/newEntry")
	public String save( @ModelAttribute("student") Student student, Model model) {
		if(student.getAge()<3 || student.getAge()>50 || student.getName().length()<3) {
			model.addAttribute("errorMessage", "Invalid Age or Name!");
			return "newStudent";
		}
	    try {
	        studentService.addStudent(student);
	        model.addAttribute("successMessage", "Student Saved Successfully!");
	    } catch (Exception e) {
	        e.printStackTrace();
	        model.addAttribute("errorMessage", "Something went wrong while saving the student.");
	    }
	    return "newStudent";
	}

    
    @GetMapping("/Update-Student/{id}")
    public String updateStudent(@PathVariable int id, Model model) {
		Student student=studentService.getStudent(id);
		model.addAttribute("student", student);
        return "updateStudent"; 
    }
    
    @PostMapping(value = "/updatedStudent/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String updatedStudent(@ModelAttribute("student")Student s,@PathVariable ("id") int id ,Model model) {
    	try {
    	studentService.updateStudent(s,id);
    	model.addAttribute("successMessage", "Student Updated Successfully!");
    	}catch(Exception e) {
    		e.printStackTrace();
    		model.addAttribute("errorMessage", "Something went wrong while updating student!");
    	}
    	return "updateStudent";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable ("id") int id,Model model) {
    	try {
    	studentService.deleteStudent(id);
    	model.addAttribute("successMessage", "Student Successfully Deleted!");
    	}catch(Exception e) {
    		e.printStackTrace();
    		model.addAttribute("errorMessage", "Something went wrong!");
    	}
    	return "redirect:/All-Students";
    }
}
