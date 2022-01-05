package com.revature.search;

import com.revature.search.dtos.SearchRequest;
import com.revature.search.dtos.SearchResponse;
import com.revature.users.profiles.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/api/search")
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public SearchResponse getUsersByString(@RequestParam(name = "query", required = true) String searchInput) {
        List<Profile> profiles = searchService.getUsersBySearch(searchInput);

        return new SearchResponse(profiles);

    }
}
