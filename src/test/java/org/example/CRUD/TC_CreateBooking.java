package org.example.CRUD;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.example.Actions.AssertActions;
import org.example.Base.BaseTest;
import org.example.Endpoints.APIConstants;
import org.example.Modules.PayloadManager;
import org.example.Payloads.Response.BookingResponse;
import org.example.Utils.PropertyReaderUtil;
import org.example.Utils.YAMLReader;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TC_CreateBooking extends BaseTest {

    @Test  (groups = {"stage","P0"})
    @Owner("Radha")
    @Severity(SeverityLevel.CRITICAL)
    @Description("TC#01-Verify that Create booking works and ID is generated")
    public void testCreateBooking_positive() throws JsonProcessingException {
        //Call the Request Block
        //Call the payload
        //Call the Assertion Block
        payloadManager = new PayloadManager();
        actions = new AssertActions();
        requestSpecification = RestAssured.given()
                .baseUri(APIConstants.BASE_URL)
                .contentType(ContentType.JSON).log().all();
        requestSpecification.basePath(APIConstants.CREATE_BOOKING);
        response = requestSpecification.when().body(payloadManager.createPayload()).post();
        validatableResponse = response.then().log().all();
        //jsonPath = JsonPath.from(response.asString());
        validatableResponse.statusCode(200);
        BookingResponse bookingResponse = payloadManager.JsonToObject(response.asString());
        assertThat(bookingResponse.getBooking().toString()).isNotEmpty().isNotNull();
    }

    @Test  (groups = {"stage","P0"})
    @Description("TC#02-Verify Create booking with no payload")
    public void testCreateBooking_negative() throws JsonProcessingException {
        payloadManager = new PayloadManager();
        actions = new AssertActions();
        requestSpecification = RestAssured.given()
                .baseUri(APIConstants.BASE_URL)
                .contentType(ContentType.JSON).log().all();
        requestSpecification.basePath(APIConstants.CREATE_BOOKING);
        response = requestSpecification.when().body("").post();
        validatableResponse = response.then().log().all();
        //jsonPath = JsonPath.from(response.asString());
        validatableResponse.statusCode(500);
        //new YAMLReader().readKey().get("username");
        //new PropertyReaderUtil().readKey().get("username");
        //new ExcelReader().readKey().get("username");
        //new TextReader().readKey().get("username");
        //new EnvReader().readKey().get("username");
        //new XMLReader().readKey().get("username");
    }
}