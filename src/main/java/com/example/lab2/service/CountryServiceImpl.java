package com.example.lab2.service;

import com.example.lab2.model.Country;
import com.example.lab2.model.exceptions.InvalidCountryIdException;
import com.example.lab2.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService{

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> listAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Country findById(Long id) {
        return this.countryRepository.findById(id).orElseThrow(InvalidCountryIdException::new);
    }

    @Override
    public Country create(String name, String continent) {
        Country country= new Country(name,continent);
        return this.countryRepository.save(country);
    }
}
