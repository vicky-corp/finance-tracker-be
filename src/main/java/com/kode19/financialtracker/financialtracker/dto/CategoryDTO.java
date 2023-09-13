package com.kode19.financialtracker.financialtracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDTO {

  private int idCategory;

  @NotBlank(message = "category name cannot be empty")
  @Size(max = 50, min = 5)
  private String name;

}