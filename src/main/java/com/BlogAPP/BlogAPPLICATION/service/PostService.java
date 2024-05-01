package com.BlogAPP.BlogAPPLICATION.service;

import java.util.List;

import com.BlogAPP.BlogAPPLICATION.playload.PostDTO;
import com.BlogAPP.BlogAPPLICATION.playload.PostResponse;

public interface PostService {
	PostDTO createPostDTO(PostDTO postDTO);
	List<PostDTO> getAllPosts();
	PostResponse getAllPostsByPage(int pageNo, int pageSize, String sortBy, String dir);
	PostDTO getPostbyID(long id);
	PostDTO updatePost(PostDTO postDTO, long id);
	void deletePostByID(long id);
}
