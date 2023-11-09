import './App.css';

import { PageHeader } from './header';
import LoginComponent from './login';
import { Routes, Route } from 'react-router-dom';
import { PhoneComponent } from './Phone';
import UserSearchesComponent from './UserSearchesComponent';
import UserProducts from './UserProducts';
import UserDashboard from './UserDashboard';
function App() {
  return (
    <div className="App">
      <PageHeader />
      <Routes>
        <Route path="/phones" element={<PhoneComponent />} />
        <Route path="/login" element={<LoginComponent />} />
        <Route path="/integratedPage" element={<UserSearchesComponent/>} />
        <Route path="/UserProducts" element={<UserProducts/>} />
        <Route path="/UserDashboard" element={<UserDashboard/>} />
      </Routes>
    </div>
  );
}

export default App;