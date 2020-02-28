package com.project.toshop.model;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table(name="users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    @NotEmpty(message = "Please provide your first name")
    private String name;

    @Column(name = "surname")
    @NotEmpty(message = "Please provide your surname")
    private  String surname;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

 /*   @Transient
    private String passwordConfirm;

  */

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    //@Email(message = "Please provide a valid e-mail")
    //@NotEmpty(message = "Please provide an e-mail")
    private String email;

   /////////////////////////////
    @Column(name = "enabled")
    private boolean enabled;



    @Column(name = "confirmation_token")
    private String confirmationToken;
    ///////////////////////

    @Column(name = "Type")
    private Integer type;

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    private Set<Role> roles;

    /*   @ManyToMany
       private Set<Role> roles;
   */
    public User() {}

    public User(String name, String surname, String username, String password, String address, String phone, Integer type){
        this.name = name;
        this.surname= surname;
        this.username = username;
        this.password = password;
        this.address= address;
        this.phone = phone;
        this.type= type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    /*
        public String getPasswordConfirm() {
            return passwordConfirm;
        }

        public void setPasswordConfirm(String passwordConfirm) {
            this.passwordConfirm = passwordConfirm;
        }

     */
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    ////////////////////////////////////

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }



}
