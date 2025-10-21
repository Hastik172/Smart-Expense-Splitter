import React, { useState } from 'react';
import { Box, Button, Typography, CircularProgress } from '@mui/material';
import Tesseract from 'tesseract.js';

function BillScanner({ onScanComplete }) {
  const [image, setImage] = useState(null);
  const [loading, setLoading] = useState(false);
  const [result, setResult] = useState('');

  const handleFileChange = (e) => {
    if (e.target.files && e.target.files[0]) {
      setImage(e.target.files[0]);
      setResult('');
    }
  };

  const handleScan = async () => {
    if (!image) return;
    setLoading(true);
    const { data } = await Tesseract.recognize(image, 'eng');
    setResult(data.text);
    setLoading(false);
    if (onScanComplete) onScanComplete(data.text);
  };

  return (
    <Box sx={{ p: 2, bgcolor: 'background.paper', borderRadius: 2, boxShadow: 2, mb: 2 }}>
      <Typography variant="h6" color="primary" sx={{ mb: 2 }}>
        Scan Bill (OCR)
      </Typography>
      <input type="file" accept="image/*" onChange={handleFileChange} />
      <Button variant="contained" color="secondary" sx={{ mt: 2 }} onClick={handleScan} disabled={!image || loading}>
        {loading ? <CircularProgress size={24} /> : 'Scan Bill'}
      </Button>
      {result && (
        <Box sx={{ mt: 2 }}>
          <Typography variant="subtitle1" color="secondary">Extracted Text:</Typography>
          <Typography variant="body2" sx={{ whiteSpace: 'pre-wrap' }}>{result}</Typography>
        </Box>
      )}
    </Box>
  );
}

export default BillScanner;
