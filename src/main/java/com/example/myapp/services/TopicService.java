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
import com.example.myapp.model.Topic;
import com.example.myapp.repositories.CourseRepository;
import com.example.myapp.repositories.LessonRepository;
import com.example.myapp.repositories.ModuleRepository;
import com.example.myapp.repositories.TopicRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class TopicService {

	@Autowired
	ModuleRepository moduleRepository;

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	LessonRepository lessonRepository;

	@Autowired
	TopicRepository topicRepository;

	@PostMapping("/api/course/{courseId}/module/{moduleId}/lesson/{lessonId}/topic")
	public Topic createTopic(@PathVariable("courseId") int courseId, @PathVariable("moduleId") int moduleId,
			@PathVariable("lessonId") int lessonId, @RequestBody Topic topic) {

		Optional<Course> data1 = courseRepository.findById(courseId);
		Optional<Module> data2 = moduleRepository.findById(moduleId);
		Optional<Lesson> data3 = lessonRepository.findById(lessonId);

		if (data1.isPresent() && data2.isPresent() && data3.isPresent()) {
			Date date=new Date();
			Course course = data1.get();
			course.setModified(date);
			Module module = data2.get();
			Lesson lesson = data3.get();
			topic.setLesson(lesson);
			lesson.setModule(module);
			module.setCourse(course);
			return topicRepository.save(topic);
		}
		return null;

	}

	@GetMapping("/api/topic")
	public Iterable<Topic> findAllTopics() {
		return topicRepository.findAll();
	}

	@GetMapping("/api/course/{courseId}/module/{moduleId}/lesson/{lessonId}/topic")
	public Iterable<Topic> findAllTopicsForLesson(@PathVariable("courseId") int courseId,
			@PathVariable("moduleId") int moduleId, @PathVariable("lessonId") int lessonId) {
		Optional<Course> data1 = courseRepository.findById(courseId);
		Optional<Module> data2 = moduleRepository.findById(moduleId);
		Optional<Lesson> data3 = lessonRepository.findById(lessonId);

		if (data1.isPresent() && data2.isPresent() && data3.isPresent()) {
			// Course course = data1.get();
			Lesson lesson = data3.get();

			return lesson.getTopics();
		}

		return null;
	}

	@DeleteMapping("/api/topic/{topicId}")
	public void deleteTopic(@PathVariable("topicId") int id) {
		topicRepository.deleteById(id);
	}

	@GetMapping("/api/topic/{topicId}")
	public Topic findTopicById(@PathVariable("topicId") int id) {
		Optional<Topic> op = topicRepository.findById(id);
		Topic topic = null;
		if (op.isPresent())
			topic = op.get();
		return topic;
	}

	@PutMapping("/api/topic/{topicId}")
	public Topic updateModule(@PathVariable("topicId") int id, @RequestBody Topic topic) {
		Topic temp = findTopicById(id);
		temp.set(topic);
		return topicRepository.save(temp);
	}

}
