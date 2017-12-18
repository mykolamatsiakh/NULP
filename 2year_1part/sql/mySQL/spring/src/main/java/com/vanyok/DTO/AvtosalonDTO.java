package com.vanyok.DTO;

import com.vanyok.controller.VlasnykController;
import com.vanyok.domain.Avtosalon;
import com.vanyok.exceptions.NoSuchBookException;
import com.vanyok.exceptions.NoSuchPersonException;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class AvtosalonDTO extends ResourceSupport {
    Avtosalon avtosalon;
    public AvtosalonDTO(Avtosalon avtosalon, Link selfLink) throws NoSuchBookException, NoSuchPersonException {
        this.avtosalon = avtosalon;
        add(selfLink);
        add(linkTo(methodOn(VlasnykController.class).getVlasnyksByAvtosalonID(avtosalon.getId())).withRel("vlasnyks"));
    }

    public Long getAvtosalonId() {
        return avtosalon.getId();
    }

    public String getAvtosalonname() {
        return avtosalon.getAvtosalonName();
    }

    public String getAuthor() {
        return avtosalon.getAuthor();
    }

    public String getSeller() {
        return avtosalon.getSeller();
    }

    public Integer getYear_of_creating() {
        return avtosalon.getYear_of_creating();
    }

    public Integer getAmount() {
        return avtosalon.getAmount();
    }
}
