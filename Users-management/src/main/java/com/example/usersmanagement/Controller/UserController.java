package com.example.usersmanagement.Controller;

import com.example.usersmanagement.Api.ApiResponse;
import com.example.usersmanagement.Model.User;
import com.example.usersmanagement.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-management")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }
    @PostMapping("/add")
    public ResponseEntity<?> addUsers(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User added Successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id,@Valid @RequestBody User user, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.updateUser(id,user);
        return ResponseEntity.status(200).body(new ApiResponse("User Updated Successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.status(200).body(new ApiResponse("User Deleted Successfully"));
    }

    @PostMapping("/login/{username}/{password}")
    public ResponseEntity<?> loginUser(@PathVariable String username, @PathVariable String password){
        userService.loginUser(username,password);
        return ResponseEntity.status(200).body(new ApiResponse("successful login"));
    }

    @GetMapping("/get-user-email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email){
        return ResponseEntity.status(200).body(userService.giveMeUserByEmail(email));
    }

    @GetMapping("/get-user-role/{role}")
    public ResponseEntity<?> getUserByRole(@PathVariable String role){
        return ResponseEntity.status(200).body(userService.giveMeUserByRole(role));
    }

    @GetMapping("/get-user-age-above/{age}")
    public ResponseEntity<?> getUserByAgeOrAbove(@PathVariable Integer age){
        return ResponseEntity.status(200).body(userService.giveMeUserAgeOrAbove(age));
    }

}
