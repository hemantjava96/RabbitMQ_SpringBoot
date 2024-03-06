package com.hk.rabbitmq.entity;

import java.io.Serializable;

public class File implements Serializable {
	private int fileID;

	private String fileType;

	public File(int fileID, String fileType) {
		super();
		this.fileID = fileID;
		this.fileType = fileType;
	}

	public File() {
	}

	// getter and setters
	public int getFileID() {
		return fileID;
	}

	public void setFileID(int fileID) {
		this.fileID = fileID;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

}
