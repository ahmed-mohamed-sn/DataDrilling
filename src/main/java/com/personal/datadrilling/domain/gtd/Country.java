package com.personal.datadrilling.domain.gtd;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by 605752564 on 08/03/2017.
 */
@Entity
@Table(name = "COUNTRY")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Country implements Serializable, Comparable<Country> {

    @Id
    @Column(name = "name")
    private String name;

    public Country() {
    }

    public Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        return name != null ? name.equals(country.name) : country.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Country{" +
            "name='" + name + '\'' +
            '}';
    }

    @Override
    public int compareTo(Country o) {
        return this.getName().compareTo(o.getName());
    }
}
