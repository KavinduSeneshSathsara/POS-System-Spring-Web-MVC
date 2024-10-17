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
        if (orderDto == null){
            return ResponseEntity.badRequest().build();
        }else {
            try {
                orderService.saveOrder(orderDto);
                logger.info("Order saved : " + orderDto);
                return ResponseEntity.created(null).build();
            } catch (DataPersistFailedException e) {
                logger.error(e.getMessage());
                return ResponseEntity.internalServerError().build();
            } catch (Exception e) {
                logger.error(e.getMessage());
                return ResponseEntity.internalServerError().build();
            }
        }
    }
}
