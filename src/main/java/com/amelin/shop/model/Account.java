package com.amelin.shop.model;

import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Generated;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "account", schema = "public", catalog = "shop")
@Transactional
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "account_id_generator")
    @SequenceGenerator(name = "account_id_generator", sequenceName = "account_account_id_seq")
    @Column(name = "account_id")
    private int accountId;

    @Basic
    @Column(name = "login")
    private String login;

    @Basic
    @Column(name = "password")
    private String password;

    @Basic
    @Column(name = "first_name")
    private String firstName;

    @Basic
    @Column(name = "last_name")
    private String lastName;

    @Basic
    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "account_type")
    private AccountTypeEnum accountType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "accountId")
    private Set<Comment> comments = new HashSet<Comment>(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "accountId")
    private Set<Order> orders = new HashSet<Order>(0);

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "cart", joinColumns = { @JoinColumn(name = "account_id") },
        inverseJoinColumns = { @JoinColumn(name = "notebook_id") })
    private Set<Notebook> cart = new HashSet<Notebook>(0);

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public AccountTypeEnum getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountTypeEnum accountType) {
        this.accountType = accountType;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Set<Notebook> getCart() {
        return cart;
    }

    public void setCart(Set<Notebook> cart) {
        this.cart = cart;
    }
}
