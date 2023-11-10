package edu.hw6;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task5 {

    private final static org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();
    private static final String TOP_STORIES_URL = "https://hacker-news.firebaseio.com/v0/topstories.json";
    private static final String ITEM_URL = "https://hacker-news.firebaseio.com/v0/item/%d.json";

    public static long[] hackerNewsTopStories() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(TOP_STORIES_URL))
            .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            String[] ids = json.replaceAll("[\\[\\]\"]", "").split(",");
            long[] storyIds = new long[ids.length];
            for (int i = 0; i < ids.length; i++) {
                storyIds[i] = Long.parseLong(ids[i]);
            }
            return storyIds;
        } catch (IOException | InterruptedException e) {
            LOGGER.info(e.getMessage());
            return new long[0];
        }
    }

    public static String news(long id) {
        String url = String.format(ITEM_URL, id);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            Pattern pattern = Pattern.compile("\"title\":\"(.*?)\"");
            Matcher matcher = pattern.matcher(json);
            if (matcher.find()) {
                return matcher.group(1);
            }
        } catch (IOException | InterruptedException e) {
            LOGGER.info(e.getMessage());
        }
        return "";
    }

}
