package org.example.frameworkstudy.repository;

import org.example.frameworkstudy.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {

    Users findByUserid(String userId);

}
