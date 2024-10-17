package lk.ijse.gdse68.aad.posbackend.controller;

import lk.ijse.gdse68.aad.posbackend.customObj.ItemResponse;
import lk.ijse.gdse68.aad.posbackend.dao.ItemDao;
import lk.ijse.gdse68.aad.posbackend.dto.ItemDto;
import lk.ijse.gdse68.aad.posbackend.excexption.CustomerNotFoundException;
import lk.ijse.gdse68.aad.posbackend.excexption.DataPersistFailedException;
import lk.ijse.gdse68.aad.posbackend.excexption.ItemNotFoundException;
import lk.ijse.gdse68.aad.posbackend.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mysql.cj.conf.PropertyKey.logger;

@RestController
@RequestMapping("api/v1/item")
@RequiredArgsConstructor
@CrossOrigin
public class ItemController {

    Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemDao itemDao;

    @PostMapping
    public ResponseEntity<String> saveItem(@RequestBody ItemDto itemDto){
        logger.info("Request to save item: {}", itemDto);
        if (itemDto == null) {
            logger.error("Item Dto is null");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (itemDto.getItemCode() == null || !itemDto.getItemCode().matches("^I\\d{4}$")) {
            return new ResponseEntity<>("Item Name is empty or invalid! It should contain at least 4 alphabetic characters and first letter should be capital. ", HttpStatus.BAD_REQUEST);
        }
        if (itemDto.getItemName() == null || !itemDto.getItemName().matches("^([A-Z][a-z]+)(\\s[A-Z][a-z]+)*$")) {
            return new ResponseEntity<>("Item Name is empty or invalid! ", HttpStatus.BAD_REQUEST);
        }
        if (itemDto.getItemQty() <= 0) {
            return new ResponseEntity<>("Item quantity is empty or invalid! It must be greater than 0", HttpStatus.BAD_REQUEST);
        }
        if (itemDto.getItemPrice() <= 0) {
            return new ResponseEntity<>("Item price is empty or invalid! ", HttpStatus.BAD_REQUEST);
        } else {
            try {
                itemService.saveItem(itemDto);
                logger.info("Successfully saved item: {}", itemDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (DataPersistFailedException e) {
                logger.error("Failed to persist item data: {}", itemDto, e);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                logger.error("Unexpected error while saving item: {}", itemDto, e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PatchMapping("/{itemCode}")
    public ResponseEntity<String> updateItem(@PathVariable ("itemCode") String itemCode, @RequestBody ItemDto itemDto) {
        logger.info("Request to update item with ID: {}", itemCode);
        if (itemDto == null) {
            logger.error("Item Dto is null");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (itemDto.getItemCode() == null || !itemDto.getItemCode().matches("^I\\d{4}$")) {
            return new ResponseEntity<>("Item code is empty or invalid!", HttpStatus.BAD_REQUEST);
        }
        if (itemDto.getItemName() == null || !itemDto.getItemName().matches("^([A-Z][a-z]+)(\\s[A-Z][a-z]+)*$")) {
            return new ResponseEntity<>("Item name is empty or invalid!", HttpStatus.BAD_REQUEST);
        }
        if (itemDto.getItemQty() <= 0) {
            return new ResponseEntity<>("Item quantity is empty or invalid!", HttpStatus.BAD_REQUEST);
        }
        if (itemDto.getItemPrice() <= 0) {
            return new ResponseEntity<>("Item price is empty or invalid!", HttpStatus.BAD_REQUEST);
        }
        try {
            itemService.updateItem(itemCode, itemDto);
            logger.info("Successfully updated item with ID: {}", itemCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CustomerNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping ("/{itemCode}")
    public ItemResponse getItem(@PathVariable ("itemCode") String itemCode){
        logger.info("Request to get item with ID: {}", itemCode);
        ItemResponse response = itemService.getItem(itemCode);
        logger.info("Successfully retrieved item: {}", response);
        return response;
    }

    @GetMapping(value = "/allItems", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemDto> getAllItems(){
        logger.info("Request to get all items");
        List<ItemDto> itemDtos = itemService.getAllItems();
        logger.info("Fetched {} Items from the database", itemDtos.size());

        for (ItemDto itemDto : itemDtos){
            logger.info("Item Dto: {}", itemDto);
        }
        logger.info("Successfully retrieved all items, count: {}", itemDtos.size());
        return itemDtos;
    }

    @DeleteMapping("/{itemCode}")
    public ResponseEntity<Void> deleteItem(@PathVariable ("itemCode") String itemCode) {
        logger.info("Request to delete item with ID: {}", itemCode);
        try {
            itemService.deleteItem(itemCode);
            logger.info("Successfully deleted item with ID: {}", itemCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ItemNotFoundException e) {
            logger.warn("Item not found with ID: {}", itemCode);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Unexpected error while deleting item with ID: {}", itemCode);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
