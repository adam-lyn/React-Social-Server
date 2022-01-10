package com.revature.search.dtos;

import com.revature.search.Searchable;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SearchResponse {

    private final List<EntitySearchResult> responses;

    public SearchResponse(List<Searchable> entities) {
        this.responses = new ArrayList<EntitySearchResult>();
        for (Searchable entity : entities) {
            EntitySearchResult result = new EntitySearchResult(entity);
            this.responses.add(result);
        }
    }

    @Data
    public static class EntitySearchResult {
        private final String label;
        private final String key;

        public EntitySearchResult(Searchable entity) {
            this.label = entity.getLabel();
            this.key = entity.getKey();
        }
    }
}
