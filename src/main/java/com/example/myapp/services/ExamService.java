package com.example.myapp.services;

import java.io.IOException;
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

import com.example.myapp.model.BaseExamQuestion;
import com.example.myapp.model.Choices;
import com.example.myapp.model.EssayExamQuestion;
import com.example.myapp.model.Exam;
import com.example.myapp.model.FillInTheBlanksExamQuestion;
import com.example.myapp.model.MultipleChoiceExamQuestion;
import com.example.myapp.model.Topic;
import com.example.myapp.model.TrueOrFalseExamQuestion;
import com.example.myapp.model.Widget;
import com.example.myapp.repositories.BaseExamQuestionRepository;
import com.example.myapp.repositories.ChoicesRepository;
import com.example.myapp.repositories.EssayExamQuestionRepository;
import com.example.myapp.repositories.ExamRepository;
import com.example.myapp.repositories.FillInTheBlanksExamQuestionRepository;
import com.example.myapp.repositories.MultipleChoiceExamQuestionRepository;
import com.example.myapp.repositories.TopicRepository;
import com.example.myapp.repositories.TrueOrFalseExamQuestionRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ExamService {

	@Autowired
	TopicRepository topicRepository;

	@Autowired
	ExamRepository examRepository;

	@Autowired
	BaseExamQuestionRepository baseExamRepository;

	@Autowired
	FillInTheBlanksExamQuestionRepository fillInTheBlanksExamRepository;

	@Autowired
	MultipleChoiceExamQuestionRepository multipleChoiceExamQuestionRepository;

	@Autowired
	EssayExamQuestionRepository essayExamQuestionRepository;

	@Autowired
	TrueOrFalseExamQuestionRepository trueOrFalseExamQuestionRepository;

	@Autowired
	ChoicesRepository choicesRepository;

	@PostMapping("/api/topic/{topicId}/exam")
	public Exam createExam(@PathVariable("topicId") int topicId, @RequestBody Exam exam) {

		Optional<Topic> data = topicRepository.findById(topicId);

		if (data.isPresent()) {
			Topic topic = data.get();
			exam.setTopic(topic);
			if (exam.getWidgetType() == null || exam.getWidgetType().equals(""))
				exam.setWidgetType("Exam");
			return examRepository.save(exam);
		}

		return null;

	}

	@GetMapping("/api/exam")
	public Iterable<Exam> findAllExams() {
		return examRepository.findAll();
	}

	@GetMapping("/api/topic/{topicId}/exam")
	public List<Exam> findAllExamsForTopic(@PathVariable("topicId") int topicId) {

		Optional<Topic> data = topicRepository.findById(topicId);

		if (data.isPresent()) {

			Topic topic = data.get();

			List<Widget> l = topic.getWidget();
			List<Exam> assignList = new ArrayList<Exam>();
			for (Widget w : l) {
				if (w.getWidgetType() != null && w.getWidgetType().equalsIgnoreCase("Exam")) {
					assignList.add((Exam) w);
				}
			}

			return assignList;
		}

		return null;
	}

	@DeleteMapping("/api/exam/{examId}")
	public void deleteExam(@PathVariable("examId") int id) {

		examRepository.deleteById(id);

	}

	@GetMapping("/api/exam/{examId}")
	public Exam findExamById(@PathVariable("examId") int id) {
		Optional<Exam> op = examRepository.findById(id);
		Exam exam = null;
		if (op.isPresent())
			exam = op.get();
		return exam;
	}

	@PutMapping("/api/exam/{examId}")
	public Exam updateExam(@PathVariable("examId") int id, @RequestBody Exam exam) {
		Exam temp = findExamById(id);
		temp.set(exam);
		return examRepository.save(temp);
	}

	@PostMapping("/api/exam/{examId}/essay")
	public EssayExamQuestion createEssayExamQuestion(@PathVariable("examId") int examId,
			@RequestBody EssayExamQuestion essayQuestion) {

		Optional<Exam> data = examRepository.findById(examId);

		if (data.isPresent()) {
			Exam exam = data.get();
			essayQuestion.setExam(exam);
			if (essayQuestion.getType() == null || essayQuestion.getType().equals(""))
				essayQuestion.setType("Essay");

			return essayExamQuestionRepository.save(essayQuestion);
		}

		return null;

	}

	// @PostMapping("/api/exam/{examId}/choice")
	// public MultipleChoiceExamQuestion
	// createMultiChoiceExamQuestion(@PathVariable("examId") int examId,
	// @RequestBody MultipleChoiceExamQuestion multiQuestion) {
	//
	// Optional<Exam> data = examRepository.findById(examId);
	//
	// if (data.isPresent()) {
	// Exam exam = data.get();
	// multiQuestion.setExam(exam);
	// if (multiQuestion.getType() == null || multiQuestion.getType().equals(""))
	// multiQuestion.setType("MultipleChoice");
	//
	// return multipleChoiceExamQuestionRepository.save(multiQuestion);
	// }
	//
	// return null;
	//
	// }

	@PostMapping("/api/exam/{examId}/choice")
	public MultipleChoiceExamQuestion createMultiChoiceExamQuestion(@PathVariable("examId") int examId,
			@RequestBody String multi) {

		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = null;
		try {
			node = mapper.readTree(multi);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MultipleChoiceExamQuestion multiQuestion = mapper.convertValue(node.get("multi"),
				MultipleChoiceExamQuestion.class);
		Choices choices[] = mapper.convertValue(node.get("choices"), Choices[].class);

		Optional<Exam> data = examRepository.findById(examId);

		if (data.isPresent()) {
			Exam exam = data.get();
			multiQuestion.setExam(exam);
			if (multiQuestion.getType() == null || multiQuestion.getType().equals(""))
				multiQuestion.setType("MultipleChoice");

			MultipleChoiceExamQuestion savedMultiQuestion = multipleChoiceExamQuestionRepository.save(multiQuestion);
			int id = savedMultiQuestion.getId();
			List<Choices> l = new ArrayList<Choices>();
			for (Choices c : choices) {
				c.setMultipleChoiceExamQuestion(savedMultiQuestion);
				l.add(c);
			}
			choicesRepository.saveAll(l);
			return multipleChoiceExamQuestionRepository.findById(id).get();

		}

		return null;

	}

	@PostMapping("/api/exam/{examId}/blanks")
	public FillInTheBlanksExamQuestion creatFillInTheBlanksExamQuestion(@PathVariable("examId") int examId,
			@RequestBody FillInTheBlanksExamQuestion fillQuestion) {

		Optional<Exam> data = examRepository.findById(examId);

		if (data.isPresent()) {
			Exam exam = data.get();
			fillQuestion.setExam(exam);
			if (fillQuestion.getType() == null || fillQuestion.getType().equals(""))
				fillQuestion.setType("FillInTheBlanks");

			return fillInTheBlanksExamRepository.save(fillQuestion);
		}

		return null;

	}

	@PostMapping("/api/exam/{examId}/truefalse")
	public TrueOrFalseExamQuestion createTrueFalseExamQuestion(@PathVariable("examId") int examId,
			@RequestBody TrueOrFalseExamQuestion truefalseQuestion) {

		Optional<Exam> data = examRepository.findById(examId);

		if (data.isPresent()) {
			Exam exam = data.get();
			truefalseQuestion.setExam(exam);
			if (truefalseQuestion.getType() == null || truefalseQuestion.getType().equals(""))
				truefalseQuestion.setType("TrueFalse");

			return trueOrFalseExamQuestionRepository.save(truefalseQuestion);
		}

		return null;

	}

	@GetMapping("/api/exam/{examId}/question")
	public List<BaseExamQuestion> findAllQuestionsForExam(@PathVariable("examId") int examId) {
		Optional<Exam> optionalExam = examRepository.findById(examId);
		if (optionalExam.isPresent()) {
			Exam exam = optionalExam.get();
			List<BaseExamQuestion> questions = exam.getQuestions();
			// int count = questions.size();
			return questions;
		}
		return null;
	}

	@PutMapping("/api/question/{questionId}/essay")
	public EssayExamQuestion updateEssayExamQuestion(@PathVariable("questionId") int questionId,
			@RequestBody EssayExamQuestion essayQuestion) {

		// EssayExamQuestion
		// essayQuestion=essayExamQuestionRepository.findById(questionId);
		// essayExamQuestionRepository.deleteById(questionId);
		return essayExamQuestionRepository.save(essayQuestion);

	}

	@PutMapping("/api/question/{questionId}/multi")
	public MultipleChoiceExamQuestion updateMultiExamQuestion(@PathVariable("questionId") int questionId,
			@RequestBody String multiQuestion) {

		Optional<MultipleChoiceExamQuestion> temp = multipleChoiceExamQuestionRepository.findById(questionId);
		if (temp.isPresent()) {
			int examId = temp.get().getExam().getId();
			multipleChoiceExamQuestionRepository.deleteById(questionId);

			return createMultiChoiceExamQuestion(examId, multiQuestion);
		}
		return null;

	}

	@PutMapping("/api/question/{questionId}/blanks")
	public FillInTheBlanksExamQuestion updateFillInTheBlanksExamQuestion(@PathVariable("questionId") int questionId,
			@RequestBody FillInTheBlanksExamQuestion blanksQuestion) {

		// fillInTheBlanksExamRepository.deleteById(questionId);
		return fillInTheBlanksExamRepository.save(blanksQuestion);

	}

	@PutMapping("/api/question/{questionId}/truefalse")
	public TrueOrFalseExamQuestion updateFillInTheBlanksExamQuestion(@PathVariable("questionId") int questionId,
			@RequestBody TrueOrFalseExamQuestion truefalseQuestion) {

		// trueOrFalseExamQuestionRepository.deleteById(questionId);
		return trueOrFalseExamQuestionRepository.save(truefalseQuestion);

	}
}
