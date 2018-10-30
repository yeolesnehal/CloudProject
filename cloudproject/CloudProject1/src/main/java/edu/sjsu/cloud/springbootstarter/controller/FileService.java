package edu.sjsu.cloud.springbootstarter.controller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;



import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;

@Service
public class FileService {
	
	@Autowired
	private AmazonS3 s3client;
	
	@Value("${aws.s3.bucketName}")
	private String bucketName;
	
	@Value("${cloud.aws.cloudfrontdownloadURL}")
	private String cloudfrontdownloadURL;
	
	
	@Autowired 
	private Environment env;
	 
	@Autowired
	UserFilesRepository userFilesRepository;
	
	
	public String uploadFile(MultipartFile multipartFile, Model model) {

	    //String fileUrl = "";
	    try {
	        File file = convertMultiPartToFile(multipartFile);
	        String fileName = generateFileName(multipartFile);
	        //String downloadURL="";
	       // fileUrl = env.getProperty("cloud.aws.endpointUrl") + "/" + env.getProperty("aws.s3.bucketName") + "/" + fileName;
	        uploadFileTos3bucket(fileName, file);
	        file.delete();
	        
	        ObjectListing ol = s3client.listObjects(env.getProperty("aws.s3.bucketName"));
			List<S3ObjectSummary> objects = ol.getObjectSummaries();
			
			model.addAttribute("Objectlist", objects); 
			model.addAttribute("cloudfrontdownloadURL", cloudfrontdownloadURL);
		    model.addAttribute("Objectlist", objects);
	     
	    } catch (Exception e) {
	       e.printStackTrace();
	       model.addAttribute("Message", "Error uploading file, please try again !!!");
	    }
	    return "/welcome";
	    
	}
	
	private String generateFileName(MultipartFile multiPart) {
	    return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
	}
	
	private File convertMultiPartToFile(MultipartFile file) throws IOException {
	    File convFile = new File(file.getOriginalFilename());
	    FileOutputStream fos = new FileOutputStream(convFile);
	    fos.write(file.getBytes());
	    fos.close();
	    return convFile;
	}
	
	private void uploadFileTos3bucket(String fileName, File file) {
	    s3client.putObject(new PutObjectRequest(env.getProperty("aws.s3.bucketName"), fileName, file)
	            .withCannedAcl(CannedAccessControlList.PublicRead));
	}	

	public List<UserFiles> getAll() {
		System.out.println("In getAll method....");
		return (List<UserFiles>) userFilesRepository.findAll();
	
	}
	
	
	public String deleteFiles() {
				
		try {
			
			s3client.deleteObject(env.getProperty("aws.s3.bucketName"), "1540585795742-BucketReview.png");
					
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return "filelist";		 
	}
	
	
	@ModelAttribute("objectlist")
	public String listUploadedFiles(String fileUrl, Model model) {
			
			String cloudfrontdownloadURL=""; 
			List<String> fileNames = new ArrayList<String>();
			List<String> UserName = new ArrayList<String>();
			List<Date> LastModified = new ArrayList<Date>();
			List<String> Description = new ArrayList<String>();
			ObjectListing ol = s3client.listObjects(env.getProperty("aws.s3.bucketName"));
			List<S3ObjectSummary> objects = ol.getObjectSummaries();
			cloudfrontdownloadURL = cloudfrontdownloadURL + "/";
		    model.addAttribute("Objectlist", objects);
		    model.addAttribute("cloudfrontdownloadURL", cloudfrontdownloadURL);
		    
			for(S3ObjectSummary os: objects) {
				if (!os.getKey().endsWith("/")) {
					os.getBucketName();
					model.addAttribute("cloudfrontdownloadURL", cloudfrontdownloadURL);
	                System.out.println("UserName" + cloudfrontdownloadURL);
	       
	               
	                }
			
			
			}
			  System.out.println("Final" + fileNames);
		        System.out.println("UserName" + UserName);
		        System.out.println("Description" + Description);
		        System.out.println("LastModified" + LastModified);
			return "filelist";
		
	}

	

}
