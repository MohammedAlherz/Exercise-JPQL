package com.example.usersmanagement.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Check(constraints = "role IN ('user', 'admin')")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name should not be null")
    @Size(min = 5, max = 20,message ="Name should be between 5 and 20 characters")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotEmpty(message = "UserName should not be null")
    @Size(min = 5,max = 20,message = "UserName should be between 5 and 20 characters")
    @Column(columnDefinition = "varchar(20) unique not null")
    private String userName;

    @NotEmpty(message = "Password should not be null")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&]).{8,}$",
            message = "Password must be at least 8 characters long and contain letters, digits, and special symbols"
    )
    @Size(min = 8, max = 20, message = "Password should be between 8 and 20 characters")
    @Column(columnDefinition = "varchar(20) not null")
    private String password;

    @NotEmpty(message = "Email should not be empty")
    @Size(min = 10, max = 50, message = "Email should be between 10 and 50 characters")
    @Email(message = "Invalid email format")
    @Column(columnDefinition = "varchar(50) unique not null")
    private String email;

    @NotEmpty(message = "Role should not be null")
    @Pattern(regexp = "^(user|admin)$", message = "Role must be either 'user' or 'admin'")
    @Column(columnDefinition = "varchar(10) not null")
    private String role;

    @NotNull(message = "Age should not be null")
    @Positive(message = "Age should be positive number")
    @Column(columnDefinition = "int not null")
    private Integer age;
}
