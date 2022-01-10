package com.revature.search;

import com.revature.groups.GroupRepository;
import com.revature.users.UserRepository;
import com.revature.search.dtos.SearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SearchService {

    Logger logger = LoggerFactory.getLogger(SearchService.class);

    private final SearchCache searchCache;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final SearchRepository searchRepository;

    @Autowired
    public SearchService(UserRepository userRepository, GroupRepository groupRepository, SearchRepository searchRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.searchRepository = searchRepository;
        this.searchCache = new SearchCache();
    }

    public SearchResponse query(String query) {
        if (searchCache.contains(query)) {
            logger.info("Cache HIT for valid bucket containing SearchResponse for query: {}", query);
            return searchCache.get(query);
        }
        Stream<Searchable> userSearch = userRepository.findByEmailContains(query).stream();
        Stream<Searchable> grouPSearch = groupRepository.findByNameContains(query).stream();
        SearchResponse response = new SearchResponse(Stream.concat(userSearch, grouPSearch).collect(Collectors.toList()));
        searchCache.put(query, response);
        return searchCache.get(query);
    }

    public SearchResponse query2(String query) {
        return new SearchResponse(searchRepository.findByLabelContains(query));
    }
}