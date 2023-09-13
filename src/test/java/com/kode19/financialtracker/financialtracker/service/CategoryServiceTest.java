package com.kode19.financialtracker.financialtracker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.kode19.financialtracker.financialtracker.dto.CategoryDTO;
import com.kode19.financialtracker.financialtracker.entity.Category;
import com.kode19.financialtracker.financialtracker.exception.custom_exception.DataNotAvailableException;
import com.kode19.financialtracker.financialtracker.paging.PagingResponse;
import com.kode19.financialtracker.financialtracker.repository.CategoryRepository;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@RunWith(SpringRunner.class)
@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoryServiceTest {

  @Autowired
  CategoryRepository categoryRepository;

  @Autowired
  CategoryService categoryService;

  private Category savedCategory;

  @BeforeEach
  public void setUp() {
    String transportName = "TRANSPORTATION";
    Category transportCategory = Category.builder().name(transportName).build();
    savedCategory = categoryRepository.save(transportCategory);
  }

  @AfterEach
  public void deleteData() {
    categoryRepository.deleteAll();
  }

  @Test
  @DisplayName("Get All Categories")
  public void test_get_all_categories() {
    PagingResponse<List<CategoryDTO>> allCategories = categoryService.getAllCategories(0, 10,
        "name", "DESC");
    assertFalse(allCategories.getData().isEmpty());
  }

  @Test
  @DisplayName("Get All Categories With No Data")
  public void test_get_all_categories_with_no_data() {
    categoryRepository.deleteAll();
    assertThrows(DataNotAvailableException.class, () -> categoryService.getAllCategories(0, 10,
        "name", "DESC"));

  }

  @Test
  @DisplayName("Get Category By Id")
  void test_get_category_by_id() {
    CategoryDTO categoryById = categoryService.getCategoryById(savedCategory.getIdCategory());
    assertEquals(categoryById.getIdCategory(), savedCategory.getIdCategory());
  }

  @Test
  @DisplayName("Create Category")
  void test_create_category() {
    CategoryDTO categoryDTO = new CategoryDTO();
    String foodName = "FOOD";
    categoryDTO.setName(foodName);
    CategoryDTO category = categoryService.createCategory(categoryDTO);

    assertTrue(category.getIdCategory() > 0);
  }

  @Test
  @DisplayName("Update Category")
  void test_update_category() {
    CategoryDTO categoryDTO = new CategoryDTO();
    String leisureName = "LEISURE";
    categoryDTO.setName(leisureName);

    CategoryDTO updateCategory = categoryService.updateCategory(savedCategory.getIdCategory(),
        categoryDTO);

    assertTrue(leisureName.equalsIgnoreCase(updateCategory.getName()));
  }

  @Test
  @DisplayName("Update Category With Data Not Available")
  void test_update_category_with_data_not_available() {
    CategoryDTO categoryDTO = new CategoryDTO();
    String leisureName = "LEISURE";
    categoryDTO.setName(leisureName);

    assertThrows(DataNotAvailableException.class,
        () -> categoryService.updateCategory(0, categoryDTO));
  }

  @Test
  @DisplayName("Delete Category")
  void test_delete_category() {
    categoryService.deleteCategory(savedCategory.getIdCategory());
    assertThrowsExactly(DataNotAvailableException.class,
        () -> categoryService.getCategoryById(savedCategory.getIdCategory()));
  }

  @Test
  @DisplayName("Delete Non Existing Category")
  void test_delete_non_existing_category() {
    assertThrowsExactly(DataNotAvailableException.class, () -> categoryService.deleteCategory(0));
  }
}
