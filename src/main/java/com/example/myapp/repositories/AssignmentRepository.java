package com.example.myapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.myapp.model.Assignment;

public interface AssignmentRepository extends CrudRepository<Assignment, Integer> {

}
