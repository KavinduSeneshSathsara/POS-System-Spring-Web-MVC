package lk.ijse.gdse68.aad.posbackend.service;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.aad.posbackend.customObj.ItemErrorResponse;
import lk.ijse.gdse68.aad.posbackend.customObj.ItemResponse;
import lk.ijse.gdse68.aad.posbackend.dao.ItemDao;
import lk.ijse.gdse68.aad.posbackend.dto.ItemDto;
import lk.ijse.gdse68.aad.posbackend.entity.Item;
import lk.ijse.gdse68.aad.posbackend.excexption.DataPersistFailedException;
import lk.ijse.gdse68.aad.posbackend.excexption.ItemNotFoundException;
import lk.ijse.gdse68.aad.posbackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemServiceImpl implements ItemService{
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveItem(ItemDto itemDto) {
        if (itemDao.existsById(itemDto.getItemCode())){
            throw new DataPersistFailedException("This item code already exists!");
        }else {
            Item savedItem = itemDao.save(mapping.convertToEntity(itemDto));
            if (savedItem == null && savedItem.getItemCode() == null) {
                throw new DataPersistFailedException("Can't save the item!");
            }
        }
    }

    @Override
    public void updateItem(String itemCode, ItemDto itemDto) {
        Optional<Item> existItem = itemDao.findById(itemCode);
        if (existItem == null) {
            throw new ItemNotFoundException("Item not found!");
        }else {
            existItem.get().setItemName(itemDto.getItemName());
            existItem.get().setItemQty((itemDto.getItemQty()));
            existItem.get().setItemPrice(itemDto.getItemPrice());
        }
    }

    @Override
    public ItemResponse getItem(String itemCode) {
        if (itemDao.existsById(itemCode)){
            return mapping.convertToDto(itemDao.getIdByItemCode(itemCode));
        }else {
            return new ItemErrorResponse(0, "Item not found!");
        }
    }

    @Override
    public List<ItemDto> getAllItems() {
        return mapping.convertToItemDtos(itemDao.findAll());
    }

    @Override
    public void deleteItem(String itemCode) {
        Optional<Item> existsItem = itemDao.findById(itemCode);
        if (!existsItem.isPresent()){
            throw new ItemNotFoundException("Item not found!");
        } else {
            itemDao.deleteById(itemCode);
        }
    }
}
