package com.BlogAPP.BlogAPPLICATION.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.BlogAPP.BlogAPPLICATION.playload.PostDTO;
import com.BlogAPP.BlogAPPLICATION.playload.PostResponse;
import com.BlogAPP.BlogAPPLICATION.service.PostService;
import com.BlogAPP.BlogAPPLICATION.utils.AppConstants;

@Controller
@RequestMapping("/api/posts")
public class PostController {
	
	private PostService postService;
	
	public PostController(PostService postService)
	{
		this.postService = postService;
	}
	
	@PostMapping("/addNewPost")
	public ResponseEntity<Object> createPost(@RequestBody PostDTO postDTO) {
		System.out.println("this is the error :"+postDTO.getTitle());
		if (postDTO.getTitle() == null || postDTO.getTitle().isEmpty()) {
            return new ResponseEntity<>("[{ \"message\" : \"Title is required\"}]", HttpStatus.BAD_REQUEST);
        }
		if (postDTO.getDescription() == null || postDTO.getDescription().isEmpty()) {
            return new ResponseEntity<>("[{ \"message\" : \"Description is required\"}]", HttpStatus.BAD_REQUEST);
        }
		if (postDTO.getContent() == null || postDTO.getContent().isEmpty()) {
            return new ResponseEntity<>("[{ \"message\" : \"Content is required\"}]", HttpStatus.BAD_REQUEST);
        }
		return new ResponseEntity<>(postService.createPostDTO(postDTO),HttpStatus.CREATED);
	}
	@GetMapping("/allPosts")
	public ResponseEntity<List<PostDTO>> getAllPosts()
	{
		return new ResponseEntity<>(postService.getAllPosts(),HttpStatus.OK);
	}
	
	@GetMapping("/getPostsByPage")
	public ResponseEntity<PostResponse> getPostByPage(
			@RequestParam(value="pageNo", defaultValue= AppConstants.DEFAULT_PAGE_NUMBER,required = false)int pageNo, 
			@RequestParam(value="pageSize", defaultValue= AppConstants.DEFAULT_PAGE_SIZE,required = false)int pageSize,
			@RequestParam(value="sortBy", defaultValue=AppConstants.DEFAULT_SORT_BY, required= false)String sortBy,
			@RequestParam(value="sortDir", defaultValue=AppConstants.DEFAULT_SORT_DIRECTION, required=false)String dir 
			)
	{
		PostResponse list = postService.getAllPostsByPage(pageNo, pageSize,sortBy,dir);
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@GetMapping("/getPostById")
	public ResponseEntity<PostDTO> getPostById(@RequestParam("idPost") long id)
	{
		return new ResponseEntity<>(postService.getPostbyID(id),HttpStatus.OK);
	}
	
	@PutMapping("/updatePost")
	public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO, @RequestParam("idPost") long id)
	{
		PostDTO postDT = postService.updatePost(postDTO, id);
		return new ResponseEntity<>(postDT,HttpStatus.OK);
	}
	
	@DeleteMapping("/deletePost")
	public ResponseEntity<String> deletePost(@RequestParam("idPost")long id)
	{
		postService.deletePostByID(id);
		return new ResponseEntity<>("[{ \"message\" : \"Post with id " + id + " is deleted \"}]",HttpStatus.OK);
	}
}
