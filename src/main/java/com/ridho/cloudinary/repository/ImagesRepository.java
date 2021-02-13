package com.ridho.cloudinary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ridho.cloudinary.model.Images;

@Repository
public interface ImagesRepository extends JpaRepository<Images, Integer>{
	
	List<Images> findByOrderById();
	
}
