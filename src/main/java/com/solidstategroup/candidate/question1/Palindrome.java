package com.solidstategroup.candidate.question1;

/**
 * Class that handles palindrome operations
 *
 * Definition: A palindrome is a string that reads the same forward and backward.
 * For example, "abcba" is a palindrome, "abab" is not.
 */
public class Palindrome {

    /**
     * Please implement this method to
     * return true if the parameter is a palindrome and false otherwise.
     *
     * @param input string to determine if a palindrome
     * @return true if the parameter is a palindrome and false otherwise
     */
    public boolean isPalindrome(String input) {

        if (input == null || "".equals(input.trim()))
            return false;

        String sanitizedInput = input.trim().toLowerCase().replace(" ", "");

        if (input.length() == 1 )
            return true;

        int inputLength = sanitizedInput.length();

        for(int idx = 0; idx < inputLength / 2 ; idx++ )
            if (sanitizedInput.charAt(idx) != sanitizedInput.charAt(inputLength - 1 - idx))
                return false;

        return true;
    }
}
