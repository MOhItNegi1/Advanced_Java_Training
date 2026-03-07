package org.example.Repository;

import org.example.Model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImplement implements UserRepository{

   private List<User> users = new ArrayList<>();

    public UserRepositoryImplement(){
        users.add(new User(1L, "Balraj", "balraj@gmail.com"));
        users.add(new User(2L, "Sunil", "Sunil@gmail.com"));

    }
    @Override
     public List<User> findAll(){
         return users;
     }

    @Override
    public User findById(Long id) {
        return users.stream().filter(user-> user.getId()==id).findFirst().orElse(null);
    }

    @Override
    public void save(User user) {
        users.add(user);
    }
}
