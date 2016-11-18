package com.solidstategroup.candidate.question3;

import java.io.IOException;
import java.util.List;

/**
 * An interface to connect to the data source which contains the data to search
 * ***NOTE** This is a partial specification i.e. it contains only the methods
 * required for the tests. In a real world app, there will be more methods
 */
public interface SearchDataSource {

  /**
   *  returns the database to perform search on
   * @return
   */
  List<String> getDatabase() throws IOException;
}
