package com.example.myapp.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.myapp.model.User;
import com.example.myapp.model.Widget;

public interface WidgetRepository extends CrudRepository<Widget, Integer> {

	
}
