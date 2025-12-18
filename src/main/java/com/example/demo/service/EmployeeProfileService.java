package com.example.demo.service;

import com.example.demo.model.EmployeeProfile;
import com.example.demo.repository.EmployeeProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeProfileService {

    private final EmployeeProfileRepository repository;

    public EmployeeProfileService(EmployeeProfileRepository repository) {
        this.repository = repository;
    }

    public EmployeeProfile createEmployee(EmployeeProfile employee) {
        return repository.save(employee);
    }

    public EmployeeProfile getEmployeeById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public List<EmployeeProfile> getAllEmployees() {
        return repository.findAll();
    }

    public EmployeeProfile findByEmployeeId(String employeeId) {
        EmployeeProfile emp = repository.findByEmployeeId(employeeId);
        if (emp == null) {
            throw new RuntimeException("Employee not found");
        }
        return emp;
    }

    public EmployeeProfile updateEmployeeStatus(Long id, boolean active) {
        EmployeeProfile emp = getEmployeeById(id);
        emp.setActive(active);
        return repository.save(emp);
    }
}
