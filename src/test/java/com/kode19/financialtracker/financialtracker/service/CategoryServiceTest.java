package com.kode19.financialtracker.financialtracker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.kode19.financialtracker.financialtracker.dto.CategoryDTO;
import com.kode19.financialtracker.financialtracker.paging.PagingResponse;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CategoryServiceTest {

  @Autowired
  private CategoryService categoryService;

  @Test
  void testGetAllCategoriesValid() {
    // Arrange
    int page = 0;
    int size = 10;
    String sortBy = "name";
    String direction = "asc";

    // Act
    PagingResponse<List<CategoryDTO>> response = categoryService.getAllCategories(page, size, sortBy, direction);

    // Assert
    assertNotNull(response);
    assertEquals(page, response.getCurrentPage());
    assertTrue(response.getTotalItems() > 0);
    assertTrue(response.getTotalPages() > 0);
    assertFalse(response.getData().isEmpty());
  }


}