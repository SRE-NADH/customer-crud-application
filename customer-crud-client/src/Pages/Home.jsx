import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { MdDelete } from "react-icons/md";
import { MdOutlineEdit } from "react-icons/md";
import { useNavigate } from 'react-router-dom';

const Home = () => {
const navigate = useNavigate();

const [isClickCustomer,setIsClickCustomer] = useState(false); // keep track of clicking add customer button 
const [customers,setCustomers] = useState([]); // keep track of customers array
const [token,setToken] = useState("");
const [isClickEdit,setIsClickEdit] = useState(false); //kepp track of clicking edit icon
const [searchValue,setSearchValue] = useState("");  // keep track of search value

const [first_name,setFirst_name] = useState("");
const [last_name,setLast_name] = useState("");
const [email,setEmail] = useState("");
const [phone,setPhone] = useState("");
const [address,setAddress] = useState("");
const [city,setCity] = useState("");
const [state,setState] = useState("");
const [street,setStreet] = useState("");
const [custmerid,setCustomerId] = useState(null);

// filter customers array with the search value
let filterArray = customers.filter((item)=>item.first_name.toLowerCase().includes(searchValue.toLowerCase())
|| item.last_name.toLowerCase().includes(searchValue.toLowerCase())
|| item.state.toLowerCase().includes(searchValue.toLowerCase())
|| item.city.toLowerCase().includes(searchValue.toLowerCase())
|| item.phone.toLowerCase().includes(searchValue.toLowerCase())
|| item.email.toLowerCase().includes(searchValue.toLowerCase()));



// Function to sort the array based on a specific property
function sortByProperty(property) {
    return function (a, b) {
      if (a[property] < b[property]) {
        return -1;
      } else if (a[property] > b[property]) {
        return 1;
      }
      return 0;
    };
  };



  // sort customers array basis of select value
  function handleSelectValue(e){
    const sortedData= customers.slice().sort(sortByProperty(e.target.value));
    setCustomers([...sortedData])
  };


// for intial rendering
useEffect(()=>{
  let token = localStorage.getItem('token');
  setToken(token)
  fetchAllCustomers(token);
},[]);



// function to fetch all customers
async function fetchAllCustomers(token){
    try{
    let response = await axios.get("http://localhost:8080/customer/get/customers",{
        headers:{
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json' 
        }
    })
    // console.log(response.data);
    setCustomers(response.data);
}
catch(error){
    console.log(error.message);
    
    window.alert("token expired ! try to login again")

    navigate("/login")
}
}


async function CreateCustomer(){
    // create a customer by calling api
    // if add customer buttun is clicked then we need to create new customer
    if(isClickCustomer){
       if(!email || !phone || !address || !last_name || !first_name || !state){
        window.alert("fill all required fields");
        return;
       }
       let obj = {
        first_name:first_name,
        last_name:last_name,
        email:email,
        phone:phone,
        city:city,
        state:state,
        street:street,
        address:address
       }
    try{
     await axios.post("http://localhost:8080/customer/create",obj,{
        headers:{
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json' 
        }
     });
     window.alert("customer created")
     setAllDataToEmpty();
    }
    catch(error){
        console.log(error);
    }

    }

    // if edit button is clicked
    // then we need to updata customer
    else if(isClickEdit){
        let obj = {
            first_name:first_name,
            last_name:last_name,
            email:email,
            phone:phone,
            city:city,
            state:state,
            street:street,
            address:address
           }
       
           try{
            await axios.put(`http://localhost:8080/customer/update/${custmerid}`,obj,{
                headers:{
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json' 
                }
            });
            window.alert('updated successfully');
            setAllDataToEmpty();

           }
           catch(error){
            console.log(error)
           }
        
    }

}





// handle add customer button
function handleAddCustomer(e){
  setIsClickCustomer(true);
  setIsClickEdit(false);
  setAllDataToEmpty();
}


// function to set all data to empty string
function setAllDataToEmpty(){
    setAddress("");
    setCity("");
    setEmail("");
    setFirst_name("");
    setLast_name("");
    setPhone("");
    setState("");
    setStreet("");
}


// handle delete functionality
async function handleDelete(id){

try{
   await axios.delete(`http://localhost:8080/customer/delete/${id}`,
    {   
    headers:{
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json' 
    }
  });
   
  //update customer array
  const tmpcustomers = customers.filter(obj => obj.id !== id);
  setCustomers([...tmpcustomers]);
  window.alert("successfully deleted");


    }
    catch(error){
        console.log(error);
    }
}



// handle edit button
function handleEdit(id){
    setIsClickCustomer(false);
    setIsClickEdit(true);
  
    // find customer object by checking the id
  const Object = customers.find(obj => obj.id === id);
  if(Object){
  // set all data vaues  
  setAddress(Object.address);
  setCity(Object.city);
  setEmail(Object.email);
  setFirst_name(Object.first_name);
  setLast_name(Object.last_name);
  setPhone(Object.phone);
  setState(Object.state);
  setStreet(Object.street);

  setCustomerId(id);
}
}


async function handleSyncButton(){
  await fetchAllCustomers(token);
}



  return (
    <div className='home'>
        <h1>Customer List </h1>
        <div className='function-components'>
          <button onClick={handleAddCustomer} >Add Customer</button>  
          <select onChange={handleSelectValue} id="Search by">
              <option value="first_name">First Name</option>
              <option value="city">City</option>
              <option value="email">Email</option>
              <option value="phone">Phone</option>
           </select>

           <input type='text' onChange={(e)=>{setSearchValue(e.target.value)}} placeholder='Search'/>

           <button onClick={handleSyncButton} >Sync</button>
        </div>

        <table>
            <thead>
               <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Addres</th>
                <th>City</th>
                <th>State</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Action</th>
                </tr> 
            </thead>
            <tbody>
                {filterArray.length>0 &&
                filterArray.map((item,index)=>(
                    <tr key={index}>
                    <td>{item.first_name}</td>
                    <td>{item.last_name}</td>
                    <td>{item.address}</td>
                    <td>{item.city}</td>
                    <td>{item.state}</td>
                    <td>{item.email}</td>
                    <td>{item.phone}</td>
                    <td style={{display:"flex"}}> <div style={{marginRight:"10px",cursor:"pointer"}} onClick={(e)=>{handleDelete(item.id)}} ><MdDelete/></div>  <div style={{cursor:"pointer"}} onClick={(e)=>{handleEdit(item.id)}} ><MdOutlineEdit/></div> </td>
                    </tr> 
                ))
                }
              
            </tbody>
        </table>

        {
            (isClickCustomer || isClickEdit) &&
            
             <div className='customer-details' >
                <h2 style={{textAlign:"center"}}>{isClickCustomer?"Create new Customer":"Edit Customer"}</h2>
                <div>
                    <input type='text' value={first_name} onChange={(e)=>{setFirst_name(e.target.value)}} placeholder='First Name' />
                    <input type='text' value={last_name} onChange={(e)=>{setLast_name(e.target.value)}}  placeholder='Last Name' />
                </div>
                <div>
                    <input type='text' value={street} onChange={(e)=>{setStreet(e.target.value)}} placeholder='Street' />
                    <input type='text' value={address} onChange={(e)=>{setAddress(e.target.value)}} placeholder='Address' />
                </div>
                <div>
                    <input type='text' value={city} onChange={(e)=>{setCity(e.target.value)}} placeholder='City' />
                    <input type='text' value={state} onChange={(e)=>{setState(e.target.value)}} placeholder='State' />
                </div>
                <div>
                    <input type='text' value={email} onChange={(e)=>{setEmail(e.target.value)}} placeholder='Email' />
                    <input type='text' value={phone} onChange={(e)=>{setPhone(e.target.value)}} placeholder='Phone' />
                </div>
                <div style={{display:"flex",justifyContent:"flex-end",marginRight:"10px"}}><button onClick={CreateCustomer} >Submit</button></div>
            </div>
        }

    </div>
  )
}

export default Home