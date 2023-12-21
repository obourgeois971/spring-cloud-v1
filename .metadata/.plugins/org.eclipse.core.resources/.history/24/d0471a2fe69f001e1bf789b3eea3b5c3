package net.javaguides.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.bean.Student;

@RestController
public class StudentController {

	/** http://localhost:8080/student */
	@GetMapping("student")
	public Student getStudent() {
		Student student = new Student(1, "Ramesh", "Fadatare");
		return student;
	}
	
	/** http://localhost:8080/students */
	@GetMapping("students")
	public List<Student> getStudents() {
		List<Student> students = new ArrayList<>();
		students.add(new Student(1, "Ramesh", "Fadatare"));
		students.add(new Student(2, "umesh", "Fadatare"));
		students.add(new Student(1, "Ram", "Fadatare"));
		students.add(new Student(1, "Sanjay", "Fadatare"));
		return students;
	}
}
