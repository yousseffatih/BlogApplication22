package com.BlogAPP.BlogAPPLICATION.service;

import java.util.List;

import com.BlogAPP.BlogAPPLICATION.playload.CommentDTO;

public interface CommentService {
	
	CommentDTO createComment(long postID, CommentDTO comment);
	List<CommentDTO> getAllCommentByPost(long postID);
	CommentDTO getCommentByIDs(long postID , long commentID);
	CommentDTO updateCommentDTO(long postID , long commentID,CommentDTO commentDTO);
	void deleteCommentDTO(long postId , long commentID);
}
