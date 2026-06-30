package com.librarymanagment.LibraryManagment.dto.Response;

public record BookAuthorDTO(String bookName, String isbn, int pageCount, String status, String authorName){}
