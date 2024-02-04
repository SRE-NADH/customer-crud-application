import { Route, Routes } from "react-router-dom";
import LoginPage from "./Pages/LoginPage";
import SiignUpPage from "./Pages/SiignUpPage";
import Home from "./Pages/Home";

function App() {
  return (
    <div>
    <Routes>
       <Route path="/" element={<SiignUpPage/>}/> 
       <Route path="/login" element={<LoginPage/>}/>
       <Route path="/home" element={<Home/>}/>
    </Routes>
   </div>
  );
}

export default App;
