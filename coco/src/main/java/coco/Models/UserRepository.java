package coco.Models;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserRepository extends CrudRepository<Users, Integer>{

    @Query("FROM Users where userName = ?1")
    List<Users> findUserName(String userName);

    @Query("FROM Users where userName = ?1 and password = ?2")
    List<Users> checkUserPasswordCombination(String userName, String password);

    @Query("FROM Users where userName LIKE ?1%")
    List<Users> searchByUserName (String userName);
}