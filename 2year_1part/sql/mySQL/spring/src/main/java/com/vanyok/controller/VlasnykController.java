package com.vanyok.controller;

import com.vanyok.DTO.VlasnykDTO;
import com.vanyok.domain.Vlasnyk;
import com.vanyok.exceptions.*;
import com.vanyok.service.VlasnykService;
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
public class VlasnykController {
    @Autowired
    VlasnykService vlasnykService;

    @GetMapping(value = "/api/vlasnyk/city/{city_id}")
    public ResponseEntity<List<VlasnykDTO>> getVlasnyksByCityID(@PathVariable Long city_id) throws NoSuchCityException, NoSuchPersonException, NoSuchBookException {
        List<Vlasnyk> vlasnykList = vlasnykService.getVlasnykByCityId(city_id);

        Link link = linkTo(methodOn(VlasnykController.class).getAllVlasnyks()).withSelfRel();

        List<VlasnykDTO> vlasnyksDTO = new ArrayList<>();
        for (Vlasnyk entity : vlasnykList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            VlasnykDTO dto = new VlasnykDTO(entity, selfLink);
            vlasnyksDTO.add(dto);
        }

        return new ResponseEntity<>(vlasnyksDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/vlasnyk/{vlasnyk_id}")
    public ResponseEntity<VlasnykDTO> getVlasnyk(@PathVariable Long vlasnyk_id) throws NoSuchPersonException, NoSuchBookException {
        Vlasnyk vlasnyk = vlasnykService.getVlasnyk(vlasnyk_id);
        Link link = linkTo(methodOn(VlasnykController.class).getVlasnyk(vlasnyk_id)).withSelfRel();

        VlasnykDTO vlasnykDTO = new VlasnykDTO(vlasnyk, link);

       return new ResponseEntity<>(vlasnykDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/vlasnyk")
    public ResponseEntity<List<VlasnykDTO>> getAllVlasnyks() throws NoSuchPersonException, NoSuchBookException {
        List<Vlasnyk> vlasnykList = vlasnykService.getAllVlasnyks();
        Link link = linkTo(methodOn(VlasnykController.class).getAllVlasnyks()).withSelfRel();

        List<VlasnykDTO> vlasnyksDTO = new ArrayList<>();
        for (Vlasnyk entity : vlasnykList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            VlasnykDTO dto = new VlasnykDTO(entity, selfLink);
            vlasnyksDTO.add(dto);
        }

        return new ResponseEntity<>(vlasnyksDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/vlasnyk/avtosalon/{avtosalon_id}")
    public ResponseEntity<List<VlasnykDTO>> getVlasnyksByAvtosalonID(@PathVariable Long avtosalon_id) throws NoSuchBookException, NoSuchPersonException {
        Set<Vlasnyk> vlasnykList = vlasnykService.getVlasnyksByAvtosalonId(avtosalon_id);
        Link link = linkTo(methodOn(VlasnykController.class).getAllVlasnyks()).withSelfRel();

        List<VlasnykDTO> vlasnyksDTO = new ArrayList<>();
        for (Vlasnyk entity : vlasnykList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            VlasnykDTO dto = new VlasnykDTO(entity, selfLink);
            vlasnyksDTO.add(dto);
        }

        return new ResponseEntity<>(vlasnyksDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/api/vlasnyk/city/{city_id}")
    public  ResponseEntity<VlasnykDTO> addVlasnyk(@RequestBody Vlasnyk newVlasnyk, @PathVariable Long city_id)
            throws NoSuchCityException, NoSuchPersonException, NoSuchBookException {
        vlasnykService.createVlasnyk(newVlasnyk, city_id);
        Link link = linkTo(methodOn(VlasnykController.class).getVlasnyk(newVlasnyk.getId())).withSelfRel();

        VlasnykDTO vlasnykDTO = new VlasnykDTO(newVlasnyk, link);

        return new ResponseEntity<>(vlasnykDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/vlasnyk/{vlasnyk_id}/city/{city_id}")
    public  ResponseEntity<VlasnykDTO> updateVlasnyk(@RequestBody Vlasnyk uVlasnyk,
                                                    @PathVariable Long vlasnyk_id, @PathVariable Long city_id)
            throws NoSuchCityException, NoSuchPersonException, NoSuchBookException {
        vlasnykService.updateVlasnyk(uVlasnyk, vlasnyk_id, city_id);
        Vlasnyk vlasnyk = vlasnykService.getVlasnyk(vlasnyk_id);
        Link link = linkTo(methodOn(VlasnykController.class).getVlasnyk(vlasnyk_id)).withSelfRel();

        VlasnykDTO vlasnykDTO = new VlasnykDTO(vlasnyk, link);

        return new ResponseEntity<>(vlasnykDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/vlasnyk/{vlasnyk_id}")
    public  ResponseEntity deleteVlasnyk(@PathVariable Long vlasnyk_id) throws NoSuchPersonException, ExistsBooksForPersonException {
        vlasnykService.deleteVlasnyk(vlasnyk_id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/api/vlasnyk/{vlasnyk_id}/avtosalon/{avtosalon_id}")
    public  ResponseEntity<VlasnykDTO> addAvtosalonForVlasnyk(@PathVariable Long vlasnyk_id, @PathVariable Long avtosalon_id)
            throws NoSuchPersonException, NoSuchBookException, AlreadyExistsBookInPersonException, BookAbsentException {
        vlasnykService.addAvtosalonForVlasnyk(vlasnyk_id,avtosalon_id);
        Vlasnyk vlasnyk = vlasnykService.getVlasnyk(vlasnyk_id);
        Link link = linkTo(methodOn(VlasnykController.class).getVlasnyk(vlasnyk_id)).withSelfRel();
        VlasnykDTO vlasnykDTO = new VlasnykDTO(vlasnyk, link);
        return new ResponseEntity<>(vlasnykDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/vlasnyk/{vlasnyk_id}/avtosalon/{avtosalon_id}")
    public  ResponseEntity<VlasnykDTO> removeAvtosalonForVlasnyk(@PathVariable Long vlasnyk_id, @PathVariable Long avtosalon_id)
            throws NoSuchPersonException, NoSuchBookException, PersonHasNotBookException {
        vlasnykService.removeAvtosalonForVlasnyk(vlasnyk_id,avtosalon_id);
        Vlasnyk vlasnyk = vlasnykService.getVlasnyk(vlasnyk_id);
        Link link = linkTo(methodOn(VlasnykController.class).getVlasnyk(vlasnyk_id)).withSelfRel();

        VlasnykDTO vlasnykDTO = new VlasnykDTO(vlasnyk, link);

        return new ResponseEntity<>(vlasnykDTO, HttpStatus.OK);
    }

}
