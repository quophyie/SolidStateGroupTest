package com.solidstategroup.candidate.question3;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SerialMultiSearch extends MultiSearch{

  public SerialMultiSearch(List<Search> searchList){
    this.setSearches(searchList);
  }
  @Override
  public List<String> search(String query) throws IOException {
    List<String> results = new ArrayList<String>();

    for (Search search : this.getSearches()){
      List<String> result = search.search(query);
      results.addAll(result);
    }
    return results;
  }
}
