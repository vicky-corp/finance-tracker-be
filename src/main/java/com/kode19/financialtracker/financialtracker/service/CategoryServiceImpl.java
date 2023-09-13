package com.kode19.financialtracker.financialtracker.service;

import com.kode19.financialtracker.financialtracker.dto.CategoryDTO;
import com.kode19.financialtracker.financialtracker.dto.CategoryMapper;
import com.kode19.financialtracker.financialtracker.entity.Category;
import com.kode19.financialtracker.financialtracker.exception.MessageService;
import com.kode19.financialtracker.financialtracker.exception.custom_exception.DataNotAvailableException;
import com.kode19.financialtracker.financialtracker.paging.PagingResponse;
import com.kode19.financialtracker.financialtracker.repository.CategoryRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;
  private final CategoryMapper categoryMapper;
  private final PagingResponse<List<CategoryDTO>> pagingResponse;
  private final MessageService messageService;


  public PagingResponse<List<CategoryDTO>> getAllCategories(int page, int size, String sortBy,
      String direction) {
    Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(direction), sortBy);
    Page<Category> categories = categoryRepository.findAll(pageable);

    if (categories.isEmpty()) {
      throw new DataNotAvailableException(
          messageService.dataNotAvailable(Category.class.getSimpleName()));
    }

    List<CategoryDTO> categoryDTOS = categories
        .stream()
        .map(categoryMapper::toDTO)
        .toList();

    pagingResponse.setData(categoryDTOS);
    pagingResponse.setCurrentPage(categories.getNumber());
    pagingResponse.setTotalItems(categories.getTotalElements());
    pagingResponse.setTotalPages(categories.getTotalPages());

    return pagingResponse;

  }

  public CategoryDTO getCategoryById(int idCategory) {
    return categoryMapper.toDTO(categoryRepository.findById(idCategory)
        .orElseThrow(
            () -> new DataNotAvailableException(
                messageService.dataNotFound(Category.class.getSimpleName(),
                    String.valueOf(idCategory)))));
  }

  public CategoryDTO createCategory(CategoryDTO categoryDTO) {
    return categoryMapper.toDTO(
        categoryRepository.save(
            categoryMapper.toEntity(categoryDTO)
        )
    );
  }

  @Override
  public CategoryDTO updateCategory(int idCategory, CategoryDTO categoryDTO) {
    var category = categoryRepository.findById(idCategory)
        .orElseThrow(
            () -> new DataNotAvailableException(
                messageService.dataNotFound(Category.class.getSimpleName(),
                    String.valueOf(idCategory))));

    category.setName(categoryDTO.getName());
    Category save = categoryRepository.save(category);
    return categoryMapper.toDTO(save);
  }

  @Override
  public void deleteCategory(int idCategory) {
    var category = categoryRepository.findById(idCategory)
        .orElseThrow(
            () -> new DataNotAvailableException(
                messageService.dataNotFound(Category.class.getSimpleName(),
                    String.valueOf(idCategory))));
    categoryRepository.delete(category);
  }
}
