import axios from 'axios';
import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom';

const SiignUpPage = () => {

 const navigate = useNavigate();

 const [formData,setFormData] = useState({
    name:"",
    password:"",
    confirm_password:"",
    phone:"",
    email:""
 });

let {name,email,password,confirm_password,phone} = formData;

async function createUser(e){
          e.preventDefault();
          if(!name || !phone || !email || !password || !confirm_password){
            window.alert('fill all the fields');
            return;
          }
    if(password!==confirm_password){
        window.alert('password should be same');
        return;
    }

    let obj = {
        name:name,
        email:email,
        password:password,
        phone:phone
    }
try{
    // call api for creating new user
    let response = await axios.post("http://localhost:8080/user/register",obj,{
        headers:{
            'Content-Type': 'application/json' 
        }
    })
    window.alert("you acount has been created");
    // console.log(response.data);
    navigate("/login")

}
catch(error){
    window.alert(error.message);
}
}

  return (
    <div className='sign-up'>
        <form>
           <input type='text' value={name}  name='name' placeholder='Name'  onChange={(e)=>{setFormData({...formData,[e.target.name]:e.target.value})}} />
           <input type='text' value={email}  name='email' placeholder='Email'  onChange={(e)=>{setFormData({...formData,[e.target.name]:e.target.value})}} />
           <input type='text' value={phone} name='phone' placeholder='Phone'  onChange={(e)=>{setFormData({...formData,[e.target.name]:e.target.value})}} />
           <input type='password' value={password}  name='password' placeholder='Password'  onChange={(e)=>{setFormData({...formData,[e.target.name]:e.target.value})}} />
           <input type='password' value={confirm_password}  name='confirm_password' placeholder='confirm password'   onChange={(e)=>{setFormData({...formData,[e.target.name]:e.target.value})}} />
           <button onClick={createUser}>Sign Up</button>
           <div style={{margin:"10px"}} onClick={()=>{navigate('/login')}}>click to login</div>
        </form>

    </div>
  )
}

export default SiignUpPage