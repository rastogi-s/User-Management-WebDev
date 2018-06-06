package com.example.myapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.myapp.model.TrueOrFalseExamQuestion;

public interface TrueOrFalseExamQuestionRepository extends CrudRepository<TrueOrFalseExamQuestion, Integer> {

}
