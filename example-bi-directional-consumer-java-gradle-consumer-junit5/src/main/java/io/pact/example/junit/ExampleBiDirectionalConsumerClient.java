package io.pact.example.junit;

import com.google.gson.Gson;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.util.List;

// This is the service client class that is responsible for making the HTTP request to Example Bi-Directional Consumer
// TODO: change this class for your actual service
public class ExampleBiDirectionalConsumerClient {

  // This is the response from the call to Example Bi-Directional Consumer
  static class Response {
    private List<Article> articles;

    public List<Article> getArticles() {
      return articles;
    }

    public void setArticles(List<Article> articles) {
      this.articles = articles;
    }
  }

  public List<Article> getArticles(String baseUrl) throws IOException {
    String result = Request.Get(baseUrl + "/articles.json").execute().returnContent().asString();
    Gson gson = new Gson();
    return gson.fromJson(result, Response.class).getArticles();
  }
}
