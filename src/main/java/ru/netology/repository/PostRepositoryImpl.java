package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class PostRepositoryImpl implements PostRepository {
  private final int NEW_POST = 0;

  private final Map<Long, Post> posts = new ConcurrentHashMap<>();
  private final AtomicLong idCounter = new AtomicLong(0);

  public List<Post> all() {
    return posts.values().stream()
            .filter(post -> !post.isOnDelete())
            .collect(Collectors.toList());
  }

  public Optional<Post> getById(long id) {
    Post post = posts.get(id);
    return Optional.ofNullable(post.isOnDelete() ? null : post);
  }

  public Post save(Post post) throws NotFoundException {
    if (post.getId() == NEW_POST)
      post.setId(idCounter.incrementAndGet());
    else {
      Post postWithId = posts.get(post.getId());
      if (postWithId == null || postWithId.isOnDelete())
        throw new NotFoundException("Post with id %d not found".formatted(post.getId()));
    }

    posts.put(post.getId(), post);
    return post;
  }

  public void removeById(long id) throws NotFoundException {
    if (!posts.containsKey(id))
      throw new NotFoundException("Post with id %d not found".formatted(id));
    posts.remove(id);
  }
}
