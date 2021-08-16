package com.rvy.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rvy.store.entity.User;

public interface IUserRepository extends JpaRepository<User, Integer>  {

}
