package ru.vlbb.workday.model;

import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

public class Employee extends AbstractNamedEntity {

    private String login;

    private String password;

    private boolean enabled = true;

    private Date registered = new Date();

    private Set<Role> roles;

    public Employee(Integer id, String name, String login, String password, Role role, Role... roles) {
        this (id, name, login, password, true, EnumSet.of(role, roles));
    }

    public Employee(Integer id, String name, String login, String password, boolean enabled, Set<Role> roles) {
        super(id, name);
        this.login = login;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User (" +
                "id=" + id +
                ", email=" + login +
                ", name=" + name +
                ", enabled=" + enabled +
                ", roles=" + roles +
                ')';
    }
}