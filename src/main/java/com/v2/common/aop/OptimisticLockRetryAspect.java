package com.v2.common.aop;

import com.v2.common.annotation.OptimisticRetry;
import jakarta.persistence.OptimisticLockException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class OptimisticLockRetryAspect {
    @Around("@annotation(retryAnnotation)")
    public Object doOptimisticRetry(ProceedingJoinPoint joinPoint, OptimisticRetry retryAnnotation) throws Throwable {
        int maxAttempts = retryAnnotation.maxAttempts();
        long delay = retryAnnotation.delay();

        int attempt = 0;
        while (true) {
            try {
                return joinPoint.proceed();
            } catch (OptimisticLockException e) {
                attempt++;
                if (attempt >= maxAttempts) {
                    throw new IllegalStateException("재시도 초과: OptimisticLockException", e);
                }
                Thread.sleep(delay);
            }
        }
    }
}
