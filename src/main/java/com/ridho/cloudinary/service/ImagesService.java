package com.ridho.cloudinary.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ridho.cloudinary.model.Images;
import com.ridho.cloudinary.repository.ImagesRepository;

@Service
@Transactional
public class ImagesService {

	@Autowired
	ImagesRepository imagesRepository;
	
	public List<Images> list(){
		return imagesRepository.findByOrderById();
	}
	
	public Optional<Images> getOne(Integer id){
		return imagesRepository.findById(id);
	}
	
	public void save(Images images) {
		imagesRepository.save(images);
	}
	
	public void delete(Integer id) {
		imagesRepository.deleteById(id);
	}
	
	public boolean exists(Integer id) {
		return imagesRepository.existsById(id);
	}
	
}
