package com.solidstategroup.candidate.question3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

/**
 * Execute {@link MultiSearch} in parallel for speed.
 */
public class ParallelMultiSearch extends MultiSearch {

    private ExecutorService threadPool;
    private int threadPoolSize = 10;

    public ParallelMultiSearch(List<Search> searchList, int threadPoolSize){
        this.setSearches(searchList);
        if (threadPoolSize > 0)
            this.threadPoolSize = threadPoolSize;
        threadPool = Executors.newFixedThreadPool(this.threadPoolSize);
    }

    @Override
    public List<String> search(final String query) throws IOException {

        final List<String> results = Collections.synchronizedList(new ArrayList<String>());

        if (query == null || "".equals(query.trim()))
            return results;

        try {
            for (Search search : this.getSearches()) {
                Runnable searchTask = new SearchTask(query, search, results);
                threadPool.execute(searchTask);
            }

            threadPool.shutdown();
            threadPool.awaitTermination(60, TimeUnit.SECONDS);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return results;
    }


  /**
   * A simple inner helper Runnable class to allow creation of tasks for the thread execution service
   * This is an inner class because its unlikely to be used anywhere else
   */
  private class SearchTask implements Runnable {
        private Search searchToRun;
        private List<String> results;
        private String query;

        public SearchTask(String query, Search searchToRun, List<String> results) {
            this.searchToRun = searchToRun;
            this.results = results;
            this.query = query;
        }

        @Override
        public void run() {
            try {
                List<String> result = searchToRun.search(this.query);

                //We can simply call results.addAll without worrying about
                // race conditions here as results list is synchronized
                results.addAll(result);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
