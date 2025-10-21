import React, { useState, useEffect } from 'react';
import { Typography, Container, Box, TextField, Button } from '@mui/material';
import CartoonAvatars from '../components/CartoonAvatars';
import BillScanner from '../components/BillScanner';
import { getGroupExpenses, addGroupExpense } from '../api';
 const handleDeleteGroup = (groupNameToDelete) => {
    setGroups(groups.filter(g => g.name !== groupNameToDelete));
    if (selectedGroup && selectedGroup.name === groupNameToDelete) {
      setSelectedGroup(null);
    }
    setExpenses(expenses.filter(exp => exp.group !== groupNameToDelete));
  };


const initialGroups = [
  { name: 'Trip to Goa', members: ['Alice', 'Bob', 'Charlie', 'David'] },
  { name: 'Project Team', members: ['Eve', 'Frank', 'Grace'] },
  { name: 'Family', members: ['Mom', 'Dad', 'Me', 'Sis', 'Bro'] },
];

function Groups() {
  const [groups, setGroups] = useState(initialGroups);
  const [groupName, setGroupName] = useState('');
  const [memberNames, setMemberNames] = useState('');
  const [selectedGroup, setSelectedGroup] = useState(null);
  const [expenseAmount, setExpenseAmount] = useState('');
  const [expenseDesc, setExpenseDesc] = useState('');
  const [expenses, setExpenses] = useState([]);
  const [splitType, setSplitType] = useState('equal');
  const [customSplits, setCustomSplits] = useState({});
  const [scannedText, setScannedText] = useState('');

  const handleAddGroup = (e) => {
    e.preventDefault();
    if (groupName && memberNames) {
      const membersArr = memberNames.split(',').map(m => m.trim()).filter(Boolean);
      setGroups([...groups, { name: groupName, members: membersArr }]);
      setGroupName('');
      setMemberNames('');
    }
  };

  const handleAddExpense = (e) => {
    e.preventDefault();
    if (selectedGroup && expenseAmount && expenseDesc) {
      let split = {};
      if (splitType === 'equal') {
        const perMember = parseFloat(expenseAmount) / selectedGroup.members.length;
        selectedGroup.members.forEach(m => {
          split[m] = perMember;
        });
      } else {
        split = { ...customSplits };
      }
      setExpenses([...expenses, {
        group: selectedGroup.name,
        amount: parseFloat(expenseAmount),
        desc: expenseDesc,
        members: selectedGroup.members,
        split
      }]);
      setExpenseAmount('');
      setExpenseDesc('');
      setCustomSplits({});
    }
  };

  // Calculate split for each expense
  const getSplitDetails = (expense) => {
    return expense.members.map(m => `${m}: ₹${expense.split[m]?.toFixed(2) || '0.00'}`).join(', ');
  };

  return (
    <Container>
      <Typography variant="h4" sx={{ mt: 4, mb: 2 }} color="primary">
        Groups
      </Typography>
      <Box component="form" onSubmit={handleAddGroup} sx={{ mb: 4, display: 'flex', gap: 2, flexWrap: 'wrap' }}>
        <TextField label="Group Name" value={groupName} onChange={e => setGroupName(e.target.value)} required />
        <TextField label="Members (comma separated)" value={memberNames} onChange={e => setMemberNames(e.target.value)} required />
        <Button type="submit" variant="contained" color="secondary">Add Group</Button>
      </Box>
      {groups.map((group, idx) => (
        <Box key={idx} sx={{ mb: 4, p: 2, bgcolor: 'background.paper', borderRadius: 2, boxShadow: 3 }}>
          <Typography variant="h6" color="secondary">{group.name}</Typography>
          <CartoonAvatars count={group.members.length} />
          <Typography variant="body2" sx={{ mt: 1 }}>Members: {group.members.join(', ')}</Typography>
          <Box sx={{ display: 'flex', gap: 2, mt: 2 }}>
            <Button variant="outlined" onClick={() => setSelectedGroup(group)}>
              Start Personal Expenses for this Group
            </Button>
            <Button variant="contained" color="error" onClick={() => handleDeleteGroup(group.name)}>
              Delete Group
            </Button>
          </Box>
        </Box>
      ))}
      {selectedGroup && (
        <Box sx={{ mt: 4, p: 2, bgcolor: 'background.paper', borderRadius: 2, boxShadow: 3 }}>
          <Typography variant="h6" color="primary">Selected Group: {selectedGroup.name}</Typography>
          <Typography variant="body2">Members: {selectedGroup.members.join(', ')}</Typography>
          <Box component="form" onSubmit={handleAddExpense} sx={{ mt: 2, display: 'flex', gap: 2, flexWrap: 'wrap' }}>
          <BillScanner onScanComplete={setScannedText} />
          {scannedText && (
            <Box sx={{ mt: 2, p: 2, bgcolor: 'background.paper', borderRadius: 2, boxShadow: 1 }}>
              <Typography variant="subtitle1" color="secondary">Bill Data Preview:</Typography>
              <Typography variant="body2" sx={{ whiteSpace: 'pre-wrap' }}>{scannedText}</Typography>
            </Box>
          )}
            <TextField label="Expense Amount" type="number" value={expenseAmount} onChange={e => setExpenseAmount(e.target.value)} />
            <TextField label="Description" value={expenseDesc} onChange={e => setExpenseDesc(e.target.value)} />
            <Box sx={{ minWidth: 180 }}>
              <label style={{ marginRight: 8 }}>Split Type:</label>
              <select value={splitType} onChange={e => setSplitType(e.target.value)} style={{ padding: '8px', borderRadius: '4px', border: '1px solid #ccc' }}>
                <option value="equal">Equally</option>
                <option value="custom">Custom</option>
              </select>
            </Box>
            {splitType === 'custom' && selectedGroup && (
              <Box sx={{ display: 'flex', flexDirection: 'column', gap: 1, mt: 2 }}>
                <Typography variant="body2" color="secondary">Enter amount for each member:</Typography>
                {selectedGroup.members.map(m => (
                  <TextField
                    key={m}
                    label={m}
                    type="number"
                    value={customSplits[m] || ''}
                    onChange={e => {
                      const value = e.target.value;
                      setCustomSplits(s => ({ ...s, [m]: value ? parseFloat(value) : '' }));
                    }}
                    // required removed
                  />
                ))}
              </Box>
            )}
            <Button type="submit" variant="contained" color="primary">Add Expense & Split</Button>
          </Box>
          {expenses.filter(exp => exp.group === selectedGroup.name).length > 0 && (
            <Box sx={{ mt: 3 }}>
              <Typography variant="h6" color="secondary">Group Expenses & Split</Typography>
              {expenses.filter(exp => exp.group === selectedGroup.name).map((exp, idx) => (
                <Box key={idx} sx={{ p: 2, mb: 2, bgcolor: 'background.paper', borderRadius: 2, boxShadow: 1 }}>
                  <Typography>Amount: ₹{exp.amount}</Typography>
                  <Typography>Description: {exp.desc}</Typography>
                  <Typography>Split: {getSplitDetails(exp)}</Typography>
                </Box>
              ))}
            </Box>
          )}
        </Box>
      )}
    </Container>
  );
}

export default Groups;

