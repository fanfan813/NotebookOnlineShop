package com.amelin.shop.service;

import com.amelin.shop.dao.AccountDAO;
import com.amelin.shop.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("AccountService")
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDAO dao;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Account findById(int id) {
        return dao.findById(id);
    }

    public Account findByLogin(String login) {
        return dao.findByLogin(login);
    }

    public void saveUser(Account user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        dao.saveUser(user);
    }

    public void updateUser(Account user) {
        Account account = dao.findById(user.getAccountId());
        if (account != null) {

        }
        dao.updateUser(user);
    }

    public void deleteUser(Account user) {
        dao.deleteUser(user);
    }

    public void deleteUserById(int id) {
        dao.deleteUserById(id);
    }

    public List<Account> getAllUsers() {
        return dao.getAllUsers();
    }
}