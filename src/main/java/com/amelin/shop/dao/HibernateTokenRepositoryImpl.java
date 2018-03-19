package com.amelin.shop.dao;

import com.amelin.shop.model.PersistentLogin;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository("tokenRepositoryDao")
@Transactional
public class HibernateTokenRepositoryImpl extends AbstractDAO<String, PersistentLogin>
    implements PersistentTokenRepository {

    public void createNewToken(PersistentRememberMeToken token) {
        PersistentLogin login = new PersistentLogin();
        login.setLogin(token.getUsername());
        login.setSeries(token.getSeries());
        login.setToken(token.getTokenValue());
        login.setLastUsed(token.getDate());
        persist(login);
    }

    public void updateToken(String series, String token, Date date) {
        PersistentLogin login = getByKey(series);
        login.setToken(token);
        login.setLastUsed(date);
        getSession().update(login);
    }

    public PersistentRememberMeToken getTokenForSeries(String series) {
        try {
            Criteria criteria = createEntityCriteria();
            criteria.add(Restrictions.eq("series", series));
            PersistentLogin persistentLogin = (PersistentLogin)criteria.uniqueResult();

            String login = persistentLogin.getLogin();
            String token = persistentLogin.getToken();
            Date lastUsed = persistentLogin.getLastUsed();

            return new PersistentRememberMeToken(login, series, token, lastUsed);
        } catch (Exception e) {
            return null;
        }
    }

    public void removeUserTokens(String login) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("login", login));
        PersistentLogin persistentLogin = (PersistentLogin)criteria.uniqueResult();
        if (persistentLogin != null) {
            delete(persistentLogin);
        }
    }
}
