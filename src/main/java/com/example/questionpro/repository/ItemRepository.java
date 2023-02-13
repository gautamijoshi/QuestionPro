package com.example.questionpro.repository;

import com.example.questionpro.model.Item;
import com.example.questionpro.model.TYPE;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, Integer> {
    @Query(value = """
            select i from Item i
            where i.type = :type and i.title is not null and i.score is not null and i.url is not null and i.byUser is not null 
            and i.time >= :time
            order by i.score 
            limit 10 """)
    List<Item> findTopStory(@Param("time") Date time, @Param("type") TYPE type);

    @Query("select i from Item i where i.type = :type and i.parent = :parent")
    List<Item> getCommentsForStory(@Param("type") TYPE type, @Param("parent") Item parent);

    @Query("select i from Item i where i.id = :id and i.type = :type")
    Item getStory(@Param("id") Integer id, @Param("type") TYPE type);


}