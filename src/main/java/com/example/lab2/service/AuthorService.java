package com.example.lab2.service;

import com.example.lab2.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> listAll();

    Optional <Author> findById(Long id);

    Author create( String name, String surname, Long countryId);

}
