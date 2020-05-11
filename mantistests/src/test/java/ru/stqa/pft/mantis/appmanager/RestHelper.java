package ru.stqa.pft.mantis.appmanager;

import com.jayway.restassured.RestAssured;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import ru.stqa.pft.mantis.model.Issue;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.Set;
import com.google.common.reflect.TypeToken;

public class RestHelper {

    private ApplicationManager app;

    public RestHelper(ApplicationManager app){
        this.app = app;
    }

    public String getIssueForBugifyById(int issueId) throws IOException {
        RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490", "");
        String json = RestAssured.get("http://bugify.stqa.ru/api/issues/" + issueId + ".json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        JsonObject issue = issues.getAsJsonArray().get(0).getAsJsonObject();
        String status = issue.get("state_name").getAsString();
        return status;
    }

}
