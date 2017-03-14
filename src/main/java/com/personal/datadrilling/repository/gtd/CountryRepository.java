package com.personal.datadrilling.repository.gtd;

import com.personal.datadrilling.domain.gtd.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Spring Data JPA repository for the User entity.
 */
public interface CountryRepository extends JpaRepository<Country, String> {

    List<Country> findAll();

}
