package com.example.questionpro.repository;

import com.example.questionpro.model.TopStoryHistory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopStoryHistoryRepository extends CrudRepository<TopStoryHistory,Integer> {
    @Query( value = "select t from TopStoryHistory t order by t.timestamp DESC")
    List<TopStoryHistory> findByOrderByTimestampDesc();

}
