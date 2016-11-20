package com.solidstategroup.candidate.question3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchImpl implements Search {


  private SearchDataSource searchDataSource;

  /**
   * Intializes a search with a searchDataSource
   * @param searchDataSource - The searchDataSource ie. a list of Strings
   */
  public SearchImpl(SearchDataSource searchDataSource) {
    if (searchDataSource == null)
      throw new  IllegalArgumentException("searchDataSource cannot be null");

     this.searchDataSource = searchDataSource;


  }
  @Override
  public List<String> search(String query) throws IOException {
    List<String> searchResults = new ArrayList<String>();

    if (query == null || "".equals(query.trim()))
      return searchResults;

    List<String> database = searchDataSource.getDatabase();
    if (database == null)
      throw new  IllegalStateException("database cannot be null");
    if (!database.isEmpty()){
      for (String item : database ){
        if (item.toLowerCase().trim().contains(query.trim().toLowerCase()))
          searchResults.add(item);
      }
    }
    return searchResults;
  }
}
