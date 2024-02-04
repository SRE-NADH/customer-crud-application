package com.sunbase.customercrudserver.Service.imp;

import com.sunbase.customercrudserver.Dto.ReqDto.CustomerReqDto;
import com.sunbase.customercrudserver.Dto.ResDto.CustomerResDto;
import com.sunbase.customercrudserver.Model.Customer;
import com.sunbase.customercrudserver.Model.User;
import com.sunbase.customercrudserver.Repocitory.CustomerRepocitory;
import com.sunbase.customercrudserver.Repocitory.UserRepocitory;
import com.sunbase.customercrudserver.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


@Service
public class CustomerServiceImp implements CustomerService {
@Autowired
private CustomerRepocitory customerRepocitory;

@Autowired
private UserRepocitory userRepocitory;
    @Override
    public CustomerResDto createCustomer(CustomerReqDto customerReqDto) throws Exception {
        // get user email from authentication object
        String userEmail = getUserEmail();


        //get user Object
        User user = userRepocitory.findByEmail(userEmail);

        if(user==null){
            throw new Exception("user not found");
        }


        //create customer object
        Customer customer = new Customer();
        customer.setAddress(customerReqDto.getAddress());
        customer.setCity(customerReqDto.getCity());
        customer.setEmail(customerReqDto.getEmail());
        customer.setPhone(customerReqDto.getPhone());
        customer.setState(customerReqDto.getState());
        customer.setFirst_name(customerReqDto.getFirst_name());
        customer.setLast_name(customerReqDto.getLast_name());
        customer.setStreet(customerReqDto.getStreet());
        customer.setUser(user);
        user.getCustomerList().add(customer);

        // save user in user repocitory
        User savedUser = userRepocitory.save(user);

        Customer savedCustomer = savedUser.getCustomerList().get(savedUser.getCustomerList().size()-1);
        return convertToCustomerResDto(savedCustomer);
    }

    @Override
    public void updateCustomer(int id, CustomerReqDto customerReqDto) throws Exception {

        Optional<Customer> optCustomer = customerRepocitory.findById(id);

        Customer customer = optCustomer.orElse(null);
        if(customer==null){
            throw new Exception("customer not found");
        }

        System.out.println(customer.getEmail());




        //update customer
        // check data contains in customerReqDto  and update accordingly
        if(customerReqDto.getEmail()!=null){
            customer.setEmail(customerReqDto.getEmail());
            System.out.println(true);
        }
        if(customerReqDto.getState()!=null){
            customer.setState(customerReqDto.getState());
            System.out.println(true);
        }
        if(customerReqDto.getStreet()!=null){
            customer.setStreet(customerReqDto.getStreet());
            System.out.println(true);
        }
        if(customerReqDto.getLast_name()!=null){
            customer.setLast_name(customerReqDto.getLast_name());
            System.out.println(true);
        }

        if(customerReqDto.getFirst_name()!=null){
            customer.setFirst_name(customerReqDto.getFirst_name());
            System.out.println(true);
        }
        if(customerReqDto.getPhone()!=null){
            customer.setPhone(customerReqDto.getPhone());
            System.out.println(true);
        }
        if(customerReqDto.getAddress()!=null){
            customer.setAddress(customerReqDto.getAddress());
            System.out.println(true);
        }
        if(customerReqDto.getCity()!=null){
            customer.setCity(customerReqDto.getCity());
            System.out.println(true);
        }
//

//        // get user
         String userEmail = getUserEmail();
         User user = userRepocitory.findByEmail(userEmail);
         if(user==null) {
             throw  new Exception("user not found");
         }


         // find customer and remove from customer list

         List<Customer> customerList = user.getCustomerList();

        Iterator<Customer> iterator = customerList.iterator();
        while (iterator.hasNext()) {
            Customer tmpCustomer = iterator.next();
            System.out.println(tmpCustomer.getId());

            if (tmpCustomer.getId()==id) {
                customerList.remove(tmpCustomer);
                break;
            }
        }

        //add updated customer to customer list
        customerList.add(customer);

        // set customer list
        user.setCustomerList(customerList);

        // save user
         userRepocitory.save(user);
    }

    // return a list customers
    @Override
    public List<CustomerResDto> getAllCustomers() {
        String userEmail = getUserEmail();
        User user = userRepocitory.findByEmail(userEmail);

        List<Customer> customerList= user.getCustomerList();

        List<CustomerResDto> customerResDtoList = new ArrayList<>();

        // convert all customers to customerResDto
        for(Customer customer:customerList){
            CustomerResDto customerResDto = convertToCustomerResDto(customer);
            customerResDtoList.add(customerResDto);
        }

        return customerResDtoList;
    }



    //return a customer with given id
    @Override
    public CustomerResDto getCustomerById(int id) throws Exception {
        Optional<Customer> Optcustomer = customerRepocitory.findById(id);

        Customer customer = Optcustomer.orElse(null);

        if(customer==null){
            throw new Exception("customer not found");
        }
        return convertToCustomerResDto(customer);
    }


    // delete customer
    @Override
    public void DeleteCustomer(int id) {
        Optional<Customer> optionalCustomer = customerRepocitory.findById(id);
        Customer customer = optionalCustomer.orElse(null);
        if(customer!=null){

            String userEmail = getUserEmail();
            User user =  userRepocitory.findByEmail(userEmail);

            //remove customer from user customer list
            List<Customer> customerList = user.getCustomerList();

            Iterator<Customer> iterator = customerList.iterator();
            while (iterator.hasNext()) {
                Customer tmpCustomer = iterator.next();
                System.out.println(tmpCustomer.getId());

                if (tmpCustomer.getId()==id) {
                    customerList.remove(tmpCustomer);
                    break;
                }
            }

            // set customer list
            user.setCustomerList(customerList);

            // save user
            userRepocitory.save(user);
            customerRepocitory.delete(customer);
        }

    }


    // convert to CustomerResDto object
    private CustomerResDto convertToCustomerResDto(Customer savedCustomer) {
        return CustomerResDto.builder().id(savedCustomer.getId()).state(savedCustomer.getState())
                .city(savedCustomer.getCity()).first_name(savedCustomer.getFirst_name())
                .last_name(savedCustomer.getLast_name()).street(savedCustomer.getStreet()).email(savedCustomer.getEmail())
                .Phone(savedCustomer.getPhone()).address(savedCustomer.getAddress())
                .build();

    }

    // get user email from authentication object
    private String getUserEmail(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String userEmail = userDetails.getUsername();
        return userEmail;
    }

}
