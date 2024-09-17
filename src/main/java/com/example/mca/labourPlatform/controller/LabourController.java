package com.example.mca.labourPlatform.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mca.labourPlatform.ServiceImplementation.LabourServiceImplementation;
import com.example.mca.labourPlatform.dto.LabourDto;
import com.example.mca.labourPlatform.dto.UserDto;
import com.example.mca.labourPlatform.exception.LabourHubException;
import com.example.mca.labourPlatform.model.Labour;
import com.example.mca.labourPlatform.rest.LabourHubResponse;
import com.example.mca.labourPlatform.service.LabourService;
import com.example.mca.labourPlatform.service.UserService;

@RestController
@RequestMapping("/labour")
public class LabourController {

	@Autowired
	private LabourService labourService;

	@Autowired
	private UserService userService;

	Logger logger = LoggerFactory.getLogger(LabourController.class);
	
	@GetMapping
	public LabourHubResponse getLabours() {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			List<LabourDto> listOfDto = labourService.getLabours();
			status = HttpStatus.OK;
			return new LabourHubResponse(listOfDto, status);
		} catch (LabourHubException e) {
			message = "Failed to retrieve Labour data; " + e.getMessage();
		} catch (Exception e) {
			message = "Internal server error" + e.getMessage();
		}

		return new LabourHubResponse(message, status);
	}
	
	@GetMapping("/filter")
	  public LabourHubResponse getLaboursByFilters(
	            @RequestParam(required = false) String skillName,
	            @RequestParam(required = false) String state,
	            @RequestParam(required = false) String city,
	            @RequestParam(required = false) String area) {

	        HttpStatus status = HttpStatus.BAD_REQUEST;
	        String message = "";
	        try {
            List<LabourDto> listOfDto = labourService.getLaboursByFilters(skillName, state, city,area);
	            status = HttpStatus.OK;
	            return new LabourHubResponse(listOfDto, status);
	        } catch (LabourHubException e) {
	            message = "Failed to retrieve Labour data; " + e.getMessage();
	        } catch (Exception e) {
	            message = "Internal server error; " + e.getMessage();
	        }

	        return new LabourHubResponse(message, status);
	    }
	
//	@GetMapping("/filter")
//    public LabourHubResponse getLabours(
//            @RequestParam(required = false) String area,
//            @RequestParam(required = false) String city,
//            @RequestParam(required = false) String state,
//            @RequestParam(required = false) String country,
//            @RequestParam(required = false) String zipCode) {
//            	HttpStatus status = HttpStatus.BAD_REQUEST;
//        		String message = "";
//        		try {
//        			List<LabourDto> listOfDto = labourService.findLaboursByFilter(area, city, state, country, zipCode);
//        			status = HttpStatus.OK;
//        			return new LabourHubResponse(listOfDto, status);
//        		} catch (LabourHubException e) {
//        			message = "Failed to retrieve Labour data; " + e.getMessage();
//        		} catch (Exception e) {
//        			message = "Internal server error" + e.getMessage();
//        		}
//
//        		return new LabourHubResponse(message, status);
//       
//    }

	@PostMapping("/signUp")
	public LabourHubResponse createLabour(@RequestBody LabourDto labourDto) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			message= labourService.createLabour(labourDto);
			//if(newDto!=null) {
			status = HttpStatus.OK;
			//return new LabourHubResponse(newDto, status);
			//}
			
			logger.info("labour created succesfully");
		} catch (LabourHubException e) {
			message = "Error while creating Labour; " + e.getLocalizedMessage();
			logger.error("failed to create labour");
		} catch (Exception e) {
			message = "Internal Server Error." + e.getMessage();
		}

		return new LabourHubResponse(message, status);

	}

	@PutMapping("/labour-id/{id}")
	public LabourHubResponse updateLabour(@PathVariable(name = "id") Integer id, @RequestBody Labour labour) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			message= labourService.updateLabour(id, labour);
			status = HttpStatus.OK;
			
		} catch (LabourHubException exception) {
			message = "Failed to Update User !!" ;
		} catch (Exception exception) {
			message = "Internal Server Error !!" ;
		}
		return new LabourHubResponse(message, status);
	}

	@DeleteMapping("/labour-id/{id}")
	public LabourHubResponse deleteLabour(@PathVariable(name = "id") Integer id) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			message = labourService.deleteLabour(id);
			status = HttpStatus.OK;
		} catch (LabourHubException exception) {
			message = "Failed to delete User !!" + exception.getMessage();
		} catch (Exception exception) {
			message = "Internal Server Error !!" + exception.getMessage();
		}
		return new LabourHubResponse(message, status);

	}
}
