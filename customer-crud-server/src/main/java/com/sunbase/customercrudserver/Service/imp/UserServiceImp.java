package com.sunbase.customercrudserver.Service.imp;

import com.sunbase.customercrudserver.Dto.ReqDto.UserReqDto;
import com.sunbase.customercrudserver.Dto.ResDto.UserResDto;
import com.sunbase.customercrudserver.Model.User;
import com.sunbase.customercrudserver.Repocitory.UserRepocitory;
import com.sunbase.customercrudserver.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService,UserDetailsService {

    @Autowired
    UserRepocitory userRepocitory;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserResDto RegisterUser(UserReqDto userReqDto) {
        User user = new User();
        user.setName(userReqDto.getName());
        user.setEmail(userReqDto.getEmail());
        user.setPhone(userReqDto.getPhone());
        user.setPassword(passwordEncoder.encode(userReqDto.getPassword()));
        User tmpUser = userRepocitory.save(user);
        return convertToResDto(tmpUser);
    }


        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User user = userRepocitory.findByEmail(username);
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
        }


    // convert user to userResDto object
    private UserResDto convertToResDto(User user){
        return UserResDto.builder().name(user.getName()).email(user.getEmail()).phone(user.getPhone())
                .build();
    }
}
