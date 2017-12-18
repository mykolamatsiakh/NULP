package com.vanyok.service;

import com.vanyok.Repository.AvtosalonRepository;
import com.vanyok.Repository.VlasnykRepository;
import com.vanyok.domain.Avtosalon;
import com.vanyok.domain.Vlasnyk;
import com.vanyok.exceptions.ExistsPersonForBookException;
import com.vanyok.exceptions.NoSuchBookException;
import com.vanyok.exceptions.NoSuchPersonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class AvtosalonService {
    @Autowired
    AvtosalonRepository avtosalonRepository;

    @Autowired
    VlasnykRepository vlasnykRepository;

    public Set<Avtosalon> getAvtosalonsByVlasnykId(Long vlasnyk_id) throws NoSuchPersonException {
//        Vlasnyk vlasnyk = personRepository.findOne(vlasnyk_id);//1.5.9
        Vlasnyk vlasnyk = vlasnykRepository.findById(vlasnyk_id).get();//2.0.0.M7
        if (vlasnyk == null) throw new NoSuchPersonException();
        return vlasnyk.getAvtosalons();
    }

    public Avtosalon getAvtosalon(Long avtosalon_id) throws NoSuchBookException {
//        Avtosalon avtosalon = bookRepository.findOne(avtosalon_id);//1.5.9
        Avtosalon avtosalon = avtosalonRepository.findById(avtosalon_id).get();//2.0.0.M7
        if (avtosalon == null) throw new NoSuchBookException();
        return avtosalon;
    }

    public List<Avtosalon> getAllAvtosalons() {
        return avtosalonRepository.findAll();
    }

    @Transactional
    public void createAvtosalon(Avtosalon avtosalon) {
        avtosalonRepository.save(avtosalon);
    }

    @Transactional
    public void updateAvtosalon(Avtosalon uAvtosalon, Long avtosalon_id) throws NoSuchBookException {
//        Avtosalon avtosalon = bookRepository.findOne(avtosalon_id);//1.5.9
        Avtosalon avtosalon = avtosalonRepository.findById(avtosalon_id).get();//2.0.0.M7
        if (avtosalon == null) throw new NoSuchBookException();
        //update
        avtosalon.setAvtosalonName(uAvtosalon.getAvtosalonName());
        avtosalon.setAuthor(uAvtosalon.getAuthor());
        avtosalon.setSeller(uAvtosalon.getSeller());
        avtosalon.setYear_of_creating(uAvtosalon.getYear_of_creating());
        avtosalon.setAmount(uAvtosalon.getAmount());
    }

    @Transactional
    public void deleteAvtosalon(Long avtosalon_id) throws NoSuchBookException, ExistsPersonForBookException {
//        Avtosalon avtosalon = bookRepository.findOne(avtosalon_id);//1.5.9
        Avtosalon avtosalon = avtosalonRepository.findById(avtosalon_id).get();//2.0.0.M7

        if (avtosalon == null) throw new NoSuchBookException();
        if (avtosalon.getVlasnyks().size() != 0) throw new ExistsPersonForBookException();
        avtosalonRepository.delete(avtosalon);
    }
}
