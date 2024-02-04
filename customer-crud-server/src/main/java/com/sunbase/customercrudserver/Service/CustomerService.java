package com.sunbase.customercrudserver.Service;

import com.sunbase.customercrudserver.Dto.ReqDto.CustomerReqDto;
import com.sunbase.customercrudserver.Dto.ResDto.CustomerResDto;

import java.util.List;

public interface CustomerService {
    CustomerResDto createCustomer(CustomerReqDto customerReqDto) throws Exception;

    void updateCustomer(int id, CustomerReqDto customerReqDto) throws Exception;

    List<CustomerResDto> getAllCustomers();


    CustomerResDto getCustomerById(int id) throws Exception;

    void DeleteCustomer(int id);
}
