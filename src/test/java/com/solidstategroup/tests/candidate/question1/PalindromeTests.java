package com.solidstategroup.tests.candidate.question1;

import com.solidstategroup.candidate.question1.Palindrome;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;


public class PalindromeTests {

  private Palindrome palindrome;

  @Before
  public void setUp(){
    this.palindrome = new Palindrome();
  }

  @Test
  public void shouldReturnFalseGivenNullStringWhichIsNotAPalindrome() {
    boolean result = palindrome.isPalindrome(null);
    assertFalse(result);
  }

  @Test
  public void shouldReturnFalseGivenEmptyStringWhichIsNotAPalindrome() {
    boolean result = palindrome.isPalindrome("");
    assertFalse(result);
  }

  @Test
  public void shouldReturnTrueGivenStringMadamWhichIsAPalindrome() {
    boolean result = palindrome.isPalindrome("madam");
    assertTrue(result);
  }

  @Test
  public void shouldReturnTrueGivenStringOfLength1WhichIsAPalindrome() {
    boolean result = palindrome.isPalindrome("m");
    assertTrue(result);
  }

  @Test
  public void shouldReturnTrueGivenASentenceWhichIsAPalindrome(){
    boolean result = palindrome.isPalindrome("A Santa dog lived as a devil God at NASA");
    assertTrue(result);

  }

  @Test
  public void shouldReturnFalseGivenStringMadameWhichIsNotAPalindrome() {
    boolean result = palindrome.isPalindrome("madame");
    assertFalse(result);
  }

  @Test
  public void shouldReturnFalseGivenStringOfLength2WhichIsNotAPalindrome() {
    boolean result = palindrome.isPalindrome("ma");
    assertFalse(result);
  }

  @Test
  public void shouldReturnFalseGivenASentenceWhichIsNotAPalindrome(){
    boolean result = palindrome.isPalindrome("A Santa dog lived as a devil God at NASA1");
    assertFalse(result);

  }
}
