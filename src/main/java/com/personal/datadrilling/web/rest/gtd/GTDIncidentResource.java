package com.personal.datadrilling.web.rest.gtd;

import com.codahale.metrics.annotation.Timed;
import com.personal.datadrilling.domain.gtd.Country;
import com.personal.datadrilling.domain.gtd.GTDIncident;
import com.personal.datadrilling.repository.gtd.GTDIncidentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class GTDIncidentResource {

    private final Logger log = LoggerFactory.getLogger(GTDIncidentResource.class);

    @Inject
    private GTDIncidentRepository gtdIncidentRepository;


    @GetMapping("/allIncidents")
    @Timed
    public ResponseEntity<List<GTDIncident>> getAllIncidents() {
        List<GTDIncident> gtdIncidents = gtdIncidentRepository.findAll();

        return new ResponseEntity<>(gtdIncidents, HttpStatus.OK);
    }

    @PostMapping("/allIncidentsByCountry")
    @Timed
    public ResponseEntity<List<GTDIncident>> getAllIncidentsByCountry(@RequestBody String country) {
        List<GTDIncident> gtdIncidents = gtdIncidentRepository.findAllByCountry(country);

        return new ResponseEntity<>(gtdIncidents, HttpStatus.OK);
    }

    @PostMapping("/allIncidentsByListOfCountries")
    @Timed
    public ResponseEntity<List<GTDIncident>> getIncidentsByListOfCountries(@RequestBody List<Country> countries) {
        List<String> countriesNames = countries.stream().map(Country::getName).collect(Collectors.toList());

        List<GTDIncident> gtdIncidents = gtdIncidentRepository.findAllByCountryIn(countriesNames);

        return new ResponseEntity<>(gtdIncidents, HttpStatus.OK);
    }


}
