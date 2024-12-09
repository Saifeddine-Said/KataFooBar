package com.kata.service;

import org.springframework.stereotype.Service;

@Service
public class FooBarService {
    public String checkNumber(int number) {
        StringBuilder result = new StringBuilder();

        //check the range
        if (number < 0 || number > 100) {
            return String.valueOf(number);
        }

        // check if is divisible by 3 or 5
        if (number % 3 == 0) {
            result.append("FOO");
        }
        if (number % 5 == 0) {
            result.append("BAR");
        }

        // check if contains 3,5 or 7
        for ( char c : String.valueOf(number).toCharArray()) {
            if(c== '3'){
                result.append("FOO");
            }
            else if (c=='5') {
                result.append("BAR");
            }
            else if(c=='7') {
                result.append("QUIX");
            }
        }

        return result.isEmpty() ? String.valueOf(number) : result.toString();
    }
}
