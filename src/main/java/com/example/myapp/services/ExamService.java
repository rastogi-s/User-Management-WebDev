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
import com.example.myapp.model.Variable;
import com.example.myapp.model.Widget;
import com.example.myapp.repositories.BaseExamQuestionRepository;
import com.example.myapp.repositories.ChoicesRepository;
import com.example.myapp.repositories.EssayExamQuestionRepository;
import com.example.myapp.repositories.ExamRepository;
import com.example.myapp.repositories.FillInTheBlanksExamQuestionRepository;
import com.example.myapp.repositories.MultipleChoiceExamQuestionRepository;
import com.example.myapp.repositories.TopicRepository;
import com.example.myapp.repositories.TrueOrFalseExamQuestionRepository;
import com.example.myapp.repositories.VariableRepository;
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

	@Autowired
	VariableRepository variableRepository;

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
			@RequestBody String fillString) {

		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = null;
		try {
			node = mapper.readTree(fillString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FillInTheBlanksExamQuestion fillQuestion = mapper.convertValue(node.get("fill"),
				FillInTheBlanksExamQuestion.class);
		Variable vars[] = mapper.convertValue(node.get("vars"), Variable[].class);

		Optional<Exam> data = examRepository.findById(examId);

		if (data.isPresent()) {
			Exam exam = data.get();

			fillQuestion.setExam(exam);
			if (fillQuestion.getType() == null || fillQuestion.getType().equals(""))
				fillQuestion.setType("FillInTheBlanks");

			FillInTheBlanksExamQuestion savedFillQuestion = fillInTheBlanksExamRepository.save(fillQuestion);
			int id = savedFillQuestion.getId();
			List<Variable> l = new ArrayList<Variable>();
			for (Variable v : vars) {
				v.setFillInTheBlanksExamQuestion(savedFillQuestion);
				l.add(v);
			}
			variableRepository.saveAll(l);
			return fillInTheBlanksExamRepository.findById(id).get();

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

		Optional<EssayExamQuestion> temp = essayExamQuestionRepository.findById(questionId);
		if (temp.isPresent()) {
			int examId = temp.get().getExam().getId();
			essayExamQuestionRepository.deleteById(questionId);

			return createEssayExamQuestion(examId, essayQuestion);
		}
		return null;

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
			@RequestBody String blanksQuestion) {

		Optional<FillInTheBlanksExamQuestion> temp = fillInTheBlanksExamRepository.findById(questionId);
		if (temp.isPresent()) {
			int examId = temp.get().getExam().getId();

			fillInTheBlanksExamRepository.deleteById(questionId);

			return creatFillInTheBlanksExamQuestion(examId, blanksQuestion);
		}
		return null;

	}

	@PutMapping("/api/question/{questionId}/truefalse")
	public TrueOrFalseExamQuestion updateFillInTheBlanksExamQuestion(@PathVariable("questionId") int questionId,
			@RequestBody TrueOrFalseExamQuestion truefalseQuestion) {

		Optional<TrueOrFalseExamQuestion> temp = trueOrFalseExamQuestionRepository.findById(questionId);
		if (temp.isPresent()) {
			int examId = temp.get().getExam().getId();
			trueOrFalseExamQuestionRepository.deleteById(questionId);

			return createTrueFalseExamQuestion(examId, truefalseQuestion);
		}
		return null;

	}

	@DeleteMapping("/api/question/{questionId}/essay")
	public void deleteEssay(@PathVariable("questionId") int id) {

		essayExamQuestionRepository.deleteById(id);

	}

	@DeleteMapping("/api/question/{questionId}/blanks")
	public void deleteFillInTheBlanks(@PathVariable("questionId") int id) {

		fillInTheBlanksExamRepository.deleteById(id);

	}

	@DeleteMapping("/api/question/{questionId}/multi")
	public void deleteMultipleChoice(@PathVariable("questionId") int id) {

		multipleChoiceExamQuestionRepository.deleteById(id);

	}

	@DeleteMapping("/api/question/{questionId}/truefalse")
	public void deleteTrueFalse(@PathVariable("questionId") int id) {

		trueOrFalseExamQuestionRepository.deleteById(id);

	}
}
