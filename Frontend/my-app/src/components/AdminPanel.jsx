import { useEffect, useState } from 'react';
import axios from 'axios';

export default function AdminPanel() {
  const [providers, setProviders] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // Form state for new provider
  const [newProvider, setNewProvider] = useState({
    title: '',
    address: '',
    manager: '',
  });

  // Fetch providers from backend
  const fetchProviders = async () => {
    setLoading(true);
    try {
      const response = await axios.get('/api/providers'); // Adjust URL
      setProviders(response.data);
      setError(null);
    } catch (err) {
      setError('Failed to fetch providers');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchProviders();
  }, []);

  const handleChange = e => {
    setNewProvider(prev => ({ ...prev, [e.target.name]: e.target.value }));
  };

  
  const handleCreate = async e => {
    e.preventDefault();
    try {
      await axios.post('/api/providers', newProvider);
      setNewProvider({ title: '', address: '', manager: '' });
      fetchProviders();
    } catch (err) {
      alert('Failed to create provider');
    }
  };

  
  const handleDelete = async (id) => {
    if (!window.confirm('Are you sure you want to delete this provider?')) return;
    try {
      await axios.delete(`/api/providers/${id}`);
      fetchProviders();
    } catch (err) {
      alert('Failed to delete provider');
    }
  };

  return (
    <div style={{ padding: 20 }}>
      <h2>Admin Panel - Manage Providers</h2>

      {/* New Provider Form */}
      <form onSubmit={handleCreate} style={{ marginBottom: 20 }}>
        <input
          name="title"
          placeholder="Title"
          value={newProvider.title}
          onChange={handleChange}
          required
          style={{ marginRight: 10 }}
        />
        <input
          name="address"
          placeholder="Address"
          value={newProvider.address}
          onChange={handleChange}
          required
          style={{ marginRight: 10 }}
        />
        <input
          name="manager"
          placeholder="Manager"
          value={newProvider.manager}
          onChange={handleChange}
          required
          style={{ marginRight: 10 }}
        />
        <button type="submit">Add Provider</button>
      </form>

      {/* Providers List */}
      {loading ? (
        <p>Loading providers...</p>
      ) : error ? (
        <p style={{ color: 'red' }}>{error}</p>
      ) : providers.length === 0 ? (
        <p>No providers found.</p>
      ) : (
        <table border="1" cellPadding="8" cellSpacing="0">
          <thead>
            <tr>
              <th>Title</th>
              <th>Address</th>
              <th>Manager</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {providers.map((provider) => (
              <tr key={provider.id}>
                <td>{provider.title}</td>
                <td>{provider.address}</td>
                <td>{provider.manager}</td>
                <td>
                  <button onClick={() => handleDelete(provider.id)}>Delete</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}
