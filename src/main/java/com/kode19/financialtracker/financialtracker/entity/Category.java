package com.kode19.financialtracker.financialtracker.entity;

import lombok.*;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idCategory;

  @Column(length=50, nullable=false, unique=true)
  private String name;

  @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Expense> expenses;
}