package net.onlineStore.model;

import net.onlineStore.Constants;
import net.onlineStore.entities.Profile;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

public class CurrentProfile extends User {

    private static final long serialVersionUID = -8760105949057652635L;
    private final Long id;
    private final String name;

    public CurrentProfile(Profile profile) {
        super(profile.getId().toString(), profile.getPassword(), true, true, true,
                true, Collections.singleton(new SimpleGrantedAuthority(Constants.USER)));
        this.id = profile.getId();
        this.name = profile.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Current profile [id=%s, username=%s]", id, getUsername());
    }
}
