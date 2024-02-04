package com.sunbase.customercrudserver.Controller;

import com.sunbase.customercrudserver.Dto.ReqDto.CustomerReqDto;
import com.sunbase.customercrudserver.Dto.ResDto.CustomerResDto;
import com.sunbase.customercrudserver.Model.Customer;
import com.sunbase.customercrudserver.Service.imp.CustomerServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerServiceImp customerServiceImp;

    //create costomer
    @PostMapping("/create")
    public ResponseEntity CreateCustomer(@RequestBody CustomerReqDto customerReqDto){
        try {
            CustomerResDto customerResDto = customerServiceImp.createCustomer(customerReqDto);
            return new ResponseEntity<>(customerResDto,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    // update customer
    @PutMapping("/update/{id}")
    public ResponseEntity updateCustomer(@PathVariable int id, @RequestBody CustomerReqDto customerReqDto){
        try{
            customerServiceImp.updateCustomer(id,customerReqDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    //get all customers
    @GetMapping("/get/customers")
    public ResponseEntity getAllCustomers(){
        try{
            List<CustomerResDto> customerResDtoList = customerServiceImp.getAllCustomers();
            return new ResponseEntity<>(customerResDtoList,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    //get a customer by id
    @GetMapping("/getById/{id}")
    public ResponseEntity getCustomerById(@PathVariable int id){

        try{
            CustomerResDto customerResDto = customerServiceImp.getCustomerById(id);
            return new ResponseEntity<>(customerResDto,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    // delete a customer using id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity DeleteCustomer(@PathVariable int id){
        try{
             customerServiceImp.DeleteCustomer(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
