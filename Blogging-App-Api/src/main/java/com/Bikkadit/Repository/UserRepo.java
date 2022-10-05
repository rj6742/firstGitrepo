package com.Bikkadit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Bikkadit.model.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
