package com.personal.datadrilling.domain.gtd;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by 605752564 on 08/03/2017.
 */
@Entity
@Table(name = "GTD")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class GTDIncident implements Serializable {

    @Id
    @Column(name = "eventid")
    private String eventId;

    @Column(name = "iyear")
    private String year;

    @Column(name = "imonth")
    private String month;

    @Column(name = "iday")
    private String day;

    @Column(name = "country_txt")
    private String country;

    @Column(name = "region_txt")
    private String region;

    @Column(name = "city")
    private String city;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "targtype1_txt")
    private String targetType;

    @Column(name = "target1")
    private String targetDetailed;

    @Column(name = "natlty1_txt")
    private String nationality;

    @Column(name = "gname")
    private String groupResponsible;

    @Column(name = "nperps")
    private String numberOfPerpetratorsText;

    @Transient
    @JsonSerialize
    @JsonDeserialize
    private Double numberOfPerpetrators;

    @Column(name = "weaptype1_txt")
    private String weapon;

    @Column(name = "nkill")
    private String numberOfKills;

    @Column(name = "nwound")
    private String numberOfWounded;

    @Column(name = "propvalue")
    private String valueOfDamageInUSD;

    @Column(name = "dbsource")
    private String source;

    public GTDIncident() {
    }


    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public String getNumberOfPerpetratorsText() {
        return numberOfPerpetratorsText;
    }

    public void setNumberOfPerpetratorsText(String numberOfPerpetratorsText) {
        this.numberOfPerpetratorsText = numberOfPerpetratorsText;

        if(!numberOfPerpetratorsText.equals("-99") && !numberOfPerpetratorsText.equals("Unknown") && !numberOfPerpetratorsText.isEmpty())
        {
            setNumberOfPerpetrators(Double.parseDouble(numberOfPerpetratorsText));
        }
        else {
            setNumberOfPerpetrators(0.0);
        }
    }

    public Double getNumberOfPerpetrators() {

        if(!numberOfPerpetratorsText.equals("-99") && !numberOfPerpetratorsText.equals("Unknown") && !numberOfPerpetratorsText.isEmpty())
        {
            setNumberOfPerpetrators(Double.parseDouble(numberOfPerpetratorsText));
        }
        else {
            setNumberOfPerpetrators(0.0);
        }

        return numberOfPerpetrators;
    }

    public void setNumberOfPerpetrators(Double numberOfPerpetrators) {
        this.numberOfPerpetrators = numberOfPerpetrators;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getTargetDetailed() {
        return targetDetailed;
    }

    public void setTargetDetailed(String targetDetailed) {
        this.targetDetailed = targetDetailed;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getGroupResponsible() {
        return groupResponsible;
    }

    public void setGroupResponsible(String groupResponsible) {
        this.groupResponsible = groupResponsible;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public String getNumberOfKills() {
        return numberOfKills;
    }

    public void setNumberOfKills(String numberOfKills) {
        this.numberOfKills = numberOfKills;
    }

    public String getNumberOfWounded() {
        return numberOfWounded;
    }

    public void setNumberOfWounded(String numberOfWounded) {
        this.numberOfWounded = numberOfWounded;
    }

    public String getValueOfDamageInUSD() {
        return valueOfDamageInUSD;
    }

    public void setValueOfDamageInUSD(String valueOfDamageInUSD) {
        this.valueOfDamageInUSD = valueOfDamageInUSD;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GTDIncident that = (GTDIncident) o;

        if (eventId != null ? !eventId.equals(that.eventId) : that.eventId != null) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;
        if (month != null ? !month.equals(that.month) : that.month != null) return false;
        if (day != null ? !day.equals(that.day) : that.day != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (region != null ? !region.equals(that.region) : that.region != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (latitude != null ? !latitude.equals(that.latitude) : that.latitude != null) return false;
        if (longitude != null ? !longitude.equals(that.longitude) : that.longitude != null) return false;
        if (targetType != null ? !targetType.equals(that.targetType) : that.targetType != null) return false;
        if (targetDetailed != null ? !targetDetailed.equals(that.targetDetailed) : that.targetDetailed != null)
            return false;
        if (nationality != null ? !nationality.equals(that.nationality) : that.nationality != null) return false;
        if (groupResponsible != null ? !groupResponsible.equals(that.groupResponsible) : that.groupResponsible != null)
            return false;
        if (numberOfPerpetratorsText != null ? !numberOfPerpetratorsText.equals(that.numberOfPerpetratorsText) : that.numberOfPerpetratorsText != null)
            return false;
        if (numberOfPerpetrators != null ? !numberOfPerpetrators.equals(that.numberOfPerpetrators) : that.numberOfPerpetrators != null)
            return false;
        if (weapon != null ? !weapon.equals(that.weapon) : that.weapon != null) return false;
        if (numberOfKills != null ? !numberOfKills.equals(that.numberOfKills) : that.numberOfKills != null)
            return false;
        if (numberOfWounded != null ? !numberOfWounded.equals(that.numberOfWounded) : that.numberOfWounded != null)
            return false;
        if (valueOfDamageInUSD != null ? !valueOfDamageInUSD.equals(that.valueOfDamageInUSD) : that.valueOfDamageInUSD != null)
            return false;
        return source != null ? source.equals(that.source) : that.source == null;

    }

    @Override
    public int hashCode() {
        int result = eventId != null ? eventId.hashCode() : 0;
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (month != null ? month.hashCode() : 0);
        result = 31 * result + (day != null ? day.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        result = 31 * result + (targetType != null ? targetType.hashCode() : 0);
        result = 31 * result + (targetDetailed != null ? targetDetailed.hashCode() : 0);
        result = 31 * result + (nationality != null ? nationality.hashCode() : 0);
        result = 31 * result + (groupResponsible != null ? groupResponsible.hashCode() : 0);
        result = 31 * result + (numberOfPerpetratorsText != null ? numberOfPerpetratorsText.hashCode() : 0);
        result = 31 * result + (numberOfPerpetrators != null ? numberOfPerpetrators.hashCode() : 0);
        result = 31 * result + (weapon != null ? weapon.hashCode() : 0);
        result = 31 * result + (numberOfKills != null ? numberOfKills.hashCode() : 0);
        result = 31 * result + (numberOfWounded != null ? numberOfWounded.hashCode() : 0);
        result = 31 * result + (valueOfDamageInUSD != null ? valueOfDamageInUSD.hashCode() : 0);
        result = 31 * result + (source != null ? source.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GTDIncident{" +
            "eventId='" + eventId + '\'' +
            ", year='" + year + '\'' +
            ", month='" + month + '\'' +
            ", day='" + day + '\'' +
            ", country='" + country + '\'' +
            ", region='" + region + '\'' +
            ", city='" + city + '\'' +
            ", latitude='" + latitude + '\'' +
            ", longitude='" + longitude + '\'' +
            ", targetType='" + targetType + '\'' +
            ", targetDetailed='" + targetDetailed + '\'' +
            ", nationality='" + nationality + '\'' +
            ", groupResponsible='" + groupResponsible + '\'' +
            ", numberOfPerpetratorsText='" + numberOfPerpetratorsText + '\'' +
            ", numberOfPerpetrators=" + numberOfPerpetrators +
            ", weapon='" + weapon + '\'' +
            ", numberOfKills='" + numberOfKills + '\'' +
            ", numberOfWounded='" + numberOfWounded + '\'' +
            ", valueOfDamageInUSD='" + valueOfDamageInUSD + '\'' +
            ", source='" + source + '\'' +
            '}';
    }
}
