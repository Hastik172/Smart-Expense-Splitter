

import React, { useState, useEffect } from 'react';
import { Typography, Container, Card, CardContent, Avatar, Box, TextField, Button } from '@mui/material';

import { getUsers, addUser } from '../api';

function Users() {
  const [users, setUsers] = useState([]);
  useEffect(() => {
    getUsers().then(setUsers);
  }, []);
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');

  const handleAddUser = async (e) => {
    e.preventDefault();
    if (name && email) {
      const newUser = await addUser({ name, email });
      setUsers([...users, newUser]);
      setName('');
      setEmail('');
    }
  };

  const handleDeleteUser = (idx) => {
    setUsers(users.filter((_, i) => i !== idx));
  };

  return (
    <Container>
      <Typography variant="h4" sx={{ mt: 4, mb: 2 }} color="primary">
        Users
      </Typography>
      <Box component="form" onSubmit={handleAddUser} sx={{ mb: 4, display: 'flex', gap: 2 }}>
        <TextField label="Name" value={name} onChange={e => setName(e.target.value)} required />
        <TextField label="Email" value={email} onChange={e => setEmail(e.target.value)} required />
        <Button type="submit" variant="contained" color="secondary">Add User</Button>
      </Box>
      <Box sx={{ display: 'flex', gap: 3, flexWrap: 'wrap' }}>
        {users.map((user, idx) => (
          <Card key={idx} sx={{ minWidth: 220, mb: 2, bgcolor: 'background.paper', boxShadow: 3 }}>
            <CardContent>
              <Avatar sx={{ bgcolor: 'primary.main', mb: 2 }}>{user.name[0]}</Avatar>
              <Typography variant="h6" color="secondary">{user.name}</Typography>
              <Typography variant="body2">{user.email}</Typography>
              <Button variant="outlined" color="error" sx={{ mt: 2 }} onClick={() => handleDeleteUser(idx)}>
                Delete
              </Button>
            </CardContent>
          </Card>
        ))}
      </Box>
    </Container>
  );
}

export default Users;
