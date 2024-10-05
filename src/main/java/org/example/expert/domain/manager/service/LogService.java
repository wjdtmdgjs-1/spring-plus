package org.example.expert.domain.manager.service;

import lombok.RequiredArgsConstructor;
import org.example.expert.aop.LogEntity;
import org.example.expert.aop.LogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LogService {
    private final LogRepository logRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveLog(LocalDateTime localDateTime){
        LogEntity logEntity = new LogEntity(localDateTime);
        logRepository.save(logEntity);
    }
}
