package lk.ijse.gdse68.aad.posbackend.service;

import lk.ijse.gdse68.aad.posbackend.customObj.ItemResponse;
import lk.ijse.gdse68.aad.posbackend.dto.ItemDto;

import java.util.List;

public interface ItemService{
    void saveItem(ItemDto itemDto);
    void updateItem(String itemCode, ItemDto itemDto);
    ItemResponse getItem(String itemCode);
    List<ItemDto> getAllItems();
    void deleteItem(String itemCode);
}
