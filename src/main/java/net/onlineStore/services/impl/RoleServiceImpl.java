package net.onlineStore.services.impl;

import net.onlineStore.entities.Role;
import net.onlineStore.repositories.RoleRepository;
import net.onlineStore.services.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).orElse(new Role(1L, "ROLE_USER"));
    }

    @Override
    public void save(Set<Role> newRole) {
        for (Role role : newRole) {
            roleRepository.save(role);
        }
    }
}
