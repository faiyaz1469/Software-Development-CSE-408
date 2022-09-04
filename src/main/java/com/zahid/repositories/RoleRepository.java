package com.zahid.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zahid.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    
}
