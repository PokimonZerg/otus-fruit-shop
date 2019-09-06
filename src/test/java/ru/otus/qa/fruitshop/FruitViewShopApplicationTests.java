package ru.otus.qa.fruitshop;

import io.restassured.RestAssured;
import io.restassured.specification.Argument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerPort;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureStubRunner(
		stubsMode = StubRunnerProperties.StubsMode.REMOTE,
		repositoryRoot="git://https://github.com/PokimonZerg/otus-contracts.git",
		ids="ru.otus.qa:fruit-warehouse:0.0.1-SNAPSHOT:12345"
)
@TestPropertySource(properties = "warehouse.url=http://localhost:12345/warehouse/")
public class FruitViewShopApplicationTests {

	@LocalServerPort
	int randomServerPort;

	//@StubRunnerPort("fruit-warehouse")
	//int producerPort;

	@Test
	public void testApples() {

		given()
				.port(randomServerPort).
		when().
				get("/shop/apples").
		then().
				statusCode(200).
				body("name", equalTo("Яблоко"),
						"count", equalTo(4));

	}

}
