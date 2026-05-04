package com.example.AvLabBD2.admin;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {
    
    private final AdminRepository adminRepository;

    public boolean autenticar(String login, String senha) {
        return adminRepository.validarLoginNativo(login, senha) > 0;
    }
}
