package com.devsuperior.bds04.dto;

import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EventDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	@NotBlank(message = "Campo requerido")
	private String name;

//	@PastOrPresent(message = "A data do evento não pode ser passada")
	private LocalDate date;

	private String url;

	@NotNull(message = "Campo requerido")
	private Long cityId;

	private final List<CityDTO> cities = new ArrayList<>();

	public EventDTO() {
	}

	public EventDTO(Long id, String name, LocalDate date, String url, Long cityId) {
		this.id = id;
		this.name = name;
		this.date = date;
		this.url = url;
		this.cityId = cityId;
	}

	public EventDTO(Event event) {
		id = event.getId();
		name = event.getName();
		date = event.getDate();
		url = event.getUrl();
		cityId = event.getCity().getId();
	}

	public EventDTO(Event event, Set<City> cities) {
		this(event);
		cities.forEach(city -> this.cities.add(new CityDTO(city)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public List<CityDTO> getCities() {
		return cities;
	}
}