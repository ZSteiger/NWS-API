# NWS-API

Small Kotlin / Ktor REST API for making HTTP requests to the [National Weather Service API](https://www.weather.gov/documentation/services-web-api#/default/point)

## Build and Run

Start by cloning this repo to your local machine. Next, ensure the project builds using the provided Gradle Wrapper:

```bash
./gradlew build
```

and if that is successful, the project should be ready to start with

```bash
./gradlew run
```

If the Idea IDE is installed on your machine, you should also be able to open the project there and use the Run button on the top bar to start the server.

### Making Requests

Once the server has started successfully, you should see this info log, and are able to begin querying the API.
`INFO  Application - Responding at http://0.0.0.0:8080`

Both Postman and your web browser should suffice for testing the service. Either begin by making a new GET request in Postman, or by navigating to this URL in your browser:

<http://localhost:8080/weather?lat=37.7749&lon=-122.4194>

Once the request has been made, you should see something similar to this:

```text
City: Daly City, State: CA
Forecast: Sunny
Temperature: 77°F (moderate)
```

Improperly formatted or invalid Lat/Long values also return an error message informing the API consumer what happened:
<http://localhost:8080/weather?lat=35.68&lon=139.69>

`Error fetching weather for provided Lat/Long, please ensure you have the correct values and try again.`

Finally, if our request to the NWS API returns a 500, they should see this error:
`NWS API is currently unavailable. Please try again later.`

If you have any questions, please feel free to reach out!

## Ktor Generated Content - Reference if necessary

This project was created using the [Ktor Project Generator](https://start.ktor.io).

Here are some useful links to get you started:

- [Ktor Documentation](https://ktor.io/docs/home.html)
- [Ktor GitHub page](https://github.com/ktorio/ktor)
- The [Ktor Slack chat](https://app.slack.com/client/T09229ZC6/C0A974TJ9). You'll need to [request an invite](https://surveys.jetbrains.com/s3/kotlin-slack-sign-up) to join.

## Features

Here's a list of features included in this project:

| Name                                               | Description                                                 |
| -------------------------------------------------- | ----------------------------------------------------------- |
| [Routing](https://start.ktor.io/p/routing-default) | Allows to define structured routes and associated handlers. |

## Building & Running

To build or run the project, use one of the following tasks:

| Task                                    | Description                                                          |
| --------------------------------------- | -------------------------------------------------------------------- |
| `./gradlew test`                        | Run the tests                                                        |
| `./gradlew build`                       | Build everything                                                     |
| `./gradlew buildFatJar`                 | Build an executable JAR of the server with all dependencies included |
| `./gradlew buildImage`                  | Build the docker image to use with the fat JAR                       |
| `./gradlew publishImageToLocalRegistry` | Publish the docker image locally                                     |
| `./gradlew run`                         | Run the server                                                       |
| `./gradlew runDocker`                   | Run using the local docker image                                     |

If the server starts successfully, you'll see the following output:

```
2024-12-04 14:32:45.584 [main] INFO  Application - Application started in 0.303 seconds.
2024-12-04 14:32:45.682 [main] INFO  Application - Responding at http://0.0.0.0:8080
```
