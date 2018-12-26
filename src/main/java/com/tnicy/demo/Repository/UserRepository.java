package com.tnicy.demo.Repository;

import com.tnicy.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByUsername(String username);
    public User findByUid(Integer uid);
    public User findByTelephone(String telephone);

    public Boolean existsByTelephone(String telephone);
}
