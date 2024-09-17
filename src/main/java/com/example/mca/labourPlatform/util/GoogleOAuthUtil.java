package com.example.mca.labourPlatform.util;


	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.HttpEntity;
	import org.springframework.http.HttpHeaders;
	import org.springframework.http.HttpMethod;
	import org.springframework.http.MediaType;
	import org.springframework.http.ResponseEntity;
	import org.springframework.stereotype.Service;
	import org.springframework.util.LinkedMultiValueMap;
	import org.springframework.util.MultiValueMap;
	import org.springframework.web.client.RestTemplate;

	import com.nimbusds.jose.shaded.gson.Gson;
	import com.nimbusds.jose.shaded.gson.JsonObject;

	public class GoogleOAuthUtil {
		
		
		public static String getOauthAccessTokenGoogle(String code) {
			    RestTemplate restTemplate = new RestTemplate();
			    HttpHeaders httpHeaders = new HttpHeaders();
			    httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

			    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			    params.add("code", code);
			    params.add("redirect_uri", "http://localhost:9000/api/v1/user/signInWithGoogle");
			    params.add("client_id", "529061361842-2ihaop64m6ajmerlbraviu2o3o36qkm7.apps.googleusercontent.com");
			    params.add("client_secret", "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");
			    params.add("scope", "https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.profile");
			    params.add("scope", "https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email");
			    params.add("scope", "openid");
			    params.add("grant_type", "authorization_code");

			    HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, httpHeaders);

			    String url = "https://oauth2.googleapis.com/token";
			    String response = restTemplate.postForObject(url, requestEntity, String.class);
			    	    		
			    return response;
			}
			
			public static JsonObject getProfileDetailsGoogle(String accessToken) {
				RestTemplate restTemplate = new RestTemplate();
			    HttpHeaders httpHeaders = new HttpHeaders();
			    httpHeaders.setBearerAuth(accessToken);

			    HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);

			    String url = "https://www.googleapis.com/oauth2/v2/userinfo";
			    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
			    JsonObject jsonObject = new Gson().fromJson(response.getBody(), JsonObject.class);
			    return jsonObject;
			}


}
