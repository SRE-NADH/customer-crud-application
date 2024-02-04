package com.sunbase.customercrudserver.Dto.ReqDto;

import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerReqDto {
    String first_name;

    String last_name;

    String street;

    String address;

    String city;

    String state;

    String email;

    String phone;
}
