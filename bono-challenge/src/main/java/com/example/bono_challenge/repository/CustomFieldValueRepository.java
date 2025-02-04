package com.example.bono_challenge.repository;

import com.example.bono_challenge.entity.CustomFieldValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomFieldValueRepository extends JpaRepository<CustomFieldValue, Long> {
    List<CustomFieldValue> findByEntityTypeAndEntityId(String entityType, Long entityId);

    CustomFieldValue findByEntityTypeAndEntityIdAndFieldName(String entityType, Long entityId, String fieldName);

    @Query(value = """
       SELECT * 
       FROM CUSTOM_FIELD_VALUES
       WHERE ENTITY_TYPE = :entityType
         AND ENTITY_ID = :entityId
       """, nativeQuery = true)
    List<CustomFieldValue> findValues(
            @Param("entityType") String entityType,
            @Param("entityId") Long entityId
    );

}
