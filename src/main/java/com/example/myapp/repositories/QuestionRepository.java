package com.example.myapp.repositories;

import org.springframework.data.repository.CrudRepository;
import com.example.myapp.model.BaseExamQuestion;

public interface QuestionRepository extends CrudRepository<BaseExamQuestion, Integer> {

}
