package edu.hw6.Task5;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackerNews {
    private HackerNews() {
    }

    private static final String TOP_STORIES_URL = "https://hacker-news.firebaseio.com/v0/topstories.json";
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final int AVAILABLE_STATUS_CODE = 200;

    public static long[] hackerNewsTopStories() {
        HttpRequest request = HttpRequest.newBuilder(URI.create(TOP_STORIES_URL)).build();

        try {
            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == AVAILABLE_STATUS_CODE) {
                String responseBody = response.body();
                String[] idsAsString = responseBody.substring(1, responseBody.length() - 1).split(",");

                int idsLength = idsAsString.length;
                long[] ids = new long[idsLength];

                for (int i = 0; i < idsLength; i++) {
                    ids[i] = Long.parseLong(idsAsString[i]);
                }
                return ids;
            }
        } catch (IOException | InterruptedException exception) {
            return null;
        }
        return new long[0];
    }

    public static String getNews(long id) throws IOException, InterruptedException {
        if (id <= 0) {
            return "Incorrect id of news";
        }

        String newsUrl = "https://hacker-news.firebaseio.com/v0/item/%s.json".formatted(id);

        HttpRequest request = HttpRequest.newBuilder(URI.create(newsUrl)).build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        return getTitle(response.body());
    }

    private static String getTitle(String responseBody) {
        String regex = "\"title\":\"(.*?)\"";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(responseBody);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "Title not found";
        }
    }
}
