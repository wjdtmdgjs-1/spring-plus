/*
package org.example.expert.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class Log {
    private final LogRepository logRepository;

    @Transactional
    @Before("execution(* org.example.expert.domain.manager.controller.ManagerController.saveManager(..))")
    public void logBeforeSaveManager(JoinPoint joinPoint){
        LocalDateTime localDateTime = LocalDateTime.now();
        LogEntity logEntity = new LogEntity(localDateTime);
        logRepository.save(logEntity);
        log.info("Admin Access Log - Method: {}", joinPoint.getSignature().getName());
    }
    @Before("execution(* org.example.expert.domain.manager.controller.ManagerController.saveManager(..))")
    public void logBeforeSaveManager(JoinPoint joinPoint) {
        LocalDateTime localDateTime = LocalDateTime.now();
        try {
            saveLogEntity(localDateTime, joinPoint);
        } catch (Exception e) {
            log.error("Failed to log access for method: {}", joinPoint.getSignature().getName(), e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveLogEntity(LocalDateTime localDateTime, JoinPoint joinPoint) {
        LogEntity logEntity = new LogEntity(localDateTime);
        logRepository.save(logEntity);
        log.info("Admin Access Log - Method: {}",joinPoint.getSignature().getName());
    }
}
*/
