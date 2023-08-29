package com.kode19.financialtracker.financialtracker.service;

import com.kode19.financialtracker.financialtracker.dto.CategoryDTO;
import com.kode19.financialtracker.financialtracker.paging.PagingResponse;
import java.util.List;

public interface CategoryService {

  PagingResponse<List<CategoryDTO>> getAllCategories(int page, int size, String sortBy,
      String direction);

  CategoryDTO getCategoryById(int idCategory);

  CategoryDTO createCategory(CategoryDTO categoryDTO);

  CategoryDTO updateCategory(int idCategory, CategoryDTO categoryDTO);

  void deleteCategory(int idCategory);
}
