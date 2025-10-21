package com.example.expensesplitter.controller;

import com.example.expensesplitter.dto.GroupMemberDto;
import com.example.expensesplitter.model.ExpenseGroup;
import com.example.expensesplitter.model.GroupMember;
import com.example.expensesplitter.model.User;
import com.example.expensesplitter.service.GroupMemberService;
import com.example.expensesplitter.service.GroupService;
import com.example.expensesplitter.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/group-members")
public class GroupMemberController {
    private final GroupMemberService gmService;
    private final GroupService groupService;
    private final UserService userService;

    public GroupMemberController(GroupMemberService gmService, GroupService groupService, UserService userService) {
        this.gmService = gmService; this.groupService = groupService; this.userService = userService;
    }

    @GetMapping
    public List<GroupMemberDto> list() {
        return gmService.listAll().stream().map(gm -> {
            GroupMemberDto d = new GroupMemberDto(); d.id = gm.getId(); d.groupId = gm.getGroup() != null ? gm.getGroup().getId() : null; d.userId = gm.getUser() != null ? gm.getUser().getId() : null; d.role = gm.getRole(); return d;
        }).collect(Collectors.toList());
    }

    @PostMapping
    public GroupMemberDto create(@RequestBody GroupMemberDto dto) {
        GroupMember gm = new GroupMember();
        if (dto.groupId != null) { ExpenseGroup g = groupService.findById(dto.groupId); gm.setGroup(g); }
        if (dto.userId != null) { User u = userService.findById(dto.userId); gm.setUser(u); }
        gm.setRole(dto.role == null ? "member" : dto.role);
        GroupMember saved = gmService.create(gm);
        GroupMemberDto d = new GroupMemberDto(); d.id = saved.getId(); d.groupId = saved.getGroup() != null ? saved.getGroup().getId() : null; d.userId = saved.getUser() != null ? saved.getUser().getId() : null; d.role = saved.getRole(); return d;
    }
}
