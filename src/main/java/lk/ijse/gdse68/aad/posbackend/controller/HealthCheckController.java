package lk.ijse.gdse68.aad.posbackend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/healthcheck")
public class HealthCheckController {

    private static final Logger logger = LoggerFactory.getLogger(HealthCheckController.class);

    @GetMapping
    public String health(){
        logger.info("This is an INFO log from a controller");
        logger.debug("This is a DEBUG log from a controller");
        logger.error("This is an ERROR log from a controller");
        return "POS System API is running..";
    }
}
