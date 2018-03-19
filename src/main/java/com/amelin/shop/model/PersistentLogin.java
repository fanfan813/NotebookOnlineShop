package com.amelin.shop.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "persistent_login", schema = "public", catalog = "shop")
public class PersistentLogin {
    @Id
    private String series;

    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @Column(name = "token", unique = true, nullable = false)
    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_used")
    private Date lastUsed;

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(Date lastUsed) {
        this.lastUsed = lastUsed;
    }
}
