package com.ridho.cloudinary.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ridho.cloudinary.exception.Message;
import com.ridho.cloudinary.model.Images;
import com.ridho.cloudinary.service.CloudinaryService;
import com.ridho.cloudinary.service.ImagesService;

@RestController
@RequestMapping("/cloudinary")
@CrossOrigin
public class MainController {

	@Autowired
	CloudinaryService cloudinaryService;
	
	@Autowired
	ImagesService imagesService;
	
	@GetMapping("/list")
	public ResponseEntity<List<Images>> list (){
		List<Images> list = imagesService.list();
		return new ResponseEntity<List<Images>>(list, HttpStatus.OK);
	}
	
	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestParam MultipartFile multipartFile) throws IOException{
		Map result; 

		BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
		
		if (bi == null) {
			return new ResponseEntity(new Message("image not valid"), HttpStatus.BAD_REQUEST);
		} else {	
			result = cloudinaryService.upload(multipartFile);
		}
		
		Images images = new Images(
							(String) result.get("original_filename"),
							(String) result.get("url"),
							(String) result.get("public_id")
						);
		imagesService.save(images);
		
		return new ResponseEntity(new Message("Images uploaded successfully!"), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) throws IOException{
		if (imagesService.exists(id)) {
			return new ResponseEntity(new Message("images with id: " + id + " doesn't exist!"), HttpStatus.BAD_REQUEST);
		}
		Images images = imagesService.getOne(id).get();
		Map result = cloudinaryService.delete(images.getImagesId());
		imagesService.delete(id);
		return new ResponseEntity<Map>(result, HttpStatus.OK);
	}
	
}
