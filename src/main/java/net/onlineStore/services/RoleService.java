package net.onlineStore.services;

import net.onlineStore.entities.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> getAllRoles();

    Role findById(Long id);

    void save(Set<Role> newRole);
}
