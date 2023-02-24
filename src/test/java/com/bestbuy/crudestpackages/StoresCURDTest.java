package com.bestbuy.crudestpackages;

import com.bestbuy.model.StorePojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StoresCURDTest extends TestBase {

    int idNumber;

    @BeforeClass
    public static void InIT(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port= 3030;
        RestAssured.basePath ="/stores";
    }
    @Test
    public void get(){
        given()
                .when()
                .log().all()
                .get()
                .then().log().all().statusCode(200);
    }

    @Test
    public void addnewStore(){

        StorePojo pojo = new StorePojo();

       pojo.setName("Londis");
       pojo.setType("bigstore");
       pojo.setAddress("131225 Regent Street");
       pojo.setAddress2("");
       pojo.setCity("london");
       pojo.setState("NW");
       pojo.setZip("lok");
       pojo.setLat(44455);
       pojo.setLng(11144);
       pojo.setHours("Monday to Saturday 10-6");

       Response response = given()
               .log().all()
               .header("Content-Type", "application/json")
               .when()
               .body(pojo)
               .post();
       response.then().statusCode(201);
       int idNumber = response.then().extract().path("id");
        System.out.println(idNumber);

    }

    @Test
    public void updatestores(){
        StorePojo pojo = new StorePojo();
        pojo.setName("Nisa");
        pojo.setType("small");
        pojo.setCity("Crawley");
        pojo.setState("West sussex");

        Response response = given()
                .log().all()
                .header("Content-Type", "application/json")
                .pathParam("id","8921")
                .when()
                .body(pojo)
                .patch("/{id}");
        response.then().statusCode(200);


    }
    @Test
    public void deletestore(){
        Response response = given()
                .log().all()
                .header("Content-Type", "application/json")
                .pathParam("id","8921")
                .when()
                .delete("/{id}");
        response.then().statusCode(200);
    }

    @Test
    public void retrivedata (){
        Response response = given()
                .log().all()
                .header("Content-Type", "application/json")
                .pathParam("id","8921")
                .when()
                .get("/{id}");
        response.then().statusCode(404);

    }
}
