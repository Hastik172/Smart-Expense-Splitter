import React, { useState, useEffect } from 'react';
import { Typography, Container, Box, TextField, Button } from '@mui/material';
// ...existing code...
import BillScanner from '../components/BillScanner';
import { getGroupExpenses, addGroupExpense } from '../api';

// Simple cartoon avatar SVG
const AvatarSVG = ({ color }) => (
  <svg width="48" height="48" viewBox="0 0 48 48" fill="none">
    <circle cx="24" cy="24" r="20" fill={color} stroke="#fff" strokeWidth="2" />
    <ellipse cx="24" cy="28" rx="10" ry="7" fill="#fff" />
    <circle cx="24" cy="20" r="8" fill="#fff" />
    <circle cx="20" cy="19" r="2" fill="#333" />
    <circle cx="28" cy="19" r="2" fill="#333" />
    <ellipse cx="24" cy="23" rx="3" ry="2" fill="#333" />
  </svg>
);

const colors = ['#00bcd4', '#ff4081', '#ffc107', '#8bc34a', '#e040fb', '#ff5722', '#607d8b'];

function CartoonAvatars({ count }) {
  return (
    <Box sx={{ display: 'flex', gap: 2, mt: 2 }}>
      {[...Array(count)].map((_, i) => (
        <Box key={i} sx={{ transition: 'transform 0.5s', transform: 'scale(1)', ':hover': { transform: 'scale(1.15)' } }}>
          <AvatarSVG color={colors[i % colors.length]} />
        </Box>
      ))}
    </Box>
  );
}

export default CartoonAvatars;
