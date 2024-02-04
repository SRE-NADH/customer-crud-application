import axios from 'axios';
import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom';

const LoginPage = () => {
    const navigate = useNavigate();
 const [email,setEmail] = useState("");
 const [password,setPassword] = useState("");


 // add login functionality and also save jwt token in local storage
 async function Login(e){
    e.preventDefault();
    if(!email || !password){
        window.alert("fill all required fields")
        return;
    }

    let obj = {
        email:email,
        password:password
    }

    try{
      let response = await axios.post("http://localhost:8080/user/login",obj,{
       headers:{
        'Content-Type': 'application/json' 
    }
          });

    // fetch token and save it in the local storage
    // navigate to Home page      
    let token = response.data;
    localStorage.setItem('token',token)
    navigate("/home")

    }
    catch(error){
      console.log(error.message)
    }
 }

  return (
    <div className='sign-up'>
    <form>
       <input type='text' value={email} onChange={(e)=>{setEmail(e.target.value)}} placeholder='Email'/>
       <input type='password' value={password} onChange={(e)=>{setPassword(e.target.value)}} placeholder='Password'/>
       <button onClick={Login}>Login</button>
    </form>
</div>
  )
}

export default LoginPage