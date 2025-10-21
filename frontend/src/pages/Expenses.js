

import React, { useState, useEffect } from 'react';
import { Typography, Container, Box } from '@mui/material';
import BillScanner from '../components/BillScanner';
import { getPersonalExpenses, addPersonalExpense } from '../api';

function PersonalExpenses() {
  const [scannedText, setScannedText] = useState('');
  const [expenses, setExpenses] = useState([]);
  const [amount, setAmount] = useState('');
  const [desc, setDesc] = useState('');

  useEffect(() => {
    getPersonalExpenses().then(setExpenses);
  }, []);

  const handleAddExpense = async (e) => {
    e.preventDefault();
    if (amount && desc) {
      const newExpense = await addPersonalExpense({
        amount: parseFloat(amount),
        description: desc,
        currency: "USD",
        payerId: 1 // You can update this to the actual user ID if available
      });
      setExpenses([...expenses, newExpense]);
      setAmount('');
      setDesc('');
    }
  };

  return (
    <Container>
      <Typography variant="h4" sx={{ mt: 4, mb: 2 }} color="primary">
        Personal Expenses
      </Typography>
      <Typography>
        Track and manage your personal expenses here.
      </Typography>
      <Box component="form" onSubmit={handleAddExpense} sx={{ mb: 4, display: 'flex', gap: 2, flexWrap: 'wrap' }}>
        <input type="number" placeholder="Amount" value={amount} onChange={e => setAmount(e.target.value)} required style={{ padding: '8px', borderRadius: '4px', border: '1px solid #ccc' }} />
        <input type="text" placeholder="Description" value={desc} onChange={e => setDesc(e.target.value)} required style={{ padding: '8px', borderRadius: '4px', border: '1px solid #ccc' }} />
        <button type="submit" style={{ padding: '8px 16px', borderRadius: '4px', background: '#00bcd4', color: '#fff', border: 'none' }}>Add Expense</button>
      </Box>
      {expenses.length > 0 && (
        <Box sx={{ mb: 4 }}>
          <Typography variant="h6" color="secondary">Personal Expenses List</Typography>
          {expenses.map((exp, idx) => (
            <Box key={idx} sx={{ p: 2, mb: 2, bgcolor: 'background.paper', borderRadius: 2, boxShadow: 1 }}>
              <Typography>Amount: {exp.amount}</Typography>
              <Typography>Description: {exp.description}</Typography>
            </Box>
          ))}
        </Box>
      )}
      <BillScanner onScanComplete={setScannedText} />
      {scannedText && (
        <Box sx={{ mt: 2, p: 2, bgcolor: 'background.paper', borderRadius: 2, boxShadow: 1 }}>
          <Typography variant="subtitle1" color="secondary">Bill Data Preview:</Typography>
          <Typography variant="body2" sx={{ whiteSpace: 'pre-wrap' }}>{scannedText}</Typography>
        </Box>
      )}
    </Container>
  );
}

export default PersonalExpenses;
