package com.example.expensesplitter.controller;

import com.example.expensesplitter.dto.GroupDto;
import com.example.expensesplitter.model.ExpenseGroup;
import com.example.expensesplitter.model.User;
import com.example.expensesplitter.service.GroupService;
import com.example.expensesplitter.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/groups")
public class GroupController {
    private final GroupService groupService;
    private final UserService userService;

    public GroupController(GroupService groupService, UserService userService) {
        this.groupService = groupService;
        this.userService = userService;
    }

    @GetMapping
    public List<GroupDto> list() {
        return groupService.listAll().stream().map(g -> {
            GroupDto d = new GroupDto();
            d.id = g.getId(); d.name = g.getName(); d.description = g.getDescription(); d.currency = g.getCurrency();
            d.createdBy = g.getCreatedBy() != null ? g.getCreatedBy().getId() : null;
            return d;
        }).collect(Collectors.toList());
    }

    @PostMapping
    public GroupDto create(@RequestBody GroupDto dto) {
        ExpenseGroup g = new ExpenseGroup();
        g.setName(dto.name); g.setDescription(dto.description); g.setCurrency(dto.currency == null ? "USD" : dto.currency);
        if (dto.createdBy != null) {
            User u = userService.findById(dto.createdBy);
            g.setCreatedBy(u);
        }
        ExpenseGroup saved = groupService.create(g);
        GroupDto out = new GroupDto(); out.id = saved.getId(); out.name = saved.getName(); out.description = saved.getDescription(); out.currency = saved.getCurrency();
        out.createdBy = saved.getCreatedBy() != null ? saved.getCreatedBy().getId() : null;
        return out;
    }
}
