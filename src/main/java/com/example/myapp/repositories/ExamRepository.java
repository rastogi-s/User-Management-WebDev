package com.example.myapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.myapp.model.Exam;

public interface ExamRepository extends CrudRepository<Exam, Integer> {

}
