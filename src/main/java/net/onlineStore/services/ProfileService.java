package net.onlineStore.services;

import net.onlineStore.entities.Profile;

import java.util.List;

public interface ProfileService {
    Profile saveProfile (Profile profile);

    Profile findByLogin (String login);

    Profile findByEmail (String email);

    Profile findByPhone (String phone);

    List<Profile> findAll();
}
