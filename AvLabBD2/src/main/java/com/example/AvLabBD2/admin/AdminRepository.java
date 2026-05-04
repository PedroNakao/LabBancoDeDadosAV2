package com.example.AvLabBD2.admin;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByLogin(String login);
    @Query(value = "SELECT COUNT(*) FROM admin_usuario WHERE login = :login AND senha = :senha", nativeQuery = true)
    int validarLoginNativo(@Param("login") String login, @Param ("senha") String senha);
}
