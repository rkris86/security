package com.cts.dao;

import com.cts.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public void register(User user) {

        String sql = "insert into user(NAME,EMAIL,USERNAME,PASSWORD) values (?,?,?,?)";

        jdbcTemplate.update(sql,new Object[]{user.getName(),user.getEmail(),user.getUsername(),user.getPassword()});

        jdbcTemplate.query("SELECT * FROM USER",
                (ResultSet rs, int rowNum) -> {
                    System.out.print(rs.getString("name"));
                    return rowNum;
                });
    }

    @Override
    public User updateUser(User user) {
        String sql = "update USER SET NAME =?,EMAIL=?,USERNAME=?,PASSWORD=? where USERNAME = ?";

        jdbcTemplate.update(sql,new Object[]{user.getName(),user.getEmail(),user.getUsername(),user.getPassword(),user.getUsername()});
        jdbcTemplate.query("SELECT * FROM USER",
                (ResultSet rs, int rowNum) -> {
                    System.out.println(rs.getString("name"));
                    return rowNum;
                });
        return user;
    }


}
