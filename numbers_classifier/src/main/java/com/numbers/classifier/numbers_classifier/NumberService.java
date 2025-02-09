import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service
public class NumberService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final Map<Integer, String> funFactCache = new HashMap<>(); // ✅ Caching for fun facts

    public NumberResponse numberProperties(int number) {
        boolean isPrime = isPrime(number);
        boolean isPerfect = isPerfect(number);
        boolean isArmstrong = isArmstrong(number);
        boolean isEven = (number % 2 == 0);
        int digitSum = getDigitSum(number);

        // ✅ Construct `properties` list
        List<String> properties = new ArrayList<>();
        if (isArmstrong) properties.add("armstrong");
        properties.add(isEven ? "even" : "odd");

        // ✅ Fetch fun fact (with caching)
        String funFact = funFactCache.computeIfAbsent(number, this::fetchFunFact);

        return new NumberResponse(number, isPrime, isPerfect, isArmstrong, isEven, digitSum, funFact, properties);
    }

    private String fetchFunFact(int number) {
        String url = "http://numbersapi.com/" + number + "/math?json";
        try {
            Map<String, Object> response = restTemplate.getForObject(url, HashMap.class);
            return response != null && response.containsKey("text") ? response.get("text").toString() : "No fun fact found.";
        } catch (Exception e) {
            return "Could not fetch fun fact.";
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

    private int getDigitSum(int number) {
        int sum = 0;
        int n = Math.abs(number);
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }
}
