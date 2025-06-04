package com.bookInformation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

@Component
public class BookInfoFetcher implements CommandLineRunner {
    private final RestTemplate restTemplate;

    public BookInfoFetcher(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name of book");
        String input = sc.nextLine();
        String base_url = "https://www.googleapis.com/books/v1/volumes?q=" + input;
        //String base_url=String.format("https://www.googleapis.com/books/v1/volumes?q=%s",input);
        // String url = URLEncoder.encode(base_url, StandardCharsets.UTF_8);
        GoogleBooksResponse object = restTemplate.getForObject(base_url, GoogleBooksResponse.class);
        List<Volume> items = object.getItems();
        try {
            if (!items.isEmpty() && items != null) {
                for (Volume item : items) {
                    System.out.println("title : " + item.getVolumeInfo().getTitle());
                    System.out.println("Publisher : " + item.getVolumeInfo().getPublisher());
                    System.out.println("############################################################################");
                }
            } else {
                System.out.println("No data found");
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
