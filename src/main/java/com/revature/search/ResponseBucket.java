package com.revature.search;

import com.revature.search.dtos.SearchResponse;

import java.time.LocalTime;

public class QueryBucket {


    private final SearchResponse response;
    private final LocalTime maxCacheAge;

    public QueryBucket(SearchResponse response) {
        this.response = response;
        this.maxCacheAge = LocalTime.now().plusSeconds(360);
    }

    public SearchResponse get() {
        return this.response;
    }

    public boolean isValid() {
        return LocalTime.now().isBefore(this.maxCacheAge);
    }
}
