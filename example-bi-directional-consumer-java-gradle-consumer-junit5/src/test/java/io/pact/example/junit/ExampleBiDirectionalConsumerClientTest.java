package io.pact.example.junit;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

// This is the Pact test for the Example Bi-Directional Consumer
@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "Example Bi-Directional Consumer")
public class ExampleBiDirectionalConsumerClientTest {
  // We setup the mock server expectations here
  @Pact(consumer = "Example Bi-Directional Consumer ")
  public RequestResponsePact articlesPact(PactDslWithProvider builder) {
    return builder
      // The request we are going to be making
      .uponReceiving("retrieving article data")
        .path("/articles.json")
        .method("GET")
      // what the mock server should respond with
      .willRespondWith()
        .status(200)
        .body(
          new PactDslJsonBody()
            .minArrayLike("articles", 1, 3)
              .stringType("description", "sample description")
              .closeObject()
            .closeArray()
        )
      .toPact();
  }

  // This is the actual test method. It instantiates the ExampleBiDirectionalConsumerClient class,
  // and then calls the method that makes the request, passing in the mock server URL
  @Test
  @PactTestFor(pactMethod = "articlesPact") // articlesPact is the method above that sets up the pact
  void testArticles(MockServer mockServer) throws IOException {
    ExampleBiDirectionalConsumerClient client = new ExampleBiDirectionalConsumerClient();
    List<String> articles = client.getArticles(mockServer.getUrl())
      .stream().map(Article::getDescription).collect(Collectors.toList());
    assertThat(articles, is(equalTo(Arrays.asList("sample description", "sample description", "sample description"))));
    System.out.println("token: " +
    System.getenv("PACTFLOW_TOKEN"));
  }
}
