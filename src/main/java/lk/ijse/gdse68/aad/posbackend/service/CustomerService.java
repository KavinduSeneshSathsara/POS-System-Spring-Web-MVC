package lk.ijse.gdse68.aad.posbackend.service;

import lk.ijse.gdse68.aad.posbackend.customObj.CustomerResponse;
import lk.ijse.gdse68.aad.posbackend.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    public void saveCustomer(CustomerDto customerDto);
    public CustomerResponse getCustomer(String customerId);
    public List<CustomerDto> getAllCustomers();
    public void updateCustomer(String customerId, CustomerDto customerDto);
    public void deleteCustomer(String customerId);
}
