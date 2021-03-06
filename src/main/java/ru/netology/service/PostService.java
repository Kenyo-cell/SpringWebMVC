package ru.netology.service;

import org.springframework.stereotype.Service;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.repository.PostRepository;

import java.util.List;

@Service
public class PostService {
  private final PostRepository repository;

  public PostService(PostRepository repository) {
    this.repository = repository;
  }

  public List<Post> all() {
    return repository.all();
  }

  public Post getById(long id) throws NotFoundException {
    return repository.getById(id)
              .orElseThrow(() -> new NotFoundException("Post not found with id " + id));
  }

  public Post save(Post post) throws NotFoundException {
    return repository.save(post);
  }

  public void removeById(long id) throws NotFoundException {
    repository.removeById(id);
  }
}

