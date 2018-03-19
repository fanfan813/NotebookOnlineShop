package com.amelin.shop.dao;

import com.amelin.shop.model.Account;

import java.util.List;

public interface AccountDAO {
    Account findById(int id);
    Account findByLogin(String login);
    void saveUser(Account user);
    void updateUser(Account user);
    void deleteUser(Account user);
    void deleteUserById(int id);
    List<Account> getAllUsers();
}
