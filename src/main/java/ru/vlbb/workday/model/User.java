package ru.vlbb.workday.model;

import org.springframework.util.CollectionUtils;

import java.util.*;

public class User extends AbstractNamedEntity {

    private String login;

    private String password;

    private boolean enabled = true;

    private Date registered = new Date();

    private Set<Role> roles;

    public User(Integer id, String name, String login, String password, Role... roles) {
        this(id, name, login, password, true, Arrays.asList((roles)));
    }

    public User(Integer id, String name, String login, String password, boolean enabled, Collection<Role> roles) {
        super(id, name);
        this.login = login;
        this.password = password;
        this.enabled = enabled;
        setRoles(roles);
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

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
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