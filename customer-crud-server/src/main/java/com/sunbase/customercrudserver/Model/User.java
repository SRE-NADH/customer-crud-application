package com.sunbase.customercrudserver.Model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name ;

    @Column(nullable = false,unique = true)
    String phone;

    @Column(nullable = false,unique = true)
    String email;

    String password;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Customer> customerList = new ArrayList<>();

}
