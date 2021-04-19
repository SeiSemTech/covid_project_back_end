package com.app.covid.repository;

import com.app.covid.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IExampleRepository extends JpaRepository<Example, Long> {

  @Query("SELECT e FROM Example e WHERE e.valueExample = :value")
  Example findByName(@Param("value") String value);

}
