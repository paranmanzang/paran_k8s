package com.paranmanzang.commentservice.service.impl;

import com.paranmanzang.commentservice.model.domain.BookResponseModel;
import com.paranmanzang.commentservice.model.repository.BookRepository;
import com.paranmanzang.commentservice.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;


    @Override
    public Page<BookResponseModel> getBookList(Pageable pageable) {
        return bookRepository.findAllBooks(pageable);
    }
}
