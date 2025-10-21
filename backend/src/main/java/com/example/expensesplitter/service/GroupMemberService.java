package com.example.expensesplitter.service;

import com.example.expensesplitter.model.GroupMember;
import com.example.expensesplitter.repository.GroupMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupMemberService {
    private final GroupMemberRepository repo;

    public GroupMemberService(GroupMemberRepository repo) { this.repo = repo; }

    public List<GroupMember> listAll() { return repo.findAll(); }

    public GroupMember create(GroupMember gm) { return repo.save(gm); }
}
