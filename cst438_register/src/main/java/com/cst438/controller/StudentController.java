package com.cst438.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cst438.domain.Student;
import com.cst438.domain.StudentRepository;

@RestController
public class StudentController {
	
	@Autowired
	StudentRepository studentRepository;
	
	@PostMapping("/student/new")
	public Student createNewStudent( String name, String email ) {
		
		Student stu = new Student();
		
		stu.setName(name);
		
		/* check if email is in use */
		if( studentRepository.findByEmail(email) == null) {
			stu.setEmail(email);
		} else {
			// email in use
			throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, "Email is already in use. " + email);
		}
		
		studentRepository.save(stu);
		
		System.out.println( stu.toString() );
		
		return stu;
	}
	
	@PostMapping("/student/hold")
	public Student hold( String email ) {
		
		Student stu = studentRepository.findByEmail( email );
		
		// Student found
		if( stu != null ) {
			
			// Status already set to "HOLD"
			if( stu.getStatusCode() != 0 ) {
				/*Student already on HOLD */
				throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, "Student status already on HOLD. " + stu.getName() + " " + email);
			}
			// Status not on HOLD
			else {
				stu.setStatus( "HOLD" );
				stu.setStatusCode(1);
				studentRepository.findByEmail( email );
				studentRepository.save( stu );
				return stu;
			}
		}
		
		// student not found.
		throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, "Student was not found. " + email);
	}
	
	@PostMapping("/student/release")
	public Student releaseHold( String email ) {
		
		Student stu = studentRepository.findByEmail( email );
		
		// Student found
		if( stu != null ) {
			
			if( stu.getStatusCode() == 0 ) {
				// Status not on "HOLD"
				throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, "Student status is not on HOLD. " + stu.getName() + " " + email);
			}
			// Status on HOLD
			else {
				stu.setStatus( null );
				stu.setStatusCode(0);
				studentRepository.findByEmail( email );
				studentRepository.save( stu );
				return stu;
			}
		}
		
		// student not found.
		throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, "Student was not found. " + email);
	}
	
}
