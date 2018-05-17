package com.example.myapp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.myapp.model.Course;
import com.example.myapp.repositories.CourseRepository;

@RestController
@CrossOrigin()
public class CourseService {

	@Autowired
	CourseRepository courseRepository;

	@PostMapping("/api/course")
	public Course createUser(@RequestBody Course course) {

		return courseRepository.save(course);

	}

	@GetMapping("/api/course")
	public Iterable<Course> findAllCourses() {
		return courseRepository.findAll();
	}

	@DeleteMapping("/api/course/{cousrseId}")
	public void deleteCourse(@PathVariable("cousrseId") int id) {
		courseRepository.deleteById(id);
	}

	@GetMapping("/api/course/{courseId}")
	public Course findCourseById(@PathVariable("courseId") int id) {
		Optional<Course> op = courseRepository.findById(id);
		Course course = null;
		if (op.isPresent())
			course = op.get();
		return course;
	}
	
	@PutMapping("/api/course/{courseId}")
	public Course updateUser(@PathVariable("courseId") int id, @RequestBody Course course) {
		Course temp = findCourseById(id);
		temp.set(course);
		return courseRepository.save(temp);
	}

}
