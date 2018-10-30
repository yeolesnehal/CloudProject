package edu.sjsu.cloud.springbootstarter.controller;

//import java.util.List;

//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserFilesRepository extends CrudRepository<UserFiles, Integer>{

	void save(String filepath);

	//List<UserFiles> findAll();
}
	