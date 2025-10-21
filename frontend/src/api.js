
import axios from 'axios';
const BASE_URL = 'http://localhost:8080/api';

// Group Expenses
export const getGroupExpenses = async () => (await axios.get(`${BASE_URL}/group-expenses`)).data;
export const addGroupExpense = async (expense) => (await axios.post(`${BASE_URL}/group-expenses`, expense)).data;
export const deleteGroupExpense = async (id) => await axios.delete(`${BASE_URL}/group-expenses/${id}`);

// Personal Expenses
export const getPersonalExpenses = async () => (await axios.get(`${BASE_URL}/expenses`)).data;
export const addPersonalExpense = async ({ amount, description, currency = "USD", payerId = 1 }) => {
  const res = await axios.post(`${BASE_URL}/expenses`, {
    amount,
    currency,
    description,
    payerId
  });
  return res.data;
};
export const deletePersonalExpense = async (id) => await axios.delete(`${BASE_URL}/expenses/${id}`);

export const getUsers = async () => {
  const res = await axios.get(`${BASE_URL}/users`);
  return res.data;
};

export const addUser = async (user) => {
  const res = await axios.post(`${BASE_URL}/users`, user);
  return res.data;
};
