package com.cts.pmt.user.dao;


import com.cts.pmt.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Long> {



    @Query("SELECT US FROM user US WHERE US.name=:name")
    List<User> findByName(@Param("name") String name);

}
