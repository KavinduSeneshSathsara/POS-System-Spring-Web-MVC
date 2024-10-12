package lk.ijse.gdse68.aad.posbackend.dao;

import lk.ijse.gdse68.aad.posbackend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends JpaRepository <Customer, String> {
    @Query()
    Customer getCustomerByCustomerId(String customerId);
}
