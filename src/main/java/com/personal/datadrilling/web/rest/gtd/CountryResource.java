package com.personal.datadrilling.web.rest.gtd;

import com.codahale.metrics.annotation.Timed;
import com.personal.datadrilling.domain.gtd.Country;
import com.personal.datadrilling.repository.gtd.CountryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CountryResource {

    private final Logger log = LoggerFactory.getLogger(CountryResource.class);

    @Inject
    private CountryRepository countryRepository;


    @GetMapping("/allCountries")
    @Timed
    public ResponseEntity<List<Country>> getAllCountries() {
        List<Country> countries = countryRepository.findAll();
        Collections.sort(countries);

        return new ResponseEntity<List<Country>>(countries, HttpStatus.OK);
    }

}
