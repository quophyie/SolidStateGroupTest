package com.solidstategroup.tests.candidate.question3;

import com.solidstategroup.candidate.question3.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MultiSearchTests {

  private MultiSearch multiSearch;
  private Search googleSearch, yahooSearch, bingSearch;
  private List<String> googleDatabase, yahooDatabase, bingDatabase;

  @Mock
  private SearchDataSource googleDataSource, yahooDataSource, bingDataSource;

  @Before
  public void setUp(){
    googleDatabase = Arrays.asList(
        "This is london",
        "This is new york",
        "This is Toronto",
        "We love london");

    yahooDatabase = Arrays.asList(
        "london is great city",
        "This is new york",
        "This is Barcelona",
        "Things we hate about london");

    bingDatabase = Arrays.asList(
        "things to do in london",
        "London night life",
        "San Francisco is sunny",
        "Orange county is really orange");

    googleSearch = new SearchImpl(googleDataSource);
    yahooSearch = new SearchImpl(yahooDataSource);
    bingSearch = new SearchImpl(bingDataSource);


     multiSearch = new SerialMultiSearch(Arrays.asList(googleSearch, yahooSearch, bingSearch));
  }

  @Test
  public void shouldReturnEmptyListGivenQueryWhichDoesNotExistInAnySearchDb() throws IOException {
    List<String> result = multiSearch.search("Liverpool");
    assertTrue(result.isEmpty());
  }

  @Test
  public void shouldReturnListContaining6ResultsGivenSearchQueryOfLondon() throws IOException {

    when(googleDataSource.getDatabase()).thenReturn(googleDatabase);
    when(yahooDataSource.getDatabase()).thenReturn(yahooDatabase);
    when(bingDataSource.getDatabase()).thenReturn(bingDatabase);

    List<String> result = multiSearch.search("London");
    assertEquals(6, result.size());

    verify(googleDataSource).getDatabase();
    verify(yahooDataSource).getDatabase();
    verify(bingDataSource).getDatabase();
  }

  @Test
  public void shouldReturnListContaining1ResultGivenSearchQueryOfToronto() throws IOException {

    when(googleDataSource.getDatabase()).thenReturn(googleDatabase);
    when(yahooDataSource.getDatabase()).thenReturn(yahooDatabase);
    when(bingDataSource.getDatabase()).thenReturn(bingDatabase);

    List<String> result = multiSearch.search("Toronto");
    assertEquals(1, result.size());

    verify(googleDataSource).getDatabase();
    verify(yahooDataSource).getDatabase();
    verify(bingDataSource).getDatabase();
  }
}
