package com.example.usersmanagement.Service;

import com.example.usersmanagement.Api.ApiException;
import com.example.usersmanagement.Model.User;
import com.example.usersmanagement.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public void updateUser(Integer id, User user){
        User oldUser = userRepository.findUserById(id);

        if(oldUser == null){
            throw new ApiException("User not found!");
        }

        oldUser.setUserName(user.getUserName());
        oldUser.setName(user.getName());
        oldUser.setAge(user.getAge());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        oldUser.setRole(user.getRole());
        userRepository.save(oldUser);
    }

    public void deleteUser(Integer id){
        User user = userRepository.findUserById(id);

        if(user == null){
            throw new ApiException("User not found!");
        }

        userRepository.delete(user);
    }

    public void loginUser(String userName, String password){
        User user = userRepository.loginUser(userName,password);

        if(user == null){
            throw new ApiException("Invalid UserName or Password!");
        }
    }
    public User giveMeUserByEmail(String email){
        User user = userRepository.takeUserByEmail(email);

        if(user == null){
            throw new ApiException("User not found!");
        }
        return user;
    }

    public List<User> giveMeUserByRole(String role){
        List<User> userList = userRepository.getUserByRole(role);

        if(userList.isEmpty()){
            throw new ApiException("Users were not found");
        }
        return userList;
    }
    public List<User> giveMeUserAgeOrAbove(Integer age){
        List<User> userList = userRepository.takeUserAgeOrAbove(age);

        if(userList.isEmpty()){
            throw new ApiException("Users were not found");
        }
        return userList;
    }

}
