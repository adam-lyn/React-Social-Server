package com.revature.search;

import com.revature.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SearchRepository extends CrudRepository<SearchEntity, String> {
    List<Searchable> findByLabelContains(String query);
}
