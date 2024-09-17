package com.example.mca.labourPlatform.ServiceImplementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mca.labourPlatform.dao.ContactUsRepository;
import com.example.mca.labourPlatform.dto.ContactUsDto;
import com.example.mca.labourPlatform.model.ContactUs;
import com.example.mca.labourPlatform.service.ContactUsService;

@Service
public class ContactUsServiceImplementation implements ContactUsService {

	Logger logger = LoggerFactory.getLogger(ContactUsServiceImplementation.class);

	@Autowired
	private ContactUsRepository contactUsRepository;

	@Override
	public void saveContactDetails(ContactUsDto contactUsDto) {
		try {
			ContactUs contactUs = new ContactUs();
			BeanUtils.copyProperties(contactUsDto, contactUs);

			contactUsRepository.save(contactUs);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		}
	}

}
