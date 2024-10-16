package lk.ijse.gdse68.aad.posbackend.controller;

import lk.ijse.gdse68.aad.posbackend.customObj.CustomerResponse;
import lk.ijse.gdse68.aad.posbackend.dto.CustomerDto;
import lk.ijse.gdse68.aad.posbackend.excexption.CustomerNotFoundException;
import lk.ijse.gdse68.aad.posbackend.excexption.DataPersistFailedException;
import lk.ijse.gdse68.aad.posbackend.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
@CrossOrigin
public class CustomerController {

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private final CustomerService customerService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveCustomer(@RequestBody CustomerDto customerDto) {

        if (customerDto == null) {
            logger.error("Customer Dto is null");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (customerDto.getCustomerId() == null || !customerDto.getCustomerId().matches("^C\\d{4}$")) {
            return new ResponseEntity<>("Customer ID is empty or invalid! It should match 'C0000' format.", HttpStatus.BAD_REQUEST);
        }
        if (customerDto.getCustomerName() == null || !customerDto.getCustomerName().matches("^([A-Z][a-z]+)(\\s[A-Z][a-z]+)*$")) {
            return new ResponseEntity<>("Customer Name is empty or invalid! It should contain at least 4 alphabetic characters and first letter should be capital.", HttpStatus.BAD_REQUEST);
        }
        if (customerDto.getCustomerAddress() == null || !customerDto.getCustomerAddress().matches("^[A-Za-z0-9\\s,./-]+$")) {
            return new ResponseEntity<>("Customer Address is empty or invalid!", HttpStatus.BAD_REQUEST);
        }
        if (customerDto.getCustomerSalary() <= 0) {
            return new ResponseEntity<>("Customer Salary is empty or invalid! It must be greater than 0.", HttpStatus.BAD_REQUEST);
        }
        try {
                customerService.saveCustomer(customerDto);
                logger.warn("Successfully saved customer: {}", customerDto);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (DataPersistFailedException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }


    @GetMapping("/{customerId}")
    public CustomerResponse getCustomer(@PathVariable ("customerId") String customerId){
         return customerService.getCustomer((customerId));
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomerDto> getAllCustomers(){
        List<CustomerDto> customerDtos = customerService.getAllCustomers();
        logger.info("Retrieved {} customer records from the database successfully", customerDtos.size());

        for (CustomerDto customerDto : customerDtos) {
            logger.info("Customer ID: {}, Customer Name: {}, Customer Address: {}, Customer Salary: {}",
                    customerDto.getCustomerId(), customerDto.getCustomerName(), customerDto.getCustomerAddress(), customerDto.getCustomerSalary());
        }
        return customerDtos;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateCustomer(@PathVariable("customerId") String customerId, @RequestBody CustomerDto customerDto) {

        if (customerDto == null) {
            return new ResponseEntity<>("Customer data is missing!", HttpStatus.BAD_REQUEST);
        }

        if (customerId == null || !customerId.matches("^C\\d{4}$")) {
            return new ResponseEntity<>("Customer ID is empty or invalid! It should match 'C0000' format.!", HttpStatus.BAD_REQUEST);
        }

        if (customerDto.getCustomerName() == null || !customerDto.getCustomerName().matches("^([A-Z][a-z]+)(\\s[A-Z][a-z]+)*$")) {
            return new ResponseEntity<>("Customer Name is empty or invalid! It should contain at least 4 alphabetic characters and first letter should be capital.!", HttpStatus.BAD_REQUEST);
        }

        if (customerDto.getCustomerAddress() == null || !customerDto.getCustomerAddress().matches("^[A-Za-z0-9\\s,./-]+$")) {
            return new ResponseEntity<>("Customer Address is empty or invalid!", HttpStatus.BAD_REQUEST);
        }

        if (customerDto.getCustomerSalary() <= 0) {
            return new ResponseEntity<>("Customer Salary is empty or invalid!", HttpStatus.BAD_REQUEST);
        }

        try {
            customerService.updateCustomer(customerId, customerDto);
            logger.info("Customer with ID {} updated successfully", customerId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CustomerNotFoundException e) {
            logger.error("Customer with ID {} not found", customerId, e);
            return new ResponseEntity<>("Customer not found!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("An unexpected error occurred while updating customer with ID {}", customerId, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable ("customerId") String customerId) {
        try {
            customerService.deleteCustomer(customerId);
            logger.info("Customer with ID {} deleted successfully", customerId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CustomerNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}