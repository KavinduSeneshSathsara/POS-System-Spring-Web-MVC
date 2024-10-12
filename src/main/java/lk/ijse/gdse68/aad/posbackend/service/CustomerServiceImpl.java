package lk.ijse.gdse68.aad.posbackend.service;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.aad.posbackend.customObj.CustomerErrorResponse;
import lk.ijse.gdse68.aad.posbackend.customObj.CustomerResponse;
import lk.ijse.gdse68.aad.posbackend.dao.CustomerDao;
import lk.ijse.gdse68.aad.posbackend.dto.CustomerDto;
import lk.ijse.gdse68.aad.posbackend.entity.Customer;
import lk.ijse.gdse68.aad.posbackend.excexption.CustomerNotFoundException;
import lk.ijse.gdse68.aad.posbackend.excexption.DataPersistFailedException;
import lk.ijse.gdse68.aad.posbackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveCustomer(CustomerDto customerDto) {
        if (customerDao.existsById(customerDto.getCustomerId())) {
            throw new DataPersistFailedException("This customer ID already ");
        } else {
            Customer savedCustomer = customerDao.save(mapping.convertToEntity(customerDto));
            if (savedCustomer == null && savedCustomer.getCustomerId() == null) {
                throw new DataPersistFailedException("Can't save the customer!");
            }
        }
    }

    @Override
    public CustomerResponse getCustomer(String customerId) {
        if (customerDao.existsById(customerId)) {
           return mapping.convertToDto(customerDao.getCustomerByCustomerId(customerId));
        } else {
            return new CustomerErrorResponse(0, "Customer Not Found!");
        }
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return mapping.convertToDtos(customerDao.findAll());
    }

    @Override
    public void updateCustomer(String customerId, CustomerDto customerDto) {
        Optional<Customer> tmpCustomer = customerDao.findById(customerId);
        if (!tmpCustomer.isPresent()){
            throw new DataPersistFailedException(("Customer Not Found!"));
        }else {
            tmpCustomer.get().setCustomerName(customerDto.getCustomerName());
            tmpCustomer.get().setCustomerAddress(customerDto.getCustomerAddress());
            tmpCustomer.get().setCustomerSalary(customerDto.getCustomerSalary());
        }
    }

    @Override
    public void deleteCustomer(String customerId) {
        Optional<Customer> existingCustomer = customerDao.findById(customerId);
        if (!existingCustomer.isPresent()){
            throw new CustomerNotFoundException("Customer Not Found!");
        }else {
            customerDao.deleteById(customerId);
        }
    }
}
