package coco.Models;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface PostRepository extends CrudRepository<Posts,Integer> {
    @Query("FROM Posts")
    List<Posts> feed();

    @Query("FROM Posts where userName = ?1")
    List<Posts> accountPosts(String userName);
}
