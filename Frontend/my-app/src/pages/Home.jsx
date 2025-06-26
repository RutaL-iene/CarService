// src/pages/Home.jsx
import { useState } from 'react';
import Login from '../components/Login';
import Register from '../components/Register';

export default function Home() {
  const [showRegister, setShowRegister] = useState(false);

  return (
    <div>
      <h1>Welcome to CarService</h1>
      {showRegister ? (
        <>
          <Register />
          <button onClick={() => setShowRegister(false)}>Already have an account? Login</button>
        </>
      ) : (
        <>
          <Login />
          <button onClick={() => setShowRegister(true)}>Don't have an account? Register</button>
        </>
      )}
    </div>
  );
}
