package com.mainserver.oskelly.repository;

import com.mainserver.oskelly.entity.CommentEntity;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<CommentEntity,Long> {

}
