package com.example.first.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.first.model.UserFirst;

/**
 * @author Elten Hajiyev
 */
public interface UserFirstRepository extends CrudRepository<UserFirst, Long> {

}
