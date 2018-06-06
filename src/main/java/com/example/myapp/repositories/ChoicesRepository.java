package com.example.myapp.repositories;
import org.springframework.data.repository.CrudRepository;
import com.example.myapp.model.Choices;

public interface ChoicesRepository extends CrudRepository<Choices, Integer> {

}
