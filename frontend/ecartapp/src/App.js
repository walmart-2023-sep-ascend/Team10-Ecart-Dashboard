
import './App.css';

import { PageHeader } from './header';
import LoginComponent from './login';
import { Routes, Route } from 'react-router-dom';
import { PhoneComponent } from './Phone';

function App() {
  return (
    <div className="App">
      <PageHeader />
      <Routes>
        <Route path="/phones" element={<PhoneComponent />} />
        <Route path="/login" element={<LoginComponent />} />
      </Routes>
    </div>
  );
}

export default App;
