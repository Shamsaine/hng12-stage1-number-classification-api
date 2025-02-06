package main.java.com.numbers.classifier.numbers_classifier;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import java.util.math;

@Service
public class NumberService {

    @Autowired
    private NumberModel numberModel = new NumberModel();

    public String numberProperties(NumberModel numberModel){
        
    }
    
}
