package com.example.myapp.repositories;
import org.springframework.data.repository.CrudRepository;
import com.example.myapp.model.Module;

public interface ModuleRepository extends CrudRepository<Module, Integer>{
	
	
}
