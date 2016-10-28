package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static org.apache.commons.lang3.StringUtils.getLevenshteinDistance;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args).close();
    }


    @Bean
    CommandLineRunner cmd() {
        return cmd -> {

            String noteBookEntry = "Word Words Wor word";
            String keyword = "Word";

            int levenshteinDistance = getLevenshteinDistance(noteBookEntry, keyword);

            System.out.println("distance : "+getLevenshteinDistance("Word","Word"));
            System.out.println("distance : "+getLevenshteinDistance("Word","Words"));
            System.out.println("distance : "+getLevenshteinDistance("Word","Wor"));
            System.out.println("distance : "+getLevenshteinDistance("Word","word"));

        };
    }
}
