package net.onlineStore.model;

import net.onlineStore.entities.Profile;
import net.onlineStore.entities.Role;
import org.springframework.security.core.userdetails.User;

import java.util.Set;

public class CurrentProfile extends User {

    private static final long serialVersionUID = -8760105949057652635L;
    private final Long id;
    private final String fio;
    private final String name;
    private final String recipient;
    private final Set<Role> roles;

    public CurrentProfile(Profile profile) {
        super(profile.getId().toString(), profile.getPassword(), true, true,
                true, true, profile.getRoles());
        this.id = profile.getId();
        this.fio = profile.getSurName() + " " + profile.getName() + " " + profile.getLastName();
        this.name = profile.getName();
        this.recipient = "тел: " + profile.getPhone() + ", e-mail: " + profile.getEmail() +
                ", индекс: " + profile.getPostcode() + ", город: " + profile.getCity() + ", адрес: " + profile.getAddress();
        this.roles = profile.getRoles();
    }

    public Long getId() {
        return id;
    }

    public String getFio() {
        return fio;
    }

    public String getName() {
        return name;
    }

    public String getRecipient() {
        return recipient;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    @Override
    public String toString() {
        return String.format("Current profile [id=%s, username=%s]", id, getUsername());
    }
}
