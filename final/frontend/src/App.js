/*@author: Sowmyalakshmi KL */
import './App.css';

import { PageHeader } from './header';
import LoginComponent from './login';
import { Routes, Route } from 'react-router-dom';
import ProductsById from './ProductsById';
import Dashboard from './Dashboard';
import ProductsByText from './ProductsByText';

function App() {
  return (
    <div className="App">
      <PageHeader />
      <Routes>
        
        <Route path="/login" element={<LoginComponent />} />        
        <Route path="/Dashboard/:emailId" element={<Dashboard/>} />
        <Route path="/ProductsById/:productId" element={<ProductsById/>} />
        <Route path="/ProductsByText/:text" element={<ProductsByText/>} />
        
      </Routes>
    </div>
  );
}

export default App;