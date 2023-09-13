package com.kode19.financialtracker.financialtracker.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.kode19.financialtracker.financialtracker.entity.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class CategoryRepositoryTest {

  @Autowired
  private CategoryRepository categoryRepository;

  private final String categoryNameExist = "TestCategory";

  @BeforeEach
  void setUp() {
    // Given
    Category category = new Category();
    category.setName(categoryNameExist);
    categoryRepository.save(category);
  }

  @Test
  public void testSelectExistCategory_ExistingCategory_ReturnsTrue() {

    // When
    boolean exists = categoryRepository.selectExistCategory(categoryNameExist);

    // Then
    assertTrue(exists);
  }

  @Test
  public void testSelectExistCategory_NonExistingCategory_ReturnsFalse() {
    // When
    String categoryNameNotExist = "TestCategoryNotExist";
    boolean exists = categoryRepository.selectExistCategory(categoryNameNotExist);
    System.out.println(exists);

    // Then
    assertFalse(exists);
  }
}