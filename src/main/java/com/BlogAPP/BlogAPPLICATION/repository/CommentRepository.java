package com.BlogAPP.BlogAPPLICATION.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BlogAPP.BlogAPPLICATION.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
	List<Comment> findByPostId(long id);
}
