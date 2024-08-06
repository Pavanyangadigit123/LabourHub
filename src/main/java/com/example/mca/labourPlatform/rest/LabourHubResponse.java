package com.example.mca.labourPlatform.rest;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class LabourHubResponse extends ResponseEntity<Object> {
	
	public LabourHubResponse(Object body,HttpStatusCode status)
	{
		super(body,status);
	}

}
