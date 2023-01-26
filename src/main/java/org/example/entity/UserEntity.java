package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.type.descriptor.java.LocalDateJavaType;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Builder
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String phone_number;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public void agas(){
        LocalDate localDate = LocalDate.now();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

    }
}
