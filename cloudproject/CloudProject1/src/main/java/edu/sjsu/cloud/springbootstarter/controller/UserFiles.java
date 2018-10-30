package edu.sjsu.cloud.springbootstarter.controller;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="usersfiles")
public class UserFiles {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	private String name;
	private String Description;
	private String fileSize;
	private Timestamp uploadTime;
	private Timestamp updatedTime;
	private String filePath;
	
	
	public UserFiles() {
		
		
	}
	
	public UserFiles(int userId, String name, String Description, String fileSize, Timestamp uploadTime,
			Timestamp updatedTime, String filePath) {
		super();
		this.userId = userId;
		this.name = name;
		this.Description = Description;
		this.fileSize = fileSize;
		this.uploadTime = uploadTime;
		this.updatedTime = updatedTime;
		this.filePath = filePath;
	}



	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String Description) {
		this.Description = Description;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public Timestamp getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Timestamp uploadTime) {
		this.uploadTime = uploadTime;
	}

	public Timestamp getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getFilepath() {
		return filePath;
	}

	public void setFilepath(String filePath) {
		this.filePath = filePath;
	}
	
}
