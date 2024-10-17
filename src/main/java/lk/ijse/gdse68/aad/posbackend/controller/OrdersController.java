package lk.ijse.gdse68.aad.posbackend.controller;

import io.micrometer.common.util.internal.logging.InternalLoggerFactory;
import lk.ijse.gdse68.aad.posbackend.dto.OrderDto;
import lk.ijse.gdse68.aad.posbackend.excexption.DataPersistFailedException;
import lk.ijse.gdse68.aad.posbackend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrdersController {

    @Autowired
    private final OrderService orderService;

    Logger logger = LoggerFactory.getLogger(OrdersController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void>  createOrder(@RequestBody OrderDto orderDto){
        logger.info("Request to save order: {}", orderDto);
        if (orderDto == null){
            logger.warn("Received null orderDto for saving: {}", orderDto);
            return ResponseEntity.badRequest().build();
        }else {
            try {
                orderService.saveOrder(orderDto);
                logger.info("Successfully saved order: {}", orderDto);
                return ResponseEntity.created(null).build();
            } catch (DataPersistFailedException e) {
                logger.error("Failed to persist order data: {}", orderDto, e);
                return ResponseEntity.internalServerError().build();
            } catch (Exception e) {
                logger.error("Error while saving order: {}", orderDto, e);
                return ResponseEntity.internalServerError().build();
            }
        }
    }
}
