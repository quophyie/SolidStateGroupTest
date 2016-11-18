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
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ParallelMultiSearchTests {

  private MultiSearch parallelMultiSearch;
  private Search googleSearch, yahooSearch, bingSearch;
  private List<String> googleDatabase, yahooDatabase, bingDatabase;

  @Mock
  private SearchDataSource googleDatasource, yahooDatasource, bingDatasource;

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

    googleSearch = new SearchImpl(googleDatasource);
    yahooSearch = new SearchImpl(yahooDatasource);
    bingSearch = new SearchImpl(bingDatasource);


     parallelMultiSearch = new ParallelMultiSearch(Arrays.asList(googleSearch, yahooSearch, bingSearch), 2);
  }

  @Test
  public void shouldReturnEmptyListGivenQueryWhichDoesNotExistInAnySearchDb() throws IOException {
    List<String> result = parallelMultiSearch.search("Liverpool");
    assertTrue(result.isEmpty());
  }

  @Test
  public void shouldReturnListContaining6ResultsGivenSearchQueryOfLondon() throws IOException {

    when(googleDatasource.getDatabase()).thenReturn(googleDatabase);
    when(yahooDatasource.getDatabase()).thenReturn(yahooDatabase);
    when(bingDatasource.getDatabase()).thenReturn(bingDatabase);

    List<String> result = parallelMultiSearch.search("London");
    assertEquals(6, result.size());

    verify(googleDatasource, times(1)).getDatabase();
    verify(yahooDatasource, times(1)).getDatabase();
    verify(bingDatasource, times(1)).getDatabase();
  }

  @Test
  public void shouldReturnListContaining1ResultGivenSearchQueryOfToronto() throws IOException {

    when(googleDatasource.getDatabase()).thenReturn(googleDatabase);
    when(yahooDatasource.getDatabase()).thenReturn(yahooDatabase);
    when(bingDatasource.getDatabase()).thenReturn(bingDatabase);

    List<String> result = parallelMultiSearch.search("Toronto");
    assertEquals(1, result.size());

    verify(googleDatasource, times(1)).getDatabase();
    verify(yahooDatasource, times(1)).getDatabase();
    verify(bingDatasource, times(1)).getDatabase();
  }
}
