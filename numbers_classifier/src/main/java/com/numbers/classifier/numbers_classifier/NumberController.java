package com.numbers.classifier.numbers_classifier;


import com.numbers.classifier.numbers_classifier.service.NumberService;
import com.numbers.classifier.numbers_classifier.service.NumberResponse;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class NumberController {
    
    @Autowired
    private NumberService numberService;

    @GetMapping("/classify-number/{number}")
    public ResponseEntity<?> classifyNumber(@PathVariable String number) {
        try {
            int num = Integer.parseInt(number); // Validate input
            NumberResponse response = numberService.numberProperties(num);
            return ResponseEntity.ok(response); // Return JSON response
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(Map.of(
                "number", number,
                "error", true
            ));
        }
    }

}
