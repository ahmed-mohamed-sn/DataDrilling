package com.personal.datadrilling.repository.gtd;

import com.personal.datadrilling.domain.gtd.GTDIncident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Spring Data JPA repository for the User entity.
 */
public interface GTDIncidentRepository extends JpaRepository<GTDIncident, String> {

    List<GTDIncident> findAll();

    List<GTDIncident> findAllByYear(String year);

    GTDIncident findOneByEventId(String eventId);

    List<GTDIncident> findAllByCountry(String country);

    List<GTDIncident> findAllByCountryIn(List<String> countries);
}
