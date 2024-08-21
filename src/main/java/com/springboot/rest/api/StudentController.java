package com.springboot.rest.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
    private StudentService studentService;

	 @GetMapping("/student")
	    public List<Student> getAllStudents() {
	        return studentService.findAll();
	    }

	    @GetMapping("/student/page")
	    public Page<Student> getStudents(Pageable pageable) {
	        return studentService.findAll(pageable);
	    }
	    
	    @GetMapping("/paging")
	    public ResponseEntity<Page<Student>> getPaginatedStudents(@RequestParam(defaultValue="0")int page,@RequestParam(defaultValue="8")int size){
	    	Pageable pageable=PageRequest.of(page, size);
	    	Page<Student> paginatedStudents=studentService.findAll(pageable);
	    	return new ResponseEntity<>(paginatedStudents,HttpStatus.OK);
	    	
	    }
	    @GetMapping("/sorting")
	    public ResponseEntity<List<Student>> getSortedStudents(
	            @RequestParam(defaultValue = "sname") String sortBy,
	            @RequestParam(defaultValue = "asc") String direction) {
	        
	        List<Student> sortedByStu = studentService.getSortedStudents(sortBy, direction);
	        return new ResponseEntity<>(sortedByStu, HttpStatus.OK);
	    }


	    @PostMapping("/student/add")
	    public Student createStudent(@RequestBody Student student) {
	        return studentService.save(student);
	    }

	    @GetMapping("/student/{id}")
	    public Student getStudentById(@PathVariable Long id) {
	        return studentService.findById(id);
	    }

	    @PutMapping("/student/{id}")
	    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
	        student.setId(id);
	        return studentService.save(student);
	    }

	    @DeleteMapping("/student/{id}")
	    public void deleteStudent(@PathVariable Long id) {
	        studentService.deleteById(id);
	    }
	

}
