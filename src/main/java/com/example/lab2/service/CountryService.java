package com.example.lab2.service;

import com.example.lab2.model.Country;
import java.util.List;

public interface CountryService {

    List<Country> listAll();

    Country findById(Long id);

    Country create( String name, String continent);

}
