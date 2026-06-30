package com.librarymanagment.LibraryManagment.dto.Response;

import java.util.Date;

public record BookResponseDTO(long id, String bookName, String isbn, int pageCount, String status, Date addedDate, String authorName, String categoryName) {
}
