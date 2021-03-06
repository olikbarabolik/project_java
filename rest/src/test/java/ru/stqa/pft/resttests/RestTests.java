
package ru.stqa.pft.rest;

import org.testng.Assert;
import org.testng.annotations.Test;
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

public class RestTests {

    @Test
    public void testCreateIssue() throws IOException{
        Set<Issue> oldIssues = getIssues();
        Issue newIssue = new Issue().withSubject("test rest issue!!!").withDescription("test new description!");
        int issueId = createIssue(newIssue);

        Set<Issue> newIssues = getIssues();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues, oldIssues);

    }
    private int createIssue(Issue newIssue) throws IOException {
        String json = getExecutor().execute(Request.Post("https://bugify.stqa.ru/api/issues.json?limit=500")
                .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                        new BasicNameValuePair("description", newIssue.getDescription())))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

    private Set<Issue> getIssues() throws IOException {
        String json = getExecutor().execute(Request.Get("http://bugify.stqa.ru/api/issues.json?limit=500")).returnContent().asString();;
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    }

    private Executor getExecutor() {
        return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490","");
    }
}
