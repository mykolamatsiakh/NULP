package com.vanyok.service;
import com.vanyok.Repository.AvtosalonRepository;
import com.vanyok.Repository.CityRepository;
import com.vanyok.Repository.VlasnykRepository;
import com.vanyok.domain.Avtosalon;
import com.vanyok.domain.City;
import com.vanyok.domain.Vlasnyk;
import com.vanyok.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
@Service
public class VlasnykService {
    @Autowired
    VlasnykRepository vlasnykRepository;
    @Autowired
    CityRepository cityRepository;
    @Autowired
    AvtosalonRepository avtosalonRepository;
    public List<Vlasnyk> getVlasnykByCityId(Long city_id) throws NoSuchCityException {
        City city = cityRepository.findById(city_id).get();
        if (city == null) throw new NoSuchCityException();
        return city.getVlasnyks();
    }
    public Vlasnyk getVlasnyk(Long vlasnyk_id) throws NoSuchPersonException {
        Vlasnyk vlasnyk = vlasnykRepository.findById(vlasnyk_id).get();
        if (vlasnyk == null) throw new NoSuchPersonException();
        return vlasnyk;
    }
    public List<Vlasnyk> getAllVlasnyks() {
        return vlasnykRepository.findAll();
    }
    public Set<Vlasnyk> getVlasnyksByAvtosalonId(Long avtosalon_id) throws NoSuchBookException {
        Avtosalon avtosalon = avtosalonRepository.findById(avtosalon_id).get();
        if (avtosalon == null) throw new NoSuchBookException();
        return avtosalon.getVlasnyks();
    }
    @Transactional
    public void createVlasnyk(Vlasnyk vlasnyk, Long city_id) throws NoSuchCityException {
        if (city_id > 0) {
            City city = cityRepository.findById(city_id).get();
            if (city == null) throw new NoSuchCityException();
            vlasnyk.setCity(city);
        }
        vlasnykRepository.save(vlasnyk);
    }
    @Transactional
    public void updateVlasnyk(Vlasnyk uVlasnyk, Long vlasnyk_id, Long city_id) throws NoSuchCityException, NoSuchPersonException {
        City city = cityRepository.findById(city_id).get();
        if (city_id > 0) {
            if (city == null) throw new NoSuchCityException();
        }
        Vlasnyk vlasnyk = vlasnykRepository.findById(vlasnyk_id).get();
        if (vlasnyk == null) throw new NoSuchPersonException();
        //update
        vlasnyk.setSurname(uVlasnyk.getSurname());
        vlasnyk.setName(uVlasnyk.getName());
        vlasnyk.setEmail(uVlasnyk.getEmail());
        if (city_id > 0) vlasnyk.setCity(city);
        else vlasnyk.setCity(null);
        vlasnyk.setStreet(uVlasnyk.getStreet());
        vlasnyk.setAvtosalonname(uVlasnyk.getAvtosalonname());
        vlasnykRepository.save(vlasnyk);
    }
    @Transactional
    public void deleteVlasnyk(Long vlasnyk_id) throws NoSuchPersonException, ExistsBooksForPersonException {
        Vlasnyk vlasnyk = vlasnykRepository.findById(vlasnyk_id).get();
        if (vlasnyk == null) throw new NoSuchPersonException();
        if (vlasnyk.getAvtosalons().size() != 0) throw new ExistsBooksForPersonException();
        vlasnykRepository.delete(vlasnyk);
    }
    @Transactional
    public void addAvtosalonForVlasnyk(Long vlasnyk_id, Long avtosalon_id)
            throws NoSuchPersonException, NoSuchBookException, AlreadyExistsBookInPersonException, BookAbsentException {
        Vlasnyk vlasnyk = vlasnykRepository.findById(vlasnyk_id).get();
        if (vlasnyk == null) throw new NoSuchPersonException();
        Avtosalon avtosalon = avtosalonRepository.findById(avtosalon_id).get();
        if (avtosalon == null) throw new NoSuchBookException();
        if (vlasnyk.getAvtosalons().contains(avtosalon) == true) throw new AlreadyExistsBookInPersonException();
        if (avtosalon.getAmount() <= avtosalon.getVlasnyks().size()) throw new BookAbsentException();
        vlasnyk.getAvtosalons().add(avtosalon);
        vlasnykRepository.save(vlasnyk);
    }
    @Transactional
    public void removeAvtosalonForVlasnyk(Long vlasnyk_id, Long avtosalon_id)
            throws NoSuchPersonException, NoSuchBookException, PersonHasNotBookException {
        Vlasnyk vlasnyk = vlasnykRepository.findById(vlasnyk_id).get();
        if (vlasnyk == null) throw new NoSuchPersonException();
        Avtosalon avtosalon = avtosalonRepository.findById(avtosalon_id).get();
        if (avtosalon == null) throw new NoSuchBookException();
        if (vlasnyk.getAvtosalons().contains(avtosalon) == false) throw new PersonHasNotBookException();
        vlasnyk.getAvtosalons().remove(avtosalon);
        vlasnykRepository.save(vlasnyk);
    }
}
