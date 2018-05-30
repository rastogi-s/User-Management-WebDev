package com.example.myapp.services;

import java.util.Date;
import java.util.List;
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
import com.example.myapp.model.Widget;
import com.example.myapp.repositories.CourseRepository;
import com.example.myapp.repositories.LessonRepository;
import com.example.myapp.repositories.ModuleRepository;
import com.example.myapp.repositories.TopicRepository;
import com.example.myapp.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class WidgetService {

	@Autowired
	ModuleRepository moduleRepository;

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	LessonRepository lessonRepository;

	@Autowired
	TopicRepository  topicRepository;
	
	@Autowired
	WidgetRepository widgetRepository;

	@PostMapping("/api/course/{courseId}/module/{moduleId}/lesson/{lessonId}/topic/{topicId}/widget")
	public Iterable<Widget> createWidget(@PathVariable("courseId") int courseId, @PathVariable("moduleId") int moduleId,
			@PathVariable("lessonId") int lessonId, @PathVariable("topicId") int topicId,@RequestBody List<Widget> widgets) {

		Optional<Course> data1 = courseRepository.findById(courseId);
		Optional<Module> data2 = moduleRepository.findById(moduleId);
		Optional<Lesson> data3 = lessonRepository.findById(lessonId);
		Optional<Topic>  data4 = topicRepository.findById(topicId);
		
		
		
		if (data1.isPresent() && data2.isPresent() && data3.isPresent()) {
			Date date=new Date();
			Course course = data1.get();
			course.setModified(date);
			Module module = data2.get();
			Lesson lesson = data3.get();
			Topic topic = data4.get();
			List<Widget> l=topic.getWidget();
			l.clear();
			topic.setLesson(lesson);
			lesson.setModule(module);
			module.setCourse(course);
			for(Widget w:widgets)
				w.setTopic(topic);
			
			
			return widgetRepository.saveAll(widgets);
		}
		
		return null;

	}

	@GetMapping("/api/widget")
	public Iterable<Widget> findAllWidgets() {
		return widgetRepository.findAll();
	}
	
	
	@GetMapping("/api/course/{courseId}/module/{moduleId}/lesson/{lessonId}/topic/{topicId}/widget")
	public Iterable<Widget> findAllWidgetsForTopic(@PathVariable("courseId") int courseId, @PathVariable("moduleId") int moduleId,
			@PathVariable("lessonId") int lessonId, @PathVariable("topicId") int topicId){
		Optional<Course> data1 = courseRepository.findById(courseId);
		Optional<Module> data2 = moduleRepository.findById(moduleId);
		Optional<Lesson> data3 = lessonRepository.findById(lessonId);
		Optional<Topic>  data4 = topicRepository.findById(topicId);

		if (data1.isPresent() && data2.isPresent() && data3.isPresent() && data4.isPresent()) {
			
			Topic topic=data4.get();

			return topic.getWidget();
		}

		return null;
	}
		

	@DeleteMapping("/api/widget/{widgetId}")
	public void deleteWidget(@PathVariable("widgetId") int id) {
		
		widgetRepository.deleteById(id);
		
	}
	
	
	@GetMapping("/api/widget/{widgetId}")
	public Widget findWidgetById(@PathVariable("widgetId") int id) {
		Optional<Widget> op = widgetRepository.findById(id);
		Widget widget = null;
		if (op.isPresent())
			widget = op.get();
		return widget;
	}
	

	@PutMapping("/api/widget{widgetId}")
	public Widget updateWidget(@PathVariable("widgetId") int id, @RequestBody Widget widget) {
		Widget temp = findWidgetById(id);
		temp.set(widget);
		return widgetRepository.save(temp);
	}
	
}