package com.project.product;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

	@ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");

	@LocalServerPort	// CONFIGURED RANDOM PORT, SO GETTING THE RANDOM PORT NUMBER
	private Integer port;

	@BeforeEach
	void setup() {
//		RestAssured.baseURI = "http:/localhost";
		RestAssured.port = port;
	}

	static {
		mongoDBContainer.start();
	}

	@Test
	void createProduct() {
		String requestBody = """
					{
					 	"name": "1+",
					 	"description": "Phone",
					 	"price": 20.0
					}
				""";

		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when().post("/api/product/create")
				.then().statusCode(201)
				.body("id", Matchers.notNullValue())
				.body("name", Matchers.equalTo("1+"))
				.body("description", Matchers.equalTo("Phone"));
//				.body("price", Matchers.equalTo(20.0));
	}

}
