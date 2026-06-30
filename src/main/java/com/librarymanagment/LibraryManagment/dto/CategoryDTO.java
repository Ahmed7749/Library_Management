package com.librarymanagment.LibraryManagment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryDTO(
                          @NotBlank
                          String name) {
}
