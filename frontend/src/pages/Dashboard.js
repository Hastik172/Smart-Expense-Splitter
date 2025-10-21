import React from 'react';
import { Typography, Container } from '@mui/material';


import { Card, CardContent, Grid } from '@mui/material';

const stats = [
  { label: 'Total Users', value: 3 },
  { label: 'Groups', value: 3 },
  { label: 'Personal Expenses', value: 12 },
  { label: 'Payments', value: 7 },
];

function Dashboard() {
  return (
    <Container>
      <Typography variant="h3" sx={{ mt: 4, mb: 2 }} color="primary">
        Smart Expense Splitter
      </Typography>
      <Typography variant="h5" color="secondary">
        Welcome to your futuristic dashboard!
      </Typography>
      <Grid container spacing={3} sx={{ mt: 2 }}>
        {stats.map((stat, idx) => (
          <Grid item xs={12} sm={6} md={3} key={idx}>
            <Card sx={{ bgcolor: 'background.paper', boxShadow: 3 }}>
              <CardContent>
                <Typography variant="h6" color="primary">{stat.label}</Typography>
                <Typography variant="h4" color="secondary">{stat.value}</Typography>
              </CardContent>
            </Card>
          </Grid>
        ))}
      </Grid>
    </Container>
  );
}

export default Dashboard;
