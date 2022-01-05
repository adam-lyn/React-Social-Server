package com.revature.search;

import com.revature.search.dtos.SearchRequest;
import com.revature.search.dtos.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/search")
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public SearchResponse getUsersByString(@RequestParam(name = "query", required = true) String searchInput) {

        // SearchService validates input and submits search
        // We get whatever the service returns - should be a list
        // return that to the user as SearchResponse
    }
}
