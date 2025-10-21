import React from 'react';

import { CssBaseline, ThemeProvider, createTheme, Box, Toolbar } from '@mui/material';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Sidebar from './components/Sidebar';
import Dashboard from './pages/Dashboard';
import Users from './pages/Users';
import Groups from './pages/Groups';
import PersonalExpenses from './pages/Expenses';
import Login from './pages/Login';
import Payments from './pages/Payments';

const theme = createTheme({
  palette: {
    mode: 'dark',
    primary: { main: '#00bcd4' },
    secondary: { main: '#ff4081' },
    background: { default: '#181c24', paper: '#23283b' },
  },
  typography: {
    fontFamily: 'Roboto, Arial, sans-serif',
  },
});


function App() {
  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <Router>
        <Box sx={{ display: 'flex' }}>
          <Sidebar />
          <Box component="main" sx={{ flexGrow: 1, p: 3 }}>
            <Toolbar />
            <Routes>
              <Route path='/' element={<Dashboard />} />
              <Route path='/users' element={<Users />} />
              <Route path='/groups' element={<Groups />} />
              <Route path='/personal-expenses' element={<PersonalExpenses />} />
              <Route path='/login' element={<Login />} />
              <Route path='/payments' element={<Payments />} />
            </Routes>
          </Box>
        </Box>
      </Router>
    </ThemeProvider>
  );
}

export default App;
