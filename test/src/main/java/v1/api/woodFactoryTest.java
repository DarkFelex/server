package v1.api;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.jayway.restassured.builder.RequestSpecBuilder;
import java.net.URI;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by nmikutskiy on 12.01.17.
 */
public class woodFactoryTest {
    private String host = "http://localhost:8080";
    private String login;
    private String pass;

    @BeforeClass
    public void setup(){
        //регистрация тестового юзера ?
        //логин
        //постройка деревни
    }

    @Test
    public void shouldBuildWoodFactory(){
        URI uri = URI.create(host);
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().setBaseUri(uri);

        given().keystore("/Users/nmikutskiy/git/tr-tools/server.jks", "123456")
                .spec(requestSpecBuilder.build()).get().prettyPeek();

    }

    @AfterClass
    public void clean(){
        //delete user?
        //clean village
    }
}
