package com.devsuperior.bds04.services;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.CityRepository;
import com.devsuperior.bds04.repositories.EventRepository;
import com.devsuperior.bds04.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

  @Autowired
  private EventRepository eventRepository;

  @Autowired
  private CityRepository cityRepository;

  @Transactional(readOnly = true)
  public Page<EventDTO> findAll(Pageable pageable) {
    Page<Event> page = eventRepository.findAll(pageable);
    return page.map(EventDTO::new);
  }

  @Transactional
  public EventDTO insert(EventDTO eventDTO) {
    Event event = new Event();
    event.setName(eventDTO.getName());
    event.setDate(eventDTO.getDate());
    event.setUrl(eventDTO.getUrl());
    event.setCity(new City(eventDTO.getCityId(), null));
    event = eventRepository.save(event);
    return new EventDTO(event);
  }

  @Transactional
  public EventDTO update(Long id, EventDTO eventDTO) {
    try {
      Event event = eventRepository.getOne(id);
      copyDtoToEvent(eventDTO, event);
      event = eventRepository.save(event);
      return new EventDTO(event);
    }catch (EntityNotFoundException e) {
      throw new ResourceNotFoundException("Id not found " + id);
    }
  }

  private void copyDtoToEvent(EventDTO eventDTO, Event event){
    event.setName(eventDTO.getName());
    event.setDate(eventDTO.getDate());
    event.setUrl(eventDTO.getUrl());
    event.setCity(new City(eventDTO.getCityId(), null));
  }


}
