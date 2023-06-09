package com.stf.service;

import com.stf.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Author creatAuthor(Author author);

    Author updateAuthor(Author author);

    Optional<Author> findAuthorById(Long id);

    void deleteAuthorById(Long id);

    List<Author> findAllAuthor();

    List<Author> findByNickNameLick(String nickName);

    Page<Author> findAll(Pageable pageable);
}
