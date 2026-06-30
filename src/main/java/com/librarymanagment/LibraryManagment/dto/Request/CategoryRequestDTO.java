package com.librarymanagment.LibraryManagment.dto.Request;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDTO(
                          @NotBlank
                          String name) {
}
