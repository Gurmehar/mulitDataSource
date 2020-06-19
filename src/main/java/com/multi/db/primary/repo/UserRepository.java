package com.multi.db.primary.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.multi.db.primary.Users;

public interface UserRepository extends JpaRepository<Users,Long> {

}
