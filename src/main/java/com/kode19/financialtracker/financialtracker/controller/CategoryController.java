package com.kode19.financialtracker.financialtracker.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import com.kode19.financialtracker.financialtracker.dto.CategoryDTO;
import com.kode19.financialtracker.financialtracker.paging.PagingResponse;
import com.kode19.financialtracker.financialtracker.service.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/category")
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping
  public ResponseEntity<PagingResponse> getAllCategories(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(defaultValue = "idCategory") String sortBy,
      @RequestParam(defaultValue = "ASC") String direction
  ) {
    return new ResponseEntity<>(categoryService.getAllCategories(page, size, sortBy, direction),
        OK);
  }

  @GetMapping("{id}")
  public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable("id") int idCategory) {
    return new ResponseEntity<>(categoryService.getCategoryById(idCategory), OK);
  }

  @PostMapping()
  public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
    return new ResponseEntity<>(categoryService.createCategory(categoryDTO), CREATED);
  }

  @PutMapping("{id}")
  public ResponseEntity<CategoryDTO> updateCategory(
      @PathVariable("id") int idCategory,
      @Valid @RequestBody CategoryDTO categoryDTO
  ) {
    return new ResponseEntity<>(categoryService.updateCategory(idCategory, categoryDTO), OK);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteCategory(@PathVariable("id") int idCategory) {
    categoryService.deleteCategory(idCategory);
    return new ResponseEntity<>(null, OK);
  }
}
