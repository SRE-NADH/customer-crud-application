package com.sunbase.customercrudserver.Repocitory;

import com.sunbase.customercrudserver.Model.Customer;
import com.sunbase.customercrudserver.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepocitory extends JpaRepository<Customer,Integer> {

    Customer findByEmail(String email);
}
