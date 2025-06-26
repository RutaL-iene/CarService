
import { useState } from 'react';
import axios from 'axios';

export default function Register() {
  const [form, setForm] = useState({ name: '', surname: '', email: '', phoneNumber: '', username: '', password: '' });
  const [error, setError] = useState(null);
  const [success, setSuccess] = useState(false);

  const handleChange = e => setForm({ ...form, [e.target.name]: e.target.value });

  const handleRegister = async e => {
    e.preventDefault();
    try {
      await axios.post('/api/auth/register', form);
      setSuccess(true);
      setError(null);
    } catch (err) {
      setError('Registration failed');
    }
  };

  if (success) return <p>Registration successful! Please login.</p>;

  return (
    <form onSubmit={handleRegister}>
      <input name="name" placeholder="Name" onChange={handleChange} required />
      <input name="surname" placeholder="Surname" onChange={handleChange} required />
      <input name="email" type="email" placeholder="Email" onChange={handleChange} required />
      <input name="phoneNumber" placeholder="Phone Number" onChange={handleChange} required />
      <input name="username" placeholder="Username" onChange={handleChange} required />
      <input name="password" type="password" placeholder="Password" onChange={handleChange} required />
      <button type="submit">Register</button>
      {error && <p style={{color: 'red'}}>{error}</p>}
    </form>
  );
}
