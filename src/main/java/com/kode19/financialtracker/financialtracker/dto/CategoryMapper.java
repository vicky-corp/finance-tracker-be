package com.kode19.financialtracker.financialtracker.dto;

import com.kode19.financialtracker.financialtracker.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

  public CategoryDTO toDTO(Category category) {
    CategoryDTO categoryDTO = new CategoryDTO();
    categoryDTO.setIdCategory(category.getIdCategory());
    categoryDTO.setName(category.getName().toUpperCase());
    return categoryDTO;
  }

  public Category toEntity(CategoryDTO categoryDTO) {
    Category category = new Category();
    category.setIdCategory(categoryDTO.getIdCategory());
    category.setName(categoryDTO.getName().toUpperCase());
    return category;
  }

}
