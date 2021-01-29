package net.onlineStore.repositories;

import net.onlineStore.entities.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Optional<Profile> findById(Long id);

    Profile findByEmail(String email);

    Profile findByLogin(String login);

    Profile findByPhone(String phone);

    @Query(value = "SELECT us.* FROM Profile us " +
            "WHERE us.surname ILIKE CONCAT('%',:query,'%')" +
            " or us.email ILIKE CONCAT('%',:query,'%') " +
            " or us.phone ILIKE CONCAT('%',:query,'%') " +
            " or us.city ILIKE CONCAT('%',:query,'%') order by us.surname", nativeQuery = true)
    Page<Profile> searchUsers(@Param("query") String query, Pageable pageable);
}
