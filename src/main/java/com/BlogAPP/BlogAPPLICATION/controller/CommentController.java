package com.BlogAPP.BlogAPPLICATION.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.BlogAPP.BlogAPPLICATION.playload.CommentDTO;
import com.BlogAPP.BlogAPPLICATION.service.CommentService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;





@Controller
@RequestMapping("/api/post")
public class CommentController {
	private CommentService commentService;
	
	public CommentController(CommentService commentService)
	{
		this.commentService = commentService;
	}

	@PostMapping("/{postID}/addComment")
	public ResponseEntity<CommentDTO> addComment(@PathVariable(value = "postID") long postID ,@Valid @RequestBody CommentDTO comment)
	{
		return new ResponseEntity<>(commentService.createComment(postID, comment),HttpStatus.CREATED);
	}

	@GetMapping("/{postID}/comments")
	public ResponseEntity<List<CommentDTO>> getMethodName(@PathVariable(value = "postID") long postID) {
		return new ResponseEntity<>(commentService.getAllCommentByPost(postID),HttpStatus.CREATED);
	}
	
	@GetMapping("/getCommentById")
	public ResponseEntity<CommentDTO> getCommentById(@RequestParam("postId") long postID ,@RequestParam("commentId") long commentID)
	{
		CommentDTO commentDTO = commentService.getCommentByIDs(postID, commentID);
		return new ResponseEntity<>(commentDTO, HttpStatus.OK);
	}

	@PutMapping("/updateComment")
	public ResponseEntity<CommentDTO> updateComment(@RequestParam("postId") long postID,@Valid @RequestParam("commentId") long commentId,@RequestBody CommentDTO commentDTO) {
		return new ResponseEntity<>(commentService.updateCommentDTO(postID, commentId, commentDTO),HttpStatus.CREATED);
	}

	@DeleteMapping("/deleteComment")
	public ResponseEntity<String> deleteComment(@RequestParam("postId") long postID, @RequestParam("commentId") long commentId)
	{
		commentService.deleteCommentDTO(postID, commentId);
		return new ResponseEntity<>("Comment delete Successfully",HttpStatus.OK);
	}
}
