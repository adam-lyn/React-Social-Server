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

    private final SearchCache userSearchCache;
    private final SearchCache groupSearchCache;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    @Autowired
    public SearchService(UserRepository userRepository, GroupRepository groupRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.userSearchCache = new SearchCache();
        this.groupSearchCache = new SearchCache();
    }

    public SearchResponse userQuery(String query) {
        if (userSearchCache.contains(query)) {
            logger.info("Cache HIT for valid bucket containing SearchResponse for query: {}", query);
            return userSearchCache.get(query);
        }
        Stream<Searchable> userSearch = userRepository.findByEmailContains(query).stream();
        SearchResponse response = new SearchResponse(userSearch.collect(Collectors.toList()));
        userSearchCache.put(query, response);
        return userSearchCache.get(query);
    }


    public SearchResponse groupQuery(String query) {
        if (groupSearchCache.contains(query)) {
            logger.info("Cache HIT for valid bucket containing SearchResponse for query: {}", query);
            return groupSearchCache.get(query);
        }
        Stream<Searchable> groupSearch = groupRepository.findByNameContains(query).stream();
        SearchResponse response = new SearchResponse(groupSearch.collect(Collectors.toList()));
        groupSearchCache.put(query, response);
        return groupSearchCache.get(query);
    }

//    public SearchResponse query2(String query) {
//        return new SearchResponse(searchRepository.findByLabelContains(query));
//    }
}