package com.vanyok.DTO;

import com.vanyok.controller.AvtosalonController;
import com.vanyok.domain.Vlasnyk;
import com.vanyok.exceptions.NoSuchBookException;
import com.vanyok.exceptions.NoSuchPersonException;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class VlasnykDTO extends ResourceSupport {
    Vlasnyk vlasnyk;
    public VlasnykDTO(Vlasnyk vlasnyk, Link selfLink) throws NoSuchPersonException, NoSuchBookException {
        this.vlasnyk = vlasnyk;
        add(selfLink);
        add(linkTo(methodOn(AvtosalonController.class).getAvtosalonsByVlasnykID(vlasnyk.getId())).withRel("avtosalons"));
    }

    public Long gerVlasnykId() {
        return vlasnyk.getId();
    }

    public String getSurname() {
        return vlasnyk.getSurname();
    }

    public String getName() {
        return vlasnyk.getName();
    }

    public String getEmail() {
        return vlasnyk.getEmail();
    }

    public String getCity() {
        if(vlasnyk.getCity()==null) return "";
        return vlasnyk.getCity().getCity();
    }

    public String getStreet() {
        return vlasnyk.getStreet();
    }

    public String getAvtosalonname() {
        return vlasnyk.getAvtosalonname();
    }
}
