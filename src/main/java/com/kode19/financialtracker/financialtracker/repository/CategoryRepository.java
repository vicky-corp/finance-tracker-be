package com.kode19.financialtracker.financialtracker.repository;

import com.kode19.financialtracker.financialtracker.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

  @Query("""
      SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM Category c WHERE c.name = ?1
      """)
  Boolean selectExistCategory(String category);

}