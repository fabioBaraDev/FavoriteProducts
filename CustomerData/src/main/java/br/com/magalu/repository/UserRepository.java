package br.com.magalu.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.magalu.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findFirstByUsername(String userName);

}