package com.amelin.shop.dao;

import com.amelin.shop.model.Account;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository("AccountDAO")
public class AccountDAOImpl extends AbstractDAO<Integer, Account> implements AccountDAO {
    public Account findById(int id) {
        Criteria criteria = getSession().createCriteria(Account.class);
        criteria.add(Restrictions.eq("account_id", id));
        return (Account)criteria.uniqueResult();
    }

    public Account findByLogin(String login) {
        Criteria criteria = getSession().createCriteria(Account.class);
        criteria.add(Restrictions.eq("login", login));
        return (Account)criteria.uniqueResult();
    }

    public void saveUser(Account user) {
        persist(user);
    }

    public void updateUser(Account user) {
        getSession().update(user);
    }

    public void deleteUser(Account user) {
        delete(user);
    }

    public void deleteUserById(int id) {
        Query query = getSession().createSQLQuery("DELETE FROM user WHERE user_id = :id");
        query.setInteger("id", id);
        query.executeUpdate();
    }

    public List<Account> getAllUsers() {
        Criteria criteria = getSession().createCriteria(Account.class);
        return (List<Account>)criteria.list();
    }
}
