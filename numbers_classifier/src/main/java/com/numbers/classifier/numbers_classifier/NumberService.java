package com.numbers.classifier.numbers_classifier;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.concurrent.CompletableFuture;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;


@Service
public class NumberService {

    private final RestTemplate restTemplate = new RestTemplate();

    public NumberResponse numberProperties(int number) {
        boolean isPrime = isPrime(number);
        boolean isPerfect = isPerfect(number);
        boolean isArmstrong = isArmstrong(number);
        boolean isEven = (number % 2 == 0);
        int digitSum = getDigitSum(number);

        List<String> properties = new ArrayList<>();
        if (isArmstrong) properties.add("armstrong");
        properties.add(isEven ? "even" : "odd");

        //Call Fun Fact API Asynchronously
        CompletableFuture<String> funFactFuture = fetchFunFactAsync(number);

        return new NumberResponse(number, isPrime, isPerfect, isArmstrong, isEven, digitSum, funFactFuture.join(), properties);
    }

    @Async
    public CompletableFuture<String> fetchFunFactAsync(int number) {
        String url = "http://numbersapi.com/" + number + "/math?json";
        try {
            Map<String, Object> response = restTemplate.getForObject(url, HashMap.class);
            return CompletableFuture.completedFuture(response != null && response.containsKey("text") ? response.get("text").toString() : "No fun fact found.");
        } catch (Exception e) {
            return CompletableFuture.completedFuture("Could not fetch fun fact.");
        }
    }

    private boolean isPrime(int number) {
        if (number < 2) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }
    
    private boolean isPerfect(int number) {
        int sum = 1;
        for (int i = 2; i <= number / 2; i++) {
            if (number % i == 0) sum += i;
        }
        return sum == number;
    }
    
    private boolean isArmstrong(int number) {
        int original = number, sum = 0, digits = String.valueOf(number).length();
        while (number > 0) {
            int digit = number % 10;
            sum += Math.pow(digit, digits);
            number /= 10;
        }
        return sum == original;
    }
    // ✅ Add the missing method
    private int getDigitSum(int number) {
        int sum = 0;
        int n = Math.abs(number); // ✅ Handle negative numbers correctly
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }
}
