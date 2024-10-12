package lk.ijse.gdse68.aad.posbackend.util;

import lk.ijse.gdse68.aad.posbackend.dto.CustomerDto;
import lk.ijse.gdse68.aad.posbackend.entity.Customer;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    public Customer convertToEntity(CustomerDto customerDto){
        return modelMapper.map(customerDto, Customer.class);
    }
    public CustomerDto convertToDto(Customer customer){
        return modelMapper.map(customer, CustomerDto.class);
    }
    public List<CustomerDto> convertToDtos(List<Customer> customerList){
        return modelMapper.map(customerList,new TypeToken<List<CustomerDto>>(){}.getType());
    }
}
