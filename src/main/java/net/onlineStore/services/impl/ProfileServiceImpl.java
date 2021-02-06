package net.onlineStore.services.impl;

import lombok.extern.slf4j.Slf4j;
import net.onlineStore.Constants;
import net.onlineStore.entities.Profile;
import net.onlineStore.entities.Role;
import net.onlineStore.model.CurrentProfile;
import net.onlineStore.repositories.ProfileRepository;
import net.onlineStore.services.ProfileService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class ProfileServiceImpl implements ProfileService, UserDetailsService {
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;

    public ProfileServiceImpl(ProfileRepository profileRepository, PasswordEncoder passwordEncoder) {
        this.profileRepository = profileRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private Profile findProfile(String anyUniqueId) {
        Profile profile = profileRepository.findByLogin(anyUniqueId);
        if (profile == null) {
            profile = profileRepository.findByEmail(anyUniqueId);
            if (profile == null) {
                profile = profileRepository.findByPhone(anyUniqueId);
                if (profile == null) {
                    Optional<Profile> profileById = profileRepository.findById(Long.parseLong(anyUniqueId));
                    if (profileById.isPresent()) {
                        profile = profileById.get();
                    }
                }
            }
        }
        return profile;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Profile profile = findProfile(username);
        if (profile != null) {
            return new CurrentProfile(profile);
        } else {
            log.error("Profile not found by " + username);
            throw new UsernameNotFoundException("Profile not found by " + username);
        }
    }

    public Profile saveProfile(Profile profile) {
        Profile getProfile = findById(profile.getId());
        if (profile.getRoles() == null){
            profile.setRoles(Collections.singleton(new Role(1L, Constants.USER)));
        }
        if (!profile.getPassword().isEmpty()) {
            profile.setPassword(passwordEncoder.encode(profile.getPassword()));
        } else {
            profile.setPassword(getProfile.getPassword());
        }
        return profileRepository.save(profile);
    }

    @Override
    public Profile findById(Long id) {
        Optional<Profile> profile = profileRepository.findById(id);
        return profile.orElse(null);
    }

    @Override
    public Profile editUser(Set<Role> roles, Profile profile) {
        profile.setRoles(roles);
        profile.setPassword(passwordEncoder.encode(profile.getPassword()));
        profileRepository.save(profile);
        return null;
    }

    @Override
    public Profile findByLogin(String login) {
        return profileRepository.findByLogin(login);
    }

    @Override
    public Profile findByEmail(String email) {
        return profileRepository.findByEmail(email);
    }

    @Override
    public Profile findByPhone(String phone) {
        return profileRepository.findByPhone(phone);
    }

    @Override
    public Page<Profile> findAll(Pageable pageable) {
        return profileRepository.findAll(pageable);
    }

    @Override
    public Page<Profile> searchUsers(String query, Pageable pageable) {
        return profileRepository.searchUsers(query, pageable);
    }
}
