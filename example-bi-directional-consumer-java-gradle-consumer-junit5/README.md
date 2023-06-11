# Example Bi-Directional Consumer Pact example project using JUnit 5

This example is a really simple demonstration of the use of Pact in JUnit 5 tests.

Places where you need to make changes are marked with TODO comments

To run the tests:

```console
./gradlew check
```

The important files in this project:

`src/main/java/io/pact/example/junit/ExampleBiDirectionalConsumerClient.java` - This is the consumer code that will access your provider.
You should replace this with your actual consumer code.

`java/gradle/consumer/junit/src/test/java/io/pact/example/junit/ExampleBiDirectionalConsumerClientTest.java` - This is the Pact consumer test
that invokes the consumer code.

## Publishing the pact file

Running the tests ( if they pass ;-) ) will generate a pact file `pacts/Example Bi-Directional Consumer-Example Bi-Directional Consumer.json`.

You can publish this to your broker by running `./gradlew pactPublish`.


Generally, you would do this from your CI server.


**REMEMBER to set the PACTFLOW_TOKEN environment variable with a valid token before running this!**



Your Gradle build file needs to be updated to set the correct version and branch environment variables
for your CI system.
