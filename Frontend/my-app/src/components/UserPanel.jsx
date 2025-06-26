import { useEffect, useState } from 'react';
import axios from 'axios';

export default function UserPanel() {
  const [employees, setEmployees] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [ratings, setRatings] = useState({}); 

  // Fetch employees from backend
  const fetchEmployees = async () => {
    setLoading(true);
    try {
      const response = await axios.get('/api/employees'); 
      setEmployees(response.data);
      setError(null);
    } catch (err) {
      setError('Failed to load employees');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchEmployees();
  }, []);

  const handleRatingChange = (employeeId, value) => {
    setRatings(prev => ({ ...prev, [employeeId]: value }));
  };

  const submitRating = async (employeeId) => {
    const rating = ratings[employeeId];
    if (!rating) {
      alert('Please select a rating before submitting.');
      return;
    }
    try {
      await axios.post(`/api/employees/${employeeId}/rate`, { rating });
      alert('Rating submitted!');
     
      fetchEmployees();
    } catch (err) {
      alert('Failed to submit rating');
    }
  };

  return (
    <div style={{ padding: 20 }}>
      <h2>User Panel - Rate Employees</h2>

      {loading ? (
        <p>Loading employees...</p>
      ) : error ? (
        <p style={{ color: 'red' }}>{error}</p>
      ) : employees.length === 0 ? (
        <p>No employees found.</p>
      ) : (
        <table border="1" cellPadding="8" cellSpacing="0" style={{ width: '100%', maxWidth: 800 }}>
          <thead>
            <tr>
              <th>Name</th>
              <th>Specialisation</th>
              <th>City</th>
              <th>Current Rating</th>
              <th>Your Rating</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {employees.map(emp => (
              <tr key={emp.id}>
                <td>{emp.name} {emp.surname}</td>
                <td>{emp.specialisation}</td>
                <td>{emp.city}</td>
                <td>{emp.rating.toFixed(1)}</td>
                <td>
                  <select
                    value={ratings[emp.id] || ''}
                    onChange={e => handleRatingChange(emp.id, Number(e.target.value))}
                  >
                    <option value="">Select</option>
                    {[1, 2, 3, 4, 5].map(star => (
                      <option key={star} value={star}>{star}</option>
                    ))}
                  </select>
                </td>
                <td>
                  <button onClick={() => submitRating(emp.id)}>Submit</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}
