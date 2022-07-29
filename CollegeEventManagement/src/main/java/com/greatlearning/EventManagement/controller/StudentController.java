package com.greatlearning.EventManagement.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greatlearning.EventManagement.entities.Student;
import com.greatlearning.EventManagement.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;


	@RequestMapping("/list")
	public String listStudents(Model model) {
		List<Student> students = studentService.findAll();

		model.addAttribute("Students", students);

		return "list";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		Student student = new Student();
		model.addAttribute("Student", student);

		return "form";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int id, Model model) {
		Student student = studentService.findById(id);
		model.addAttribute("Student", student);

		return "form";
	}

	@PostMapping("/save")
	public String saveStudent(@RequestParam("id") int id, @RequestParam("name") String name,
			@RequestParam("dept") String dept, @RequestParam("country") String country) {
		
		System.out.println("The Id : " + id);
		Student student;
		if (id != 0) {
			student = studentService.findById(id);
			student.setName(name);
			student.setDept(dept);
			student.setCountry(country);
		}
		else {
			student = new Student(name, dept, country);
		}
		studentService.save(student);
		
		return "redirect:/student/list";
	}
	
	@RequestMapping("/delete")
	public String deleteStudent(@RequestParam("studentId") int id) {
		studentService.deletById(id);
		
		return "redirect:/student/list";
	}
	
	@RequestMapping(value = "/403")
	public ModelAndView accesssDenied(Principal user) {

		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() 
			+ ", you do not have permission to access this page!");
		} else {
			model.addObject("msg", 
			"You do not have permission to access this page!");
		}

		model.setViewName("403");
		return model;

	}
}
