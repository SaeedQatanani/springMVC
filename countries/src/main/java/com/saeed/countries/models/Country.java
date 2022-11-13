package com.saeed.countries.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="countries")
public class Country {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String code;
    @NotNull
    private String name;
    private enum Continent {
    	ASIA,
    	AFRICA,
    	NORTH_AMERICA,
    	EUROPE,
    	SOUTH_AMERICA,
    	OCEANIA,
    	ANTARCTICA
    }
    private Continent continent;
    @NotNull
    private String region;
    @NotNull
    private double surfaceArea;
    @NotNull
    private short indepYear;
    @NotNull
    private int population;
    @NotNull
    private double lifeExpectancy;
    @NotNull
    private double gnp;
    @NotNull
    private double gnpOld;
    @NotNull
    private String localName;
    @NotNull
    private String governmentForm;
    @NotNull
    private String headOfState;
    @NotNull
    private int capital;
    @NotNull
    private String code2;
    @OneToMany(mappedBy="country", fetch = FetchType.LAZY)
    private List<Language> languages;
    @OneToMany(mappedBy="country", fetch = FetchType.LAZY)
    private List<City> cities;
	public Country() {
	}
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Continent getContinent() {
		return continent;
	}
	public void setContinent(Continent continent) {
		this.continent = continent;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public double getSarfaceArea() {
		return surfaceArea;
	}
	public void setSarfaceArea(double sarfaceArea) {
		this.surfaceArea = sarfaceArea;
	}
	public short getIndepYear() {
		return indepYear;
	}
	public void setIndepYear(short indepYear) {
		this.indepYear = indepYear;
	}
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	public double getLifeExpectancy() {
		return lifeExpectancy;
	}
	public void setLifeExpectancy(double lifeExpectancy) {
		this.lifeExpectancy = lifeExpectancy;
	}
	public double getGnp() {
		return gnp;
	}
	public void setGnp(double gnp) {
		this.gnp = gnp;
	}
	public double getGnpOld() {
		return gnpOld;
	}
	public void setGnpOld(double gnpOld) {
		this.gnpOld = gnpOld;
	}
	public String getLocalName() {
		return localName;
	}
	public void setLocalName(String localName) {
		this.localName = localName;
	}
	public String getGovermentForm() {
		return governmentForm;
	}
	public void setGovermentForm(String govermentForm) {
		this.governmentForm = govermentForm;
	}
	public String getHeadOfState() {
		return headOfState;
	}
	public void setHeadOfState(String headOfState) {
		this.headOfState = headOfState;
	}
	public int getCapital() {
		return capital;
	}
	public void setCapital(int capital) {
		this.capital = capital;
	}
	public String getCode2() {
		return code2;
	}
	public void setCode2(String code2) {
		this.code2 = code2;
	}
	public List<Language> getLanguages() {
		return languages;
	}
	public void setLanguages(List<Language> languages) {
		this.languages = languages;
	}
	public List<City> getCities() {
		return cities;
	}
	public void setCities(List<City> cities) {
		this.cities = cities;
	}
    
}
