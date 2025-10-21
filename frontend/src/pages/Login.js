import React from 'react';
import { Container, Typography, Box, Button, Divider } from '@mui/material';
import GoogleIcon from '@mui/icons-material/Google';
import FacebookIcon from '@mui/icons-material/Facebook';

function Login() {
  const handleLogin = (type) => {
    // Placeholder for actual login logic
    alert(`Login with ${type} coming soon!`);
  };

  return (
    <Container maxWidth="xs" sx={{ mt: 8, p: 4, bgcolor: 'background.paper', borderRadius: 3, boxShadow: 3 }}>
      <Typography variant="h4" color="primary" align="center" gutterBottom>
        Login
      </Typography>
      <Box sx={{ display: 'flex', flexDirection: 'column', gap: 2, mt: 4 }}>
        <Button
          variant="contained"
          color="primary"
          startIcon={<GoogleIcon />}
          onClick={() => handleLogin('Google')}
        >
          Continue with Gmail
        </Button>
        <Button
          variant="contained"
          color="info"
          startIcon={<FacebookIcon />}
          onClick={() => handleLogin('Facebook')}
        >
          Continue with Facebook
        </Button>
        <Divider sx={{ my: 2 }}>or</Divider>
        <Button
          variant="outlined"
          color="secondary"
          onClick={() => handleLogin('Guest')}
        >
          Continue as Guest
        </Button>
      </Box>
    </Container>
  );
}

export default Login;
