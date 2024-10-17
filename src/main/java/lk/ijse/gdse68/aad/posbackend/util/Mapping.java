package lk.ijse.gdse68.aad.posbackend.util;

import lk.ijse.gdse68.aad.posbackend.dto.CustomerDto;
import lk.ijse.gdse68.aad.posbackend.dto.ItemDto;
import lk.ijse.gdse68.aad.posbackend.dto.OrderDetailDto;
import lk.ijse.gdse68.aad.posbackend.dto.OrderDto;
import lk.ijse.gdse68.aad.posbackend.entity.Customer;
import lk.ijse.gdse68.aad.posbackend.entity.Item;
import lk.ijse.gdse68.aad.posbackend.entity.Order;
import lk.ijse.gdse68.aad.posbackend.entity.OrderDetail;
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

    public Item convertToEntity(ItemDto itemDto){
        return modelMapper.map(itemDto, Item.class);
    }

    public ItemDto convertToDto(Item item){
        return modelMapper.map(item, ItemDto.class);
    }

    public List<ItemDto> convertToItemDtos(List<Item> itemList){
        return modelMapper.map(itemList, new TypeToken<List<ItemDto>>(){}.getType());
    }

    public Order convertToOrderEntity(OrderDto dto){
        return modelMapper.map(dto, Order.class);
    }

    public OrderDetail convertToOrderDetailEntity(OrderDetailDto orderDetailDto){
        return modelMapper.map(orderDetailDto, OrderDetail.class);
    }
}
