package net.onlineStore.services;

import net.onlineStore.entities.Profile;
import net.onlineStore.entities.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface ProfileService {
    Profile saveProfile (Profile profile);

    Profile findById(Long id);

    Profile editUser(Set<Role> roles, Profile profile);

    Profile findByLogin (String login);

    Profile findByEmail (String email);

    Profile findByPhone (String phone);

    Page<Profile> findAll(Pageable pageable);

    Page<Profile> searchUsers(String query, Pageable pageable);
}
