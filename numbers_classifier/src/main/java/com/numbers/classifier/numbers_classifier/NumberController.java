package main.java.com.numbers.classifier.numbers_classifier;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class NumberController {
    
    @Autowired
    private NumberModel numberModel;
    
    @GetMapping("/classify-number/{number}")
    public String classifyNumber(@PathVariable int number) {
        // Your logic to classify the number goes here
        return "The number " + number + " is classified as ...";
    }
}
