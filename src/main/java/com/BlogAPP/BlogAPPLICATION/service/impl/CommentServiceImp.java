package com.BlogAPP.BlogAPPLICATION.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.BlogAPP.BlogAPPLICATION.entity.Comment;
import com.BlogAPP.BlogAPPLICATION.entity.Post;
import com.BlogAPP.BlogAPPLICATION.exception.BlogApiException;
import com.BlogAPP.BlogAPPLICATION.exception.ResourceNotFoundException;
import com.BlogAPP.BlogAPPLICATION.playload.CommentDTO;
import com.BlogAPP.BlogAPPLICATION.repository.CommentRepository;
import com.BlogAPP.BlogAPPLICATION.repository.PostRepository;
import com.BlogAPP.BlogAPPLICATION.service.CommentService;

@Service
public class CommentServiceImp implements CommentService{
	
	private CommentRepository commentRepo;
	private PostRepository postRepo;
	private ModelMapper mapper;
	
	public CommentServiceImp(CommentRepository commentRepository , PostRepository postRepository,ModelMapper mapper)
	{
		this.commentRepo = commentRepository;
		this.postRepo = postRepository;
		this.mapper = mapper;
	}

	@Override
	public CommentDTO createComment(long postID, CommentDTO commentDTO) {
		Comment comment = mapToEntity(commentDTO);
		Post post = postRepo.findById(postID).orElseThrow(() -> new ResourceNotFoundException("Post","id",postID));
		comment.setPost(post);
		Comment com =  commentRepo.save(comment);
		return mapToDTO(com);
	}
	
	
	// convert Entity to DTO
		private CommentDTO mapToDTO(Comment comment)
		{ 
			CommentDTO commentDTO = mapper.map(comment, CommentDTO.class);
			return commentDTO;
		}
		
		// convert DTO TO Entity
		private Comment mapToEntity(CommentDTO commentDTO)
		{
			Comment comment = mapper.map(commentDTO, Comment.class);
			return comment;
		}

		@Override
		public List<CommentDTO> getAllCommentByPost(long postID) {
			List<Comment> comments = commentRepo.findByPostId(postID);
			List<CommentDTO> commentDTOs =  comments.stream().map(c -> mapToDTO(c)).collect(Collectors.toList());
			return commentDTOs;
		}

		@Override
		public CommentDTO getCommentByIDs(long postID, long commentID) {
			Post post = postRepo.findById(postID).orElseThrow(()-> new ResourceNotFoundException("Post","id",postID));
			Comment comment = commentRepo.findById(commentID).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentID));
			if(!(comment.getPost().getId() == post.getId()))
			{
				throw new BlogApiException("Comment does not belong the post", HttpStatus.BAD_REQUEST);
			}
			return mapToDTO(comment);
		}

		@Override
		public CommentDTO updateCommentDTO(long postID, long commentID, CommentDTO commentDTO) {
			Post post = postRepo.findById(postID).orElseThrow(()->new ResourceNotFoundException("Post", "id", postID));
			Comment comment = commentRepo.findById(commentID).orElseThrow(()-> new ResourceNotFoundException("Comment", "id", commentID));
			if(!(comment.getPost().getId() == post.getId()))
			{
				throw new BlogApiException("Comment does not belong the post", HttpStatus.BAD_REQUEST);
			}
			comment.setName(commentDTO.getName());
			comment.setEmail(commentDTO.getEmail());
			comment.setBody(commentDTO.getBody());
			Comment updateComment = commentRepo.save(comment);
			return mapToDTO(updateComment);
		}

		@Override
		public void deleteCommentDTO(long postID, long commentID) {
			Post post = postRepo.findById(postID).orElseThrow(()->new ResourceNotFoundException("Post", "id", postID));
			Comment comment = commentRepo.findById(commentID).orElseThrow(()-> new ResourceNotFoundException("Comment", "id", commentID));
			if(!(comment.getPost().getId() == post.getId()))
			{
				throw new BlogApiException("Comment does not belong the post", HttpStatus.BAD_REQUEST);
			}
			commentRepo.delete(comment);
		}
		
}
