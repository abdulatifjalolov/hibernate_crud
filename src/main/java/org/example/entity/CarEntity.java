package org.example.entity;
import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Builder
@ToString
@Table(name = "cars")
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;
    private String model;
    private String color;

    private Date createdDate;
    @ManyToOne
    @ToString.Exclude
    private UserEntity userEntity;
}
