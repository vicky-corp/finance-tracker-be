package com.kode19.financialtracker.financialtracker.repository;

import com.kode19.financialtracker.financialtracker.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {


}