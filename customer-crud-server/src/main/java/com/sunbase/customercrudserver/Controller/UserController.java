package com.sunbase.customercrudserver.Controller;
import com.sunbase.customercrudserver.Dto.ReqDto.AuthReqDto;
import com.sunbase.customercrudserver.Dto.ReqDto.UserReqDto;
import com.sunbase.customercrudserver.Dto.ResDto.UserResDto;
import com.sunbase.customercrudserver.Model.User;
import com.sunbase.customercrudserver.Service.imp.UserServiceImp;
import com.sunbase.customercrudserver.Utill.JwtUtill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImp userServiceImp;

    @Autowired
    private JwtUtill jwtUtill;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping(path = "/api/hello")
    public String hello(){
        return "hello";
    }

    @PostMapping("/register")
    public ResponseEntity RegisterUser(@RequestBody UserReqDto userReqDto){
        try{
            UserResDto userResDto = userServiceImp.RegisterUser(userReqDto);
            return new ResponseEntity(userResDto, HttpStatus.CREATED);
        }
        catch (Exception e){
            return  new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity Login(@RequestBody AuthReqDto authReqDto) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authReqDto.getEmail(), authReqDto.getPassword())
            );
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jwtUtill.generateToken(authReqDto.getEmail()),HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity getUserInfo() {
        return new ResponseEntity<>(SecurityContextHolder.getContext().getAuthentication().getPrincipal(),HttpStatus.OK);
    }

}


