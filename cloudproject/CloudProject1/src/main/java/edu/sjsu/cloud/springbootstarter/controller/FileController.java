package edu.sjsu.cloud.springbootstarter.controller;

import java.io.IOException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {

//	private static final MultipartFile MultipartFile = null;

	@Autowired
	private FileService uploadService;
	
	@Autowired
	UserFilesRepository userFilesRepository;
	
	
	@RequestMapping(value="/upload", method = RequestMethod.POST)
	public String uploadFile(@RequestPart(value = "file") MultipartFile file, Model model) throws IOException {
		 uploadService.uploadFile(file, model);
		 return "filelist";
	}
	
	
	@RequestMapping(value="/load", method = RequestMethod.POST)
	public List<UserFiles> persist(@RequestBody final UserFiles usersfiles){
		userFilesRepository.save(usersfiles);
		
		return (List<UserFiles>) userFilesRepository.findAll() ;
			
	}
	
	
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public String delete() {
		
		uploadService.deleteFiles();
		return "filelist";
			
	}
		
	@RequestMapping(value="/listfiles", method = RequestMethod.POST)
	public void listS3objects() throws IOException {
		String fileUrl = null;
		Model model = null;
		uploadService.listUploadedFiles(fileUrl, model);

	}
		
}

	
/*@GetMapping(path="/add") // Map ONLY GET Requests
public @ResponseBody String addNewUser (@RequestParam String name
		, @RequestParam String email) {
	// @ResponseBody means the returned String is the response, not a view name
	// @RequestParam means it is a parameter from the GET or POST request
	
	User n = new User();
	n.setName(name);
	n.setEmail(email);
	userRepository.save(n);
	return "Saved";
}

@GetMapping(path="/all")
public @ResponseBody Iterable<User> getAllUsers() {
	// This returns a JSON or XML with the users
	return userRepository.findAll();
}*/

