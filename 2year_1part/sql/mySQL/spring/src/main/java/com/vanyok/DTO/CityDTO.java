package com.vanyok.DTO;

import com.vanyok.controller.VlasnykController;
import com.vanyok.domain.City;
import com.vanyok.exceptions.NoSuchBookException;
import com.vanyok.exceptions.NoSuchCityException;
import com.vanyok.exceptions.NoSuchPersonException;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class CityDTO extends ResourceSupport {
    City city;
    public CityDTO(City city, Link selfLink) throws NoSuchPersonException, NoSuchBookException, NoSuchCityException {
        this.city=city;
        add(selfLink);
        add(linkTo(methodOn(VlasnykController.class).getVlasnyksByCityID(city.getId())).withRel("vlasnyks"));
    }

    public Long getCityId() { return city.getId(); }

    public String getCity() {
        return city.getCity();
    }
}
