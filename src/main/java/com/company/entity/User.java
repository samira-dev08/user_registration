package com.company.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@DynamicInsert
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long userId;

    private String userName;

    private String userEmail;

    private String userPassword;
    @ColumnDefault(value = "1")
    private Long status;

    private boolean isEnabled;

    public User(String userName, String userEmail, String userPassword, boolean isEnabled) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.isEnabled = isEnabled;
    }
}
