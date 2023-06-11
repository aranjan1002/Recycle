package io.pact.example.junit;

import java.util.Objects;

public class Article {
  private String description;

  public Article(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Article article = (Article) o;
    return Objects.equals(description, article.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(description);
  }
}
