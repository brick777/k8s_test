package pl.brick777.customer.api;

import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class CustomerApi {
    @Value("${app.id}")
    private String appId;

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping(value = "users/info")
    public ResponseEntity<String> displayInfo() {
        log.info("displayInfo");
        restTemplate.getForEntity("http://localhost:8080/users/info2", String.class);
        return ResponseEntity.ok("It works " + appId);
    }

    @GetMapping(value = "users/info2")
    public ResponseEntity<String> displayInfo2() {
        log.info("displayInfo2");
        restTemplate.getForEntity("http://localhost:8080/users/info3", String.class);
        return ResponseEntity.ok("It works2 " + appId);
    }

    @GetMapping(value = "users/info3")
    public ResponseEntity<String> displayInfo3() {
        log.info("displayInfo3");
        getGreeting();
        return ResponseEntity.ok("It works3 " + appId);
    }

    @Timed(value = "greeting.time", description = "Time taken to return greeting")
    public void getGreeting() {
        log.warn("WARNING!!!");
    }
}
