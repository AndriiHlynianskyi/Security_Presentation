package com.ahl.x509;

import com.ahl.common.TestUserDetails;
import com.ahl.persistence.User;
import com.ahl.persistence.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class X509UserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userDao.findByUsername(userName);

        TestUserDetails userDetails = new TestUserDetails();
        if (user == null) {
            throw new UsernameNotFoundException(userName + " not found");
        }
        userDetails.setUserName(user.getUsername());
        userDetails.setPassword("");
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(r -> authorities.add(new SimpleGrantedAuthority(r.getRolename())));
        userDetails.setAuthorities(authorities);

        return userDetails;
    }
}
