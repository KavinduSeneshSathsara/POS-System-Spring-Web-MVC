package lk.ijse.gdse68.aad.posbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/healthcheck")
public class HealthCheckController {
    @GetMapping
    public String health(){
        return "POS System API is running..";
    }
}
