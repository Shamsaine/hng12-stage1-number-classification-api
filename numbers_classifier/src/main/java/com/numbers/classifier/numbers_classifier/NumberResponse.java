package com.numbers.classifier.numbers_classifier;

import java.util.List;

public class NumberResponse {
    private int number;
    private boolean isPrime;
    private boolean isPerfect;
    private boolean isArmstrong;
    private boolean isEven;
    private int digitSum;
    private String funFact;
    private List<String> properties;

    public NumberResponse(int number, boolean isPrime, boolean isPerfect, boolean isArmstrong, boolean isEven, int digitSum, String funFact, List<String> properties) {
        this.number = number;
        this.isPrime = isPrime;
        this.isPerfect = isPerfect;
        this.isArmstrong = isArmstrong;
        this.isEven = isEven;
        this.digitSum = digitSum;
        this.funFact = (funFact != null) ? funFact : "No fun fact found.";
        this.properties = properties;
    }

    public int getNumber() { return number; }
    public boolean getIsPrime() { return isPrime; }
    public boolean getIsPerfect() { return isPerfect; }
    public boolean getIsArmstrong() { return isArmstrong; }
    public boolean getIsEven() { return isEven; }
    public int getDigitSum() { return digitSum; }
    public String getFunFact() { return funFact; }
    public List<String> getProperties() { return properties; }
}
