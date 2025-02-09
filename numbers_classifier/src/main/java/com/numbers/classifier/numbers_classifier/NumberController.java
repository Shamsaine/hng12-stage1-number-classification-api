package com.numbers.classifier.numbers_classifier;

import com.numbers.classifier.numbers_classifier.NumberService;
import com.numbers.classifier.numbers_classifier.NumberResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class NumberController {

    @Autowired
    private NumberService numberService;

    @GetMapping("/classify-number")
    public ResponseEntity<?> classifyNumber(@RequestParam(name = "number") String number) {
        try {
            int num = Integer.parseInt(number);
            NumberResponse response = numberService.numberProperties(num);
            return ResponseEntity.ok(response);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(Map.of(
                "error", true,
                "number", number
            ));
        }
    }
}
