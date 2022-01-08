package com.revature.search;

import com.revature.users.UserRepository;
import com.revature.search.dtos.SearchResponse;
import io.micrometer.core.instrument.search.Search;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Service
public class SearchService {

    Logger logger = LoggerFactory.getLogger(SearchService.class);

    private final SearchCache searchCache;
    private final UserRepository userRepository;

    @Autowired
    public SearchService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.searchCache = new SearchCache();
    }

    public SearchResponse queryUsers(String query) {
        if (searchCache.contains(query)) {
            logger.info("Returning cached search results for query: {}", query);
            return searchCache.get(query);
        }
        logger.info("Cache miss for users matching query: {}", query);
        SearchResponse response = new SearchResponse(userRepository.findUsersByEmailContains(query));
        searchCache.put(query, response);
        return searchCache.get(query);
    }
}