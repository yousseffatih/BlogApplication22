package com.BlogAPP.BlogAPPLICATION.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BlogAPP.BlogAPPLICATION.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
	
}
