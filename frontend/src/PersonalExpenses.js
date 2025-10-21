import React, { useEffect, useState } from 'react';
import { getPersonalExpenses, addPersonalExpense, deletePersonalExpense } from './api';

export default function PersonalExpenses() {
  const [expenses, setExpenses] = useState([]);
  const [desc, setDesc] = useState('');
  const [amount, setAmount] = useState('');

  useEffect(() => {
    getPersonalExpenses().then(setExpenses);
  }, []);

  const handleAdd = async () => {
    const newExpense = { description: desc, amount: parseFloat(amount) };
    const saved = await addPersonalExpense(newExpense);
    setExpenses([...expenses, saved]);
    setDesc('');
    setAmount('');
  };

  const handleDelete = async (id) => {
    await deletePersonalExpense(id);
    setExpenses(expenses.filter(e => e.id !== id));
  };

  return (
    <div>
      <h2>Personal Expenses</h2>
      <ul>
        {expenses.map(e => (
          <li key={e.id}>
            {e.description} - ₹{e.amount}
            <button onClick={() => handleDelete(e.id)}>Delete</button>
          </li>
        ))}
      </ul>
      <input value={desc} onChange={e => setDesc(e.target.value)} placeholder="Description" />
      <input value={amount} onChange={e => setAmount(e.target.value)} placeholder="Amount" type="number" />
      <button onClick={handleAdd}>Add Expense</button>
    </div>
  );
}
