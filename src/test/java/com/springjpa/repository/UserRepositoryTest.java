package com.springjpa.repository;

import com.springjpa.domain.Users;
import com.springjpa.domain.enums.eRole;
import org.apache.catalina.User;
import org.assertj.core.api.Assertions;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired private UserRepository repo;

    String newName = "Allyson";
    String password = "vxh8jkdq";
    String email = "allyson2.acs@gmail.com";

    @Test
    public void saveTest(){
        Optional<Users> result = repo.findById(1L);
        Users user = result.get();
        if (user == null) {
            user = new Users();
            user.setRole(eRole.ADMINISTRATOR);
            user.setEmail(email);
            user.setName(newName);
            user.setPassword(password);
        }
        Users u = repo.save(user);
        Assertions.assertThat(u.getId()).isPositive();
    }
    @Test
    public void updateTest(){
        Users user = repo.findById(1L).get();
        newName = user.getName() + "-" + user.getId().toString();
        if (user == null) {
            user = new Users();
            user.setId(1L);
            user.setRole(eRole.ADMINISTRATOR);
            user.setEmail(email);
            user.setPassword(password);

        }
        user.setName(newName);
        Users u = repo.save(user);
        Assertions.assertThat(u.getName()).isEqualTo(newName);
    }
    @Test
    public void getByIdTest(){
        Optional<Users> result = repo.findById(1L);
        Assertions.assertThat(result.get().getPassword()).isEqualTo(password);
    }
    @Test
    public void listTest(){
        List<Users> result = repo.findAll() ;
        Assertions.assertThat(result.isEmpty()).isFalse();
    }
    @Test
    public void loginTest(){
        Optional<Users> result = repo.login(email, password);
        Users user = result.get();
        Assertions.assertThat(user.getId()).isEqualTo(1L);
    }
}
