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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);

    @Autowired
    private ItemDao itemDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveItem(ItemDto itemDto) {
        logger.info("Attempting to save item: {}", itemDto);
        if (itemDao.existsById(itemDto.getItemCode())){
            logger.warn("Item code already exists: {}", itemDto.getItemCode());
            throw new DataPersistFailedException("This item code already exists!");
        }else {
            Item savedItem = itemDao.save(mapping.convertToEntity(itemDto));
            if (savedItem == null && savedItem.getItemCode() == null) {
                logger.error("Failed to save item: {}", itemDto);
                throw new DataPersistFailedException("Can't save the item!");
            }
            logger.info("Successfully saved item: {}", itemDto);
        }
    }

    @Override
    public void updateItem(String itemCode, ItemDto itemDto) {
        logger.info("Attempting to update item with ID: {}", itemCode);
        Optional<Item> existItem = itemDao.findById(itemCode);
        if (existItem == null) {
            logger.warn("Item not found for update with ID: {}", itemCode);
            throw new ItemNotFoundException("Item not found!");
        }else {
            logger.info("Updating item details for ID: {}", itemCode);
            existItem.get().setItemName(itemDto.getItemName());
            existItem.get().setItemQty((itemDto.getItemQty()));
            existItem.get().setItemPrice(itemDto.getItemPrice());
            logger.info("Successfully updated item with ID: {}", itemCode);
        }
    }

    @Override
    public ItemResponse getItem(String itemCode) {
        logger.info("Fetching item with item code: {}", itemCode);
        if (itemDao.existsById(itemCode)){
            logger.info("Successfully retrieved item with item code: {}", itemCode);
            return mapping.convertToDto(itemDao.getIdByItemCode(itemCode));
        }else {
            logger.warn("Item not found with item code: {}", itemCode);
            return new ItemErrorResponse(0, "Item not found!");
        }
    }

    @Override
    public List<ItemDto> getAllItems() {
        logger.info("Fetching all items");
        logger.info("Successfully retrieved all items, count: {}", itemDao.findAll().size());
        return mapping.convertToItemDtos(itemDao.findAll());
    }

    @Override
    public void deleteItem(String itemCode) {
        logger.info("Attempting to delete item with ID: {}", itemCode);
        Optional<Item> existsItem = itemDao.findById(itemCode);
        if (!existsItem.isPresent()){
            logger.warn("Item not found for delete with ID: {}", itemCode);
            throw new ItemNotFoundException("Item not found!");
        } else {
            itemDao.deleteById(itemCode);
            logger.info("Successfully deleted item with ID: {}", itemCode);
        }
    }
}
