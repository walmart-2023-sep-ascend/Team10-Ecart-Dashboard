import React , { useState } from 'react';
//import { BrowserRouter as Router, Route } from "react-router-dom";
import ReactDOM from 'react-dom'

import './App.css';
import Home from './pages/Home';
import Dashboard from './pages/Dashboard';
import Footer from './components/Footer';
import HeaderTop from './components/HeaderTop';
import { Routes, Route } from 'react-router-dom';

function App() {

  return (
    <div className="App">
     
      <HeaderTop />
      <Routes>
        <Route exact path="/" element={<Home/>}></Route>
        <Route exact path="/login" element={<Home/>}></Route>
        <Route exact path="/dashboard" element={<Dashboard/>}></Route>
      </Routes>
      <Footer/>
    </div>
  );
}

export default App;
