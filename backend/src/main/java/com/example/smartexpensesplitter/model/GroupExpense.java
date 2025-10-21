package com.example.smartexpensesplitter.model;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
public class GroupExpense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String groupName;
    private String description;
    private Double amount;
    @ElementCollection
    private List<String> members;
    @ElementCollection
    private Map<String, Double> split;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getGroupName() { return groupName; }
    public void setGroupName(String groupName) { this.groupName = groupName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public List<String> getMembers() { return members; }
    public void setMembers(List<String> members) { this.members = members; }
    public Map<String, Double> getSplit() { return split; }
    public void setSplit(Map<String, Double> split) { this.split = split; }
}
