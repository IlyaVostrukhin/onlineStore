package net.onlineStore.repositories;

import net.onlineStore.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Profile findByEmail(String email);

    Profile findByLogin(String login);

    Profile findByPhone(String phone);
}
