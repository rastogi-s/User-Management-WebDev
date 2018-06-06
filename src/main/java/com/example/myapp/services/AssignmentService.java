package com.example.myapp.services;

import java.util.ArrayList;
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

import com.example.myapp.model.Assignment;
import com.example.myapp.model.Topic;
import com.example.myapp.model.Widget;
import com.example.myapp.repositories.AssignmentRepository;
import com.example.myapp.repositories.TopicRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AssignmentService {

	@Autowired
	TopicRepository topicRepository;

	@Autowired
	AssignmentRepository assignmentRepository;

	@PostMapping("/api/topic/{topicId}/assignment")
	public Assignment createAssignment(@PathVariable("topicId") int topicId, @RequestBody Assignment assignment) {

		Optional<Topic> data = topicRepository.findById(topicId);

		if (data.isPresent()) {
			Topic topic = data.get();
			assignment.setTopic(topic);
			if (assignment.getWidgetType() == null || assignment.getWidgetType().equals(""))
				assignment.setWidgetType("Assignment");
			return assignmentRepository.save(assignment);
		}

		return null;

	}

	@GetMapping("/api/assignment")
	public Iterable<Assignment> findAllAssignments() {
		return assignmentRepository.findAll();
	}

	@GetMapping("/api/topic/{topicId}/assignment")
	public List<Assignment> findAllAssignmentsForTopic(@PathVariable("topicId") int topicId) {

		Optional<Topic> data = topicRepository.findById(topicId);

		if (data.isPresent()) {

			Topic topic = data.get();

			List<Widget> l = topic.getWidget();
			List<Assignment> assignList = new ArrayList<Assignment>();
			for (Widget w : l) {
				if (w.getWidgetType() != null && w.getWidgetType().equalsIgnoreCase("Assignment")) {
					assignList.add((Assignment) w);
				}
			}

			return assignList;
		}

		return null;
	}

	@DeleteMapping("/api/assignment/{assignmentId}")
	public void deleteAssignment(@PathVariable("assignmentId") int id) {

		assignmentRepository.deleteById(id);

	}

	@GetMapping("/api/assignment/{assignmentId}")
	public Assignment findAssignmentById(@PathVariable("assignmentId") int id) {
		Optional<Assignment> op = assignmentRepository.findById(id);
		Assignment assignment = null;
		if (op.isPresent())
			assignment = op.get();
		return assignment;
	}

	@PutMapping("/api/assignment/{assignmentId}")
	public Assignment updateAssignment(@PathVariable("assignmentId") int id, @RequestBody Assignment assignment) {
		Assignment temp = findAssignmentById(id);
		temp.set(assignment);
		return assignmentRepository.save(temp);
	}

}