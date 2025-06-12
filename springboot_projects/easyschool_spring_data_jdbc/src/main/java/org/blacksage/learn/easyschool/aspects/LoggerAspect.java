package org.blacksage.learn.easyschool.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Slf4j
@Aspect
@Component
public class LoggerAspect {

        @Around("execution(* org.blacksage.learn.easyschool..*.*(..))")
        public Object timeTaken(ProceedingJoinPoint pjp) throws Throwable {

                Instant start = Instant.now();
                Object obj = pjp.proceed();
                Instant end = Instant.now();

                log.info(
                        "Method {} took {} ms",
                        pjp.getSignature(),
                        end.toEpochMilli() - start.toEpochMilli()
                );

                return obj;
        }

        @AfterThrowing(
                value = "execution(* org.blacksage.learn.easyschool..*.*(..))",
                throwing = "ex"
        )
        public void logException(JoinPoint jp, Exception ex) {
                log.error(
                        "Method {} threw exception: {}",
                        jp.getSignature(),
                        ex.getMessage(),
                        ex
                );
        }
}
