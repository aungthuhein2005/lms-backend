package com.lms.backend.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/media")
@CrossOrigin(origins = "http://localhost:3000")
public class MediaController {

	private static final String UPLOAD_URL = "uploads";

	
	@PostMapping("/upload")
	public ResponseEntity<?> uploadMedia(@RequestParam("file") MultipartFile file ){
		try {
			String filename = System.currentTimeMillis()+"_"+file.getOriginalFilename();
			Path filepaPath = Paths.get(UPLOAD_URL,filename);
			Files.createDirectories(filepaPath.getParent());
			Files.write(filepaPath, file.getBytes());
			
			String mediaURL = "/media/"+filename;
			return ResponseEntity.ok(Map.of(
					"mediaURL", mediaURL,
					"mediaFilename", filename,
					"mediaType", file.getContentType()
					));
		}catch(IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to upload");
		}
	}
	
}
