package hr.java.spring.foodstore.foodstore.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class TestService {

    private final RestTemplate restTemplate;

    public TestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String makeSecureRequest() {
        String url = "https://localhost:8443/api/all"; // Change to your endpoint
        try {
            // You can use a GET request or POST request depending on the endpoint
            String response = restTemplate.getForObject(url, String.class);
            return response;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
