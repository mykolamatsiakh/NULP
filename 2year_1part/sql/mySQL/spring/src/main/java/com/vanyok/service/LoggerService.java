package com.vanyok.service;
import com.vanyok.Repository.LoggerRepository;
import com.vanyok.domain.Logger;
import com.vanyok.exceptions.NoSuchLogException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class LoggerService {
    @Autowired
    LoggerRepository loggerRepository;
    public List<Logger> getAllLogger() {
        return loggerRepository.findAll();
    }
    public List<Logger> getLoggerFilterBySurname(String like) {
        return loggerRepository.findByVlasnykLike(like + "%");
    }
    public Logger getLog(Long log_id) throws NoSuchLogException {
        Logger logger = loggerRepository.findById(log_id).get();
        if (logger == null) throw new NoSuchLogException();
        return logger;
    }
}
