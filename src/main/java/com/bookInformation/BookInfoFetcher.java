package com.bookInformation;

import org.apache.catalina.util.URLEncoder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
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
        String input=sc.nextLine();
        String base_url="https://www.googleapis.com/books/v1/volumes?q="+input;
        //String base_url=String.format("https://www.googleapis.com/books/v1/volumes?q=%s",input);
       //String url = URLEncoder.encode(base_url,StandardCharsets.UTF_8);
        GoogleBooksResponse json = restTemplate.getForObject(base_url, GoogleBooksResponse.class);
        List<Volume> items = json.getItems();
        if (items!=null&&!items.isEmpty()){
            for (Volume item : items){
                System.out.println("Title : "+item.getVolumeInfo().getTitle());
                System.out.println("Publisher : "+item.getVolumeInfo().getPublisher());
            }
        }else {
            System.out.println("No data found");
        }
    }
}
