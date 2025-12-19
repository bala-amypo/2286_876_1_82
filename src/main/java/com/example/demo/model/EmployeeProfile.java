package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "employee_profiles",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "employeeId"),
                @UniqueConstraint(columnNames = "email")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String employeeId;

    private String fullName;

    @Column(nullable = false)
    private String email;

    private String teamName;
    private String role;
    private Boolean active = true;

    private LocalDateTime createdAt = LocalDateTime.now();
}
