package com.vanyok.controller;
import com.vanyok.DTO.AvtosalonDTO;
import com.vanyok.domain.Avtosalon;
import com.vanyok.exceptions.ExistsPersonForBookException;
import com.vanyok.exceptions.NoSuchBookException;
import com.vanyok.exceptions.NoSuchPersonException;
import com.vanyok.service.AvtosalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class AvtosalonController {
    @Autowired
    AvtosalonService avtosalonService;
    @GetMapping(value = "/api/avtosalon/vlasnyk/{vlasnyk_id}")
    public ResponseEntity<List<AvtosalonDTO>> getAvtosalonsByVlasnykID(@PathVariable Long vlasnyk_id) throws NoSuchPersonException, NoSuchBookException {
        Set<Avtosalon> avtosalonList = avtosalonService.getAvtosalonsByVlasnykId(vlasnyk_id);
        Link link = linkTo(methodOn(AvtosalonController.class).getAllAvtosalons()).withSelfRel();
        List<AvtosalonDTO> avtosalonsDTO = new ArrayList<>();
        for (Avtosalon entity : avtosalonList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            AvtosalonDTO dto = new AvtosalonDTO(entity, selfLink);
            avtosalonsDTO.add(dto);
        }
        return new ResponseEntity<>(avtosalonsDTO, HttpStatus.OK);
    }
    @GetMapping(value = "/api/avtosalon/{avtosalon_id}")
    public ResponseEntity<AvtosalonDTO> getAvtosalon(@PathVariable Long avtosalon_id) throws NoSuchBookException, NoSuchPersonException {
        Avtosalon avtosalon = avtosalonService.getAvtosalon(avtosalon_id);
        Link link = linkTo(methodOn(AvtosalonController.class).getAvtosalon(avtosalon_id)).withSelfRel();
        AvtosalonDTO avtosalonDTO = new AvtosalonDTO(avtosalon, link);
        return new ResponseEntity<>(avtosalonDTO, HttpStatus.OK);
    }
    @GetMapping(value = "/api/avtosalon")
    public ResponseEntity<List<AvtosalonDTO>> getAllAvtosalons() throws NoSuchBookException, NoSuchPersonException {
        List<Avtosalon> avtosalonList = avtosalonService.getAllAvtosalons();
        Link link = linkTo(methodOn(AvtosalonController.class).getAllAvtosalons()).withSelfRel();
        List<AvtosalonDTO> carsDTO = new ArrayList<>();
        for (Avtosalon entity : avtosalonList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            AvtosalonDTO dto = new AvtosalonDTO(entity, selfLink);
            carsDTO.add(dto);
        }
        return new ResponseEntity<>(carsDTO, HttpStatus.OK);
    }
    @PostMapping(value = "/api/avtosalon")
    public ResponseEntity<AvtosalonDTO> addAvtosalon(@RequestBody Avtosalon newAvtosalon) throws NoSuchBookException, NoSuchPersonException {
        avtosalonService.createAvtosalon(newAvtosalon);
        Link link = linkTo(methodOn(AvtosalonController.class).getAvtosalon(newAvtosalon.getId())).withSelfRel();
        AvtosalonDTO avtosalonDTO = new AvtosalonDTO(newAvtosalon, link);
        return new ResponseEntity<>(avtosalonDTO, HttpStatus.CREATED);
    }
    @PutMapping(value = "/api/avtosalon/{avtosalon_id}")
    public ResponseEntity<AvtosalonDTO> updateAvtosalon(@RequestBody Avtosalon uAvtosalon, @PathVariable Long avtosalon_id) throws NoSuchBookException, NoSuchPersonException {
        avtosalonService.updateAvtosalon(uAvtosalon, avtosalon_id);
        Avtosalon avtosalon = avtosalonService.getAvtosalon(avtosalon_id);
        Link link = linkTo(methodOn(AvtosalonController.class).getAvtosalon(avtosalon_id)).withSelfRel();
        AvtosalonDTO avtosalonDTO = new AvtosalonDTO(avtosalon, link);
        return new ResponseEntity<>(avtosalonDTO, HttpStatus.OK);
    }
    @DeleteMapping(value = "/api/avtosalon/{avtosalon_id}")
    public  ResponseEntity deleteAvtosalon(@PathVariable Long avtosalon_id) throws ExistsPersonForBookException, NoSuchBookException {
        avtosalonService.deleteAvtosalon(avtosalon_id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
