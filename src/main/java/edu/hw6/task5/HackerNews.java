package edu.hw6.task5;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HackerNews {

    private final HttpClient client;
    private HttpResponse<String> response;

    public HackerNews(HttpClient client) {
        this.client = client;
    }

    public HackerNews(HttpClient client, HttpResponse<String> response) {
        this.client = client;
        this.response = response;
    }

    public long[] hackerNewsTopStories() {
        HttpRequest request;
        try {
            request = HttpRequest.newBuilder()
                    .uri(new URI("https://hacker-news.firebaseio.com/v0/topstories.json"))
                    .GET()
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Arrays
                .stream(response.body().substring(1, response.body().length() - 1).split(","))
                .mapToLong(Long::parseLong)
                .toArray();
    }

    public String news(long id) {
        HttpRequest request;
        try {
            request = HttpRequest.newBuilder()
                    .uri(new URI(String.format("https://hacker-news.firebaseio.com/v0/item/%d.json", id)))
                    .GET()
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        Pattern pattern = Pattern.compile("\"title\":\"([^\"]*)\"");
        Matcher matcher = pattern.matcher(response.body());
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
