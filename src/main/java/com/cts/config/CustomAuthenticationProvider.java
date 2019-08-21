package com.cts.config;

import com.cts.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    DataSource dataSource;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement("select * from USER where USERNAME= ?");
            statement.setString(1, name);
            ResultSet results = statement.executeQuery();
            User user = new User();
            while (results.next()) {
                user.setName(results.getString("NAME"));
                user.setEmail(results.getString("EMAIL"));
                user.setUsername(results.getString("USERNAME"));
                user.setPassword(results.getString("PASSWORD"));
            }
            if (password.equals(user.getPassword())) {
                List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
                return new UsernamePasswordAuthenticationToken(
                        name, password, authorities);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
