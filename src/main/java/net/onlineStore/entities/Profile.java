package net.onlineStore.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "account")
public class Profile extends AbstractEntity<Long> {
    private static final long serialVersionUID = -5873271526854116984L;

//    ToDo: rename table and seq to "profile"
    @Id
    @SequenceGenerator(name = "PROFILE_ID_GENERATOR", sequenceName = "ACCOUNT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROFILE_ID_GENERATOR")
    @Column(unique = true, nullable = false)
    private Long id;

    @Column
    @Size(min=2, message = "Не меньше 2 символов")
    private String name;

    @Column
    @Size(min=2, message = "Не меньше 2 символов")
    private String surName;

    @Column
    @Size(min=2, message = "Не меньше 2 символов")
    private String lastName;

    @Column
    @Size(min=8, message = "Не меньше 8 символов")
    private String email;

    @Column
    @Size(min=11, message = "Не меньше 11 символов")
    private String phone;

    @Column
    @Size(min=2, message = "Не меньше 2 символов")
    private String city;

    @Column
    @Size(min=6, message = "Не меньше 6 символов")
    private Integer postcode;

    @Column
    @Size(min=15, message = "Не меньше 15 символов")
    private String address;

    @Column
    @Size(min=5, message = "Не меньше 5 символов")
    private String login;

    @Column
    @Size(min=8, message = "Не меньше 8 символов")
    private String password;

    @Transient
    private String confirmPassword;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getPostcode() {
        return postcode;
    }

    public void setPostcode(Integer postcode) {
        this.postcode = postcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
