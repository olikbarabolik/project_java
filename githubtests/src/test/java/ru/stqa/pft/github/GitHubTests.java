package ru.stqa.pft.github;

import org.testng.Assert;
import org.testng.annotations.Test;
import  com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import java.io.IOException;

//my token c049579cb4bb4427d5f146f579e30601ad56c119
public class GitHubTests {

    @Test
    public void testCommits() throws IOException{
        Github github = new RtGithub("c049579cb4bb4427d5f146f579e30601ad56c119");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("olikbarabolik","java_pft")).commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String,String>().build()) ){
            //System.out.println(commit);
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
