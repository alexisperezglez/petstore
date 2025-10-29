package es.home.petstore.service.jparepository.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.home.petstore.service.jparepository.model.TagMO;

@Repository
public interface TagMOJpaRepository extends JpaRepository<TagMO, UUID> {

  @Query(value = """
      SELECT t 
      FROM TagMO t
      WHERE (:filter IS NULL OR UPPER(t.value) LIKE UPPER(CONCAT('%', :filter, '%')))
        AND (
          :cursor IS NULL OR t.value > (
            SELECT t2.value FROM TagMO t2 WHERE t2.id = :cursor
          )
        )
      ORDER BY t.value
      """,
      countQuery = """
          SELECT COUNT(t) 
          FROM TagMO t
          WHERE :filter IS NULL 
             OR UPPER(t.value) LIKE UPPER(CONCAT('%', :filter, '%')) 
      """)
  Page<TagMO> findAllBy(String filter, UUID cursor, Pageable pageable);

}
