package com.revature.search.dtos;

import com.revature.search.SearchEntity;
import com.revature.search.Searchable;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SearchResponse {

    private final List<SearchEntity> responses;

    public SearchResponse(List<SearchEntity> entities) {
        this.responses = new ArrayList<SearchEntity>();
    }
}
