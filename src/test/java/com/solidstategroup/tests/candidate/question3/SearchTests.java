package com.solidstategroup.tests.candidate.question3;


import com.solidstategroup.candidate.question3.SearchDataSource;
import com.solidstategroup.candidate.question3.SearchImpl;
import com.solidstategroup.candidate.question3.Search;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SearchTests {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private Search search;

  @Mock
  private SearchDataSource searchDataSource;

  private List<String> searchDatabase;

  @Before
  public void setUp(){
    searchDatabase = Arrays.asList(
        "This is london",
        "This is new york",
        "This is Barcelona",
        "We love london");

    search = new SearchImpl(searchDataSource);

  }

  @Test
  public void shouldThrowIllegalArgumentExceptionIfConnectionIsNull() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("searchDataSource cannot be null");
    search = new SearchImpl(null);
  }

  @Test
  public void shouldReturnEmptyListGivenEmptySearchQueryInGoogeDatabase() throws IOException {

    List<String> result = search.search("");
    assertTrue(result.isEmpty());
  }

  @Test
  public void shouldReturnEmptyListGivenNullSearchQueryInGoogeDatabase() throws IOException {
    List<String> result = search.search(null);
    assertTrue(result.isEmpty());
  }

  @Test
  public void shouldReturnEmptyListGivenSearchQueryWhichDoesNotExistInGoogeDatabase() throws IOException {
    when(searchDataSource.getDatabase()).thenReturn(searchDatabase);
    List<String> result = search.search("Liverpool");
    assertTrue(result.isEmpty());
    verify(searchDataSource, times(1)).getDatabase();
  }

  @Test
  public void shouldReturnListContaining2ResultsGivenLondonAsSearchQuery() throws IOException {
    when(searchDataSource.getDatabase()).thenReturn(searchDatabase);
    List<String> result = search.search("london");
    assertEquals(2,result.size());
    verify(searchDataSource, times(1)).getDatabase();
  }

  @Test
  public void shouldThrowIOExceptionIfConnectionToSearchDatabaseFails() throws IOException {
    when(searchDataSource.getDatabase()).thenThrow(new IOException());
    thrown.expect(IOException.class);
    search.search("london");
    verify(searchDataSource, times(1)).getDatabase();
  }
}
