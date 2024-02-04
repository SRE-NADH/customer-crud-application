package com.sunbase.customercrudserver.Dto.ResDto;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserResDto {
    String name ;

    String email;

    String phone;
}
