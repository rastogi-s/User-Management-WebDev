package com.example.myapp.repositories;
import org.springframework.data.repository.CrudRepository;

import com.example.myapp.model.Variable;

public interface VariableRepository extends CrudRepository<Variable, Integer> {

}
