package com.BlogAPP.BlogAPPLICATION.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.BlogAPP.BlogAPPLICATION.entity.Post;
import com.BlogAPP.BlogAPPLICATION.playload.PostDTO;
import com.BlogAPP.BlogAPPLICATION.playload.PostResponse;
import com.BlogAPP.BlogAPPLICATION.repository.PostRepository;
import com.BlogAPP.BlogAPPLICATION.service.PostService;

import lombok.Data;


import com.BlogAPP.BlogAPPLICATION.exception.ResourceNotFoundException;

@Data
@Service
public class PostServiceImpl implements PostService{
	private PostRepository postRepository;
	private ModelMapper mapper;
	
	public PostServiceImpl(PostRepository postRepository , ModelMapper mapper) {
		this.postRepository = postRepository;
		this.mapper = mapper;
	}
	 
	@Override
	public PostDTO createPostDTO(PostDTO postDTO) {
		Post post = mapToEntity(postDTO);
		Post newPost = postRepository.save(post);
		return mapToDTO(newPost);
		
	}
	
	@Override
	public List<PostDTO> getAllPosts(){
		List<Post> posts = postRepository.findAll();
		List<PostDTO>postDTO = posts.stream().map(p -> mapToDTO(p)).collect(Collectors.toList());
		return postDTO;
		
	}
	@Override
	public PostDTO getPostbyID(long id) {
		Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
		PostDTO postDTO = mapToDTO(post);
		return postDTO;
	}
	
	
	// convert Entity to DTO
	private PostDTO mapToDTO(Post post)
	{ 
		PostDTO postResponce = mapper.map(post, PostDTO.class);
		return postResponce;
	}
	
	// convert DTO TO Entity
	private Post mapToEntity(PostDTO postDTO)
	{
		Post post = mapper.map(postDTO, Post.class);
		return post;
	}

	@Override
	public PostDTO updatePost(PostDTO postDTO , long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id",id));
		post.setTitle(postDTO.getTitle());
		post.setDescription(postDTO.getDescription());
		post.setContent(postDTO.getContent());
		Post updatedPost = postRepository.save(post);
		return mapToDTO(updatedPost);
	}

	@Override
	public void deletePostByID(long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id",id));
		postRepository.delete(post);
		
	}

	@Override
	public PostResponse getAllPostsByPage(int pageNo, int pageSize, String sortBy,String dir) {
		
		Sort sort = dir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();
		// create Pageable_instance
		PageRequest pageable = PageRequest.of(pageNo,pageSize,sort);
		
		Page<Post> postsPage = postRepository.findAll(pageable);
		
		List<Post> listOfPosts = postsPage.getContent();
		
		List<PostDTO> content = listOfPosts.stream().map(p -> mapToDTO(p)).collect(Collectors.toList());
		
		PostResponse response = new PostResponse();
		response.setContent(content);
		response.setPageNo(postsPage.getNumber());
		response.setPageSize(postsPage.getSize());
		response.setTotalElements(postsPage.getTotalElements());
		response.setTotalPages(postsPage.getTotalPages());
		response.setLast(postsPage.isLast());
		return response;
	}
	

	
}
