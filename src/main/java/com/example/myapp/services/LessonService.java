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
import com.example.myapp.model.Lesson;
import com.example.myapp.model.Module;
import com.example.myapp.repositories.CourseRepository;
import com.example.myapp.repositories.LessonRepository;
import com.example.myapp.repositories.ModuleRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class LessonService {

	@Autowired
	ModuleRepository moduleRepository;
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	LessonRepository lessonRepository;

	@PostMapping("/api/course/{courseId}/module/{moduleId}/lesson")
	public Lesson createLesson(@PathVariable("courseId") int courseId,@PathVariable("moduleId") int moduleId,@RequestBody Lesson lesson) {

		Optional<Course> data1 = courseRepository.findById(courseId);
		Optional<Module> data2 = moduleRepository.findById(moduleId);
		
		if(data1.isPresent() && data2.isPresent()) {
			Date date=new Date();
			Course course = data1.get();
			course.setModified(date);
			Module module = data2.get();
			lesson.setModule(module);
			module.setCourse(course);
			return lessonRepository.save(lesson);
		}
		return null;

	}

	@GetMapping("/api/lesson")
	public Iterable<Lesson> findAllLessons() {
		return lessonRepository.findAll();
	}
	
	@GetMapping("/api/course/{courseId}/module/{moduleId}/lesson")
	public Iterable<Lesson> findAllLessonsForModule(@PathVariable("courseId") int courseId,@PathVariable("moduleId") int moduleId) {
		Optional<Course> data1 = courseRepository.findById(courseId);
		Optional<Module> data2 = moduleRepository.findById(moduleId);
		if(data1.isPresent() && data2.isPresent()) {
			//Course course = data1.get();
			Module module = data2.get();
		
			return module.getLessons();
		}
		
		return null;
	}

	@DeleteMapping("/api/lesson/{lessonId}")
	public void deleteLesson(@PathVariable("lessonId") int id) {
		lessonRepository.deleteById(id);
	}

	@GetMapping("/api/lesson/{lessonId}")
	public Lesson findLessonById(@PathVariable("lessonId") int id) {
		Optional<Lesson> op = lessonRepository.findById(id);
		Lesson lesson = null;
		if (op.isPresent())
			lesson = op.get();
		return lesson;
	}
	
	@PutMapping("/api/lesson/{lessonId}")
	public Lesson updateLesson(@PathVariable("lessonId") int id, @RequestBody Lesson lesson) {
		Lesson temp = findLessonById(id);
		temp.set(lesson);
		return lessonRepository.save(temp);
	}

}
