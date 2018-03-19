package com.amelin.shop.service;

import com.amelin.shop.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service("UserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AccountService accountService;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Account account = accountService.findByLogin(login);

        if (account == null) {
            throw new UsernameNotFoundException("Incorrect login and/or password");
        }

        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_"
                + account.getAccountType().name());
        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
        grantedAuthorities.add(grantedAuthority);

        boolean enabled = true,
        accountNonExpired = true,
        credentialsNonExpired = true,
        nonLocked = true;
        User user = new User(account.getLogin(), account.getPassword(), enabled, accountNonExpired,
                credentialsNonExpired, nonLocked, grantedAuthorities);

        return user;
    }
}
