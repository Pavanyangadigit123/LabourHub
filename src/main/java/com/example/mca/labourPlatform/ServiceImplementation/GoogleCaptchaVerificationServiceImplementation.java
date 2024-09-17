package com.example.mca.labourPlatform.ServiceImplementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.example.mca.labourPlatform.dto.GoogleRecaptchaResponse;
import com.example.mca.labourPlatform.service.GoogleCaptchaVerificationService;

import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

@Service
public class GoogleCaptchaVerificationServiceImplementation implements GoogleCaptchaVerificationService {

	Logger logger = LoggerFactory.getLogger(GoogleCaptchaVerificationServiceImplementation.class);

	@Override
	public boolean isvalidCaptcha(String token) {

		boolean valid = false;
		if (StringUtils.isNotBlank(token)) {

			try {
				String recaptchaUrl = "https://www.google.com/recaptcha/api/siteverify";
				String secret = "6LeuZEAqAAAAAKZNJYDTTjti_vxVMr_7jSs8T-D4";
				String url = recaptchaUrl + "?secret=" + secret + "&response=" + token;

				RestTemplate restTemplate = new RestTemplate();

				GoogleRecaptchaResponse googleResponse = restTemplate.getForObject(url, GoogleRecaptchaResponse.class);
				valid = googleResponse.isSuccess();
				System.out.println("");
			} catch (Exception e) {
				logger.error(e.getLocalizedMessage());
			}
		}
		return valid;
	}

}
