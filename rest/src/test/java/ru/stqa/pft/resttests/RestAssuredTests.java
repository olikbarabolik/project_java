
package ru.stqa.pft.rest;
import com.jayway.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import  org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertTrue;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.*;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import  java.lang.*;
import org.apache.http.client.fluent.Executor;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.NameValuePair;

public class RestAssuredTests {

    @BeforeClass
    public void init(){
        RestAssured.authentication= RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490","");
    }

    @Test
    public void testCreateIssueAssured() throws IOException{
        Set<Issue> oldIssues = getIssues();
        Issue newIssue = new Issue().withSubject("test rest issue!!!").withDescription("test new description!");
        int issueId = createIssue(newIssue);

        Set<Issue> newIssues = getIssues();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues, oldIssues);

    }
    private int createIssue(Issue newIssue) throws IOException {
        String json = RestAssured.given()
                .parameter("subject", newIssue.getSubject())
                .parameter("description", newIssue.getDescription())
                .post("https://bugify.stqa.ru/api/issues.json?limit=500").asString();


        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
        //return 0;
    }

    private Set<Issue> getIssues() throws IOException {

        String json = RestAssured.get("http://bugify.stqa.ru/api/issues.json?limit=500").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    }

}
