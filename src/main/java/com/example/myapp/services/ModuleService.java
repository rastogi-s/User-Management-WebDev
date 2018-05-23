package com.example.myapp.services;

import java.util.Date;
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
import com.example.myapp.model.Module;
import com.example.myapp.repositories.CourseRepository;
import com.example.myapp.repositories.ModuleRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ModuleService {

	@Autowired
	ModuleRepository moduleRepository;
	
	@Autowired
	CourseRepository courseRepository;

	@PostMapping("/api/course/{courseId}/module")
	public Module createModule(@PathVariable("courseId") int courseId,@RequestBody Module module) {

		Optional<Course> data = courseRepository.findById(courseId);
		if(data.isPresent()) {
			Course course = data.get();
			Date date=new Date();
			course.setModified(date);
			module.setCourse(course);
			return moduleRepository.save(module);
		}
		return null;

	}

	@GetMapping("/api/module")
	public Iterable<Module> findAllModules() {
		return moduleRepository.findAll();
	}
	
	@GetMapping("/api/course/{courseId}/module")
	public Iterable<Module> findAllModulesForCourse(@PathVariable("courseId") int courseId) {
		Optional<Course> data = courseRepository.findById(courseId);
		if(data.isPresent()) {
			Course course = data.get();
		
			return course.getModules();
		}
		
		return null;
	}

	@DeleteMapping("/api/module/{moduleId}")
	public void deleteModule(@PathVariable("moduleId") int id) {
		moduleRepository.deleteById(id);
	}

	@GetMapping("/api/module/{moduleId}")
	public Module findModuleById(@PathVariable("moduleId") int id) {
		Optional<Module> op = moduleRepository.findById(id);
		Module module = null;
		if (op.isPresent())
			module = op.get();
		return module;
	}
	
	@PutMapping("/api/module/{moduleId}")
	public Module updateModule(@PathVariable("moduleId") int id, @RequestBody Module module) {
		Module temp = findModuleById(id);
		temp.set(module);
		return moduleRepository.save(temp);
	}

}
