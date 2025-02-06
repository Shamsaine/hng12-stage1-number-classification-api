package com.numbers.classifier.numbers_classifier.controller;

import com.numbers.classifier.numbers_classifier.service.NumberService;
import com.numbers.classifier.numbers_classifier.service.NumberResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // ✅ Allow requests from anywhere
public class NumberController {

    @Autowired
    private NumberService numberService;

    // ✅ Change from @PathVariable to @RequestParam
    @GetMapping("/classify-number")
    public ResponseEntity<?> classifyNumber(@RequestParam(name = "number", required = true) String number) {
        try {
            int num = Integer.parseInt(number); // Validate input
            NumberResponse response = numberService.numberProperties(num);
            return ResponseEntity.ok(response);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(Map.of(
                "number", number,
                "error", true,
                "message", "Invalid input. Please enter a valid integer."
            ));
        }
    }
}
