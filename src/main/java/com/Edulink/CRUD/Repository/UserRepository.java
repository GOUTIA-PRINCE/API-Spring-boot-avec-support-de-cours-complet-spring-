package com.Edulink.CRUD.Repository;

import com.Edulink.CRUD.Entite.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//cette interface permet d'interagir avec la base de donnee
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    public boolean existsByEmail(String email);

}
