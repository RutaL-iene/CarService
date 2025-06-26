
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Home from './pages/Home';
import AdminPanel from './components/AdminPanel';
import UserPanel from './components/UserPanel';



function App() {
  return (
    <Router>
      <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/admin" element={<AdminPanel />} />
      <Route path="/user" element={<UserPanel />} />
      </Routes>
    </Router>
  );
}

export default App;
