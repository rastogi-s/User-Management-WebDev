package com.example.myapp.repositories;

import org.springframework.data.repository.CrudRepository;
import com.example.myapp.model.MultipleChoiceExamQuestion;

public interface MultipleChoiceExamQuestionRepository
	extends CrudRepository<MultipleChoiceExamQuestion, Integer>{

}
