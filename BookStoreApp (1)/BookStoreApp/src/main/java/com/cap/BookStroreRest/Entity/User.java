package com.cap.BookStroreRest.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name="User_Table")
public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long  id;

    private String name;
    private String email;
    private String password;


    @OneToMany(mappedBy = "user" ,cascade =CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> books;




}
