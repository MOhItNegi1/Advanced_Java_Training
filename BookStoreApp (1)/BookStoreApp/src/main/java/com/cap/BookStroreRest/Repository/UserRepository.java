package com.cap.BookStroreRest.Repository;


import com.cap.BookStroreRest.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  UserRepository  extends JpaRepository<User, Long> {

}
