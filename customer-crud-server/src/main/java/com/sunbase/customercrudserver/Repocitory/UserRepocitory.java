package com.sunbase.customercrudserver.Repocitory;

import com.sunbase.customercrudserver.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepocitory extends JpaRepository<User,Integer> {

    User findByEmail(String username);
}
