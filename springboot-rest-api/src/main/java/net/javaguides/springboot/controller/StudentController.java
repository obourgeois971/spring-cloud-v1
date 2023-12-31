package net.javaguides.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.bean.Student;

@RestController
@RequestMapping("students")
public class StudentController {

	/** http://localhost:8080/student */
	@GetMapping("student")
	public ResponseEntity<Student> getStudent() {
		Student student = new Student(1, "Ramesh", "Fadatare");
		// return new ResponseEntity<>(student, HttpStatus.OK);
		// return ResponseEntity.ok(student);
		return ResponseEntity.ok()
							 .header("custom-header", "ramesh")
							 .body(student);
	}
	
	/** http://localhost:8080/students */
	@GetMapping
	public ResponseEntity<List<Student>> getStudents() {
		List<Student> students = new ArrayList<>();
		students.add(new Student(1, "Ramesh", "Fadatare"));
		students.add(new Student(2, "umesh", "Fadatare"));
		students.add(new Student(1, "Ram", "Fadatare"));
		students.add(new Student(1, "Sanjay", "Fadatare"));
		return ResponseEntity.ok(students);
	}
	
	/** Spring Boot Rest API with Path Variable */
	/** {id} - URI template variable */
	// http://localhost:8080/students/1/ramesh/dadatare
	@GetMapping("{id}/{first-name}/{last-name}")
	public ResponseEntity<Student> studentPathVariable(
										@PathVariable("id") int studentId,
										@PathVariable("first-name") String firstName,
										@PathVariable("last-name") String lastName
										) {
		Student student = new Student(studentId, firstName, lastName);
		return ResponseEntity.ok(student);
	}
	
	/** Spring Boot Rest Api with Request Param */
	/** http://localhost:8080/students/query?id=1&firstName=Ramesh&lastName=Fadatare */
	@GetMapping("query")
	public ResponseEntity<Student> studentRequestVariable(
										   @RequestParam int id,
										   @RequestParam String firstName,
										   @RequestParam String lastName
			) {
		Student student =  new Student(id, firstName, lastName);
		return ResponseEntity.ok(student);
	}
	
	/** Spring Boot Rest Api that handles HTTP Post Request - creating new resource */
	/** @PostMapping and @RequestBody */
	/** http://localhost:8080/students/create */
	@PostMapping("create")
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		System.out.println(student.getId());
		System.out.println(student.getFirstName());
		System.out.println(student.getLastName());
		return new ResponseEntity<>(student, HttpStatus.CREATED);
	}
	
	/** Spring Boot Rest Api that handles HTTP PUT Request - updating existing resource */
	@PutMapping("{id}/update")
	public ResponseEntity<Student> updateStudent(
								  @RequestBody Student student, 
								  @PathVariable("id") int studentId
								) {
		System.out.println(student.getFirstName());
		System.out.println(student.getLastName());
		return ResponseEntity.ok(student);
	}
	
	/** Spring boot Rest Api that handles HTTP Delete Request - deleting the existing resource */
	@DeleteMapping("{id}/delete")
	public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId) {
		System.out.println(studentId);
		return ResponseEntity.ok("Student deleted successfully!");
	}
}
