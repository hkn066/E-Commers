package com.challenge.projectchallenge.dataAccess;

import com.challenge.projectchallenge.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByRole(String role);

    boolean existsByRole(String role);
}
