package com.microservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WelcomeRestController {

	@Autowired
	private GreetClient greetClient;

	@GetMapping("/welcome")
	public WelcomeResponse getWelcomeMsg() {
		String welcomeMsg = "Welcome to My IT..!!";
//Inter-Service Communication
		String greetMsg = greetClient.invokeGreetApi();
//External Service Communication
		RestTemplate rt = new RestTemplate();
		String petEndPoint = "http://localhost:5252/allPet";
		// "https://fbqm3v39o8.execute-api.ap-south-1.amanonaws.com/dev/pets";

		ResponseEntity<Pet> entity = rt.getForEntity(petEndPoint, Pet.class);
		Pet petData = entity.getBody();

		WelcomeResponse finalResonse = new WelcomeResponse();
		finalResonse.setGreetMsg(greetMsg);
		finalResonse.setWelcomeMsg(welcomeMsg);
		 finalResonse.setPet(petData);

		return finalResonse;
	}
}
