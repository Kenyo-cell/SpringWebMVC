package ru.netology.model;

import com.google.gson.annotations.Expose;

public class Post {
  private long id;
  private String content;
  @Expose(serialize = false)
  private boolean onDelete = false;

  public Post() {
  }

  public Post(long id, String content) {
    this.id = id;
    this.content = content;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public boolean isOnDelete() {
    return onDelete;
  }

  public void setOnDelete(boolean onDelete) {
    this.onDelete = onDelete;
  }
}
