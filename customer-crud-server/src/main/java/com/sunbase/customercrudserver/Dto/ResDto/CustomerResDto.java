package com.sunbase.customercrudserver.Dto.ResDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CustomerResDto {

    String first_name;

    String last_name;

    String street;

    String city;

    String state;

    String address;

    String email;

    String Phone;

    int id;
}
