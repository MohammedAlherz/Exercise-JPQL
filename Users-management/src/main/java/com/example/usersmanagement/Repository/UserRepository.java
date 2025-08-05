package com.example.usersmanagement.Repository;

import com.example.usersmanagement.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findUserById(Integer id);

    @Query("select u from User u where u.userName=?1 and u.password=?2")
    User loginUser(String userName, String password);

    @Query("select u from User u where u.email=?1")
    User takeUserByEmail(String email);

    List<User> getUserByRole(String role);

    @Query("select u from User u where u.age>=?1")
    List<User> takeUserAgeOrAbove(Integer age);
}
