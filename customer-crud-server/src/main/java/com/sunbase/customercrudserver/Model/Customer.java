package com.sunbase.customercrudserver.Model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String first_name;

    String last_name;

    String street;

    String address;

    String city;

    String state;

    @Column(nullable = false,unique = true)
    String email;

    @Column(nullable = false,unique = true)
    String phone;

    @ManyToOne
    @JoinColumn
    User user;

}
