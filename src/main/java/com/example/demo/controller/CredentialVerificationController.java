package com.example.demo.controller;

import com.example.demo.dto.CredentialRequestDto;
import com.example.demo.dto.CredentialStatusDto;
import com.example.demo.model.Credential;
import com.example.demo.service.CredentialVerificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credentials")
public class CredentialVerificationController {

    private final CredentialVerificationService credentialVerificationService;

    public CredentialVerificationController(CredentialVerificationService credentialVerificationService) {
        this.credentialVerificationService = credentialVerificationService;
    }

    @PostMapping
    public ResponseEntity<Credential> registerCredential(@RequestBody CredentialRequestDto dto) {
        Credential credential = new Credential(dto.getEmployeeId(), dto.getCredentialId(),
                dto.getIssuer(), dto.getIssuedAt(), dto.getExpiresAt());
        credential.setMetadataJson(dto.getMetadataJson());
        
        Credential registered = credentialVerificationService.registerCredential(credential);
        return ResponseEntity.ok(registered);
    }

    @PostMapping("/{credentialId}/verify")
    public ResponseEntity<CredentialStatusDto> verifyCredential(@PathVariable String credentialId) {
        CredentialStatusDto status = credentialVerificationService.verifyCredential(credentialId);
        return ResponseEntity.ok(status);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Credential>> getCredentialsForEmployee(@PathVariable Long employeeId) {
        List<Credential> credentials = credentialVerificationService.getCredentialsForEmployee(employeeId);
        return ResponseEntity.ok(credentials);
    }

    @GetMapping("/{credentialId}")
    public ResponseEntity<Credential> getCredential(@PathVariable String credentialId) {
        return ResponseEntity.notFound().build();
    }
}