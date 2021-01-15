package com.maitech.security;

import com.maitech.models.UserModel;
import com.maitech.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserModel user = null; //userRepository.findByUsername(login);


        if (login.contains("@"))
            user = userRepository.findByEmail(login);
        else {
            try {
                int phone = Integer.parseInt(login);
                user = userRepository.findByPhone(login);
            }
            catch (NumberFormatException numberFormatException) {
                user = userRepository.findByUsername(login);
            }
        }


        if (user == null)
            throw new UsernameNotFoundException(login);

        String subject = user.getUsername();
        String password = user.getPassword();
        String role = user.getRoleModel().getCode();

        return new CustomUserDetails(subject, password, role);
    }
}