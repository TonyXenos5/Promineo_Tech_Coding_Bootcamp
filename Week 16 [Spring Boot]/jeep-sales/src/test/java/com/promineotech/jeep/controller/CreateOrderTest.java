package com.promineotech.jeep.controller;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import com.promineotech.jeep.entity.JeepModel;
import com.promineotech.jeep.entity.Order;
import lombok.Getter;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {"classpath:flyway/migrations/V1.0__Jeep_Schema.sql",
    "classpath:flyway/migrations/V1.1__Jeep_Data.sql"}, config = @SqlConfig(encoding = "utf-8"))

class CreateOrderTest {

  protected String createOrderBody() {
    //@formatter:off
    return "{\n"
        + "     \"customer\":\"STERN_TORO\",\n"
        + "     \"model\":\"RENEGADE\",\n"
        + "     \"trim\":\"Limited\",\n"
        + "     \"doors\":4,\n"
        + "     \"color\":\"EXT_TECHNO_GREEN\",\n"
        + "     \"engine\":\"2_0_HYBRID\",\n"
        + "     \"tire\":\"235_CONTINENTAL\",\n"
        + "     \"options\":[\n"
        + "         \"DOOR_BESTOP_COMBO\",\n"
        + "         \"EXT_MOPAR_HEAD_LED\",\n"
        + "         \"INT_QUAD_LINER\",\n"
        + "         \"STOR_TUFFY_LOCK\",\n"
        + "         \"TOP_MOPAR_SKY\",\n"
        + "         \"WHEEL_QUAD_MOAB\"\n"
        + "     ]\n"
        + "}";
    //@formatter:on
  }

  @Autowired
  @Getter
  private TestRestTemplate restTemplate;

  @LocalServerPort
  private int serverPort;

  @Test
  void testCreateOrderReturnsSuccess201() {
    // Given: an order as JSON
    String body = createOrderBody();
    String uri = String.format("http://localhost:%d/orders", serverPort);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);

    // When: the order is sent
    ResponseEntity<Order> response =
        getRestTemplate().exchange(uri, HttpMethod.POST, bodyEntity, Order.class);

    // Then: a 201 status is returned
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    // And: the returned order is correct
    assertThat(response.getBody()).isNotNull();

    Order order = response.getBody();

    assertThat(order.getCustomer().getCustomerId()).isEqualTo("STERN_TORO");
    assertThat(order.getModel().getModelId()).isEqualTo(JeepModel.RENEGADE);
    assertThat(order.getModel().getTrimLevel()).isEqualTo("Limited");
    assertThat(order.getModel().getNumDoors()).isEqualTo(4);
    assertThat(order.getColor().getColorId()).isEqualTo("EXT_TECHNO_GREEN");
    assertThat(order.getEngine().getEngineId()).isEqualTo("2_0_HYBRID");
    assertThat(order.getTire().getTireId()).isEqualTo("235_CONTINENTAL");
    assertThat(order.getOptions()).hasSize(6);
  }

}
