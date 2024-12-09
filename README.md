
# FOO BAR QUIX Kata Project

In this project, we have used Spring Boot and Spring Batch to execute batch jobs.

It includes an API endpoint that triggers the execution of a batch job and returns a response indicating whether the execution was successful or failed.


Accessing Swagger
You can access the Swagger UI to test the API endpoints at the following URL:
## Prerequisites

JDK 21 or higher

Spring Boot 3.x

Maven


## Testing

Accessing Swagger
You can access the Swagger UI to test the API endpoints at the following URL:


```http
  http://localhost:8080/swagger-ui/index.html
```

### Execute the Service Without Batch
The first service allows you to execute the service without using a batch job.

```http
GET /foobar
```
This service takes a number as a parameter and returns the result as a string.

Responses:

200 OK: The service executed successfully.

500 Internal Server Error: An error occurred during the execution of the service.


### Triggering the Batch Job via the API
The batch job can be triggered via a REST API endpoint:

```http
 GET /run-batch
```

Responses:

200 OK: The batch job was executed successfully.

500 Internal Server Error: The batch job execution failed.

Input and Output Files

The `input.txt` and `output.txt` files can be found in the `src/main/resources` folder.
