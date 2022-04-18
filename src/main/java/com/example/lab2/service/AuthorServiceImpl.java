package com.example.lab2.service;

import com.example.lab2.model.Author;
import com.example.lab2.model.Country;
import com.example.lab2.model.exceptions.InvalidAuthorIdException;
import com.example.lab2.model.exceptions.InvalidCountryIdException;
import com.example.lab2.repository.AuthorRepository;
import com.example.lab2.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService{

    private final AuthorRepository authorRepository;

    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> listAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public Author create(String name, String surname, Long countryId) {
     Country country= this.countryRepository.findById(countryId).orElseThrow(InvalidCountryIdException::new);
     Author author = new Author(name,surname,country);
        return this.authorRepository.save(author);
    }
}
