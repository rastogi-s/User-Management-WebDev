package com.example.myapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.myapp.model.EssayExamQuestion;

public interface EssayExamQuestionRepository
	extends CrudRepository<EssayExamQuestion, Integer>{

}
