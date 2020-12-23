package com.mainserver.oskelly.repository;

import com.mainserver.oskelly.entity.CommentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentsEntity,Long> {

}
