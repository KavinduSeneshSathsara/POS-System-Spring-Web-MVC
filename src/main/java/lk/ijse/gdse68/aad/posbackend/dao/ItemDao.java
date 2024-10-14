package lk.ijse.gdse68.aad.posbackend.dao;

import lk.ijse.gdse68.aad.posbackend.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDao extends JpaRepository<Item, String>{
    Item getIdByItemCode(String itemCode);
}
