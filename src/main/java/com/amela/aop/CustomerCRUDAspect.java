package com.amela.aop;

import com.amela.service.CustomerService;
import com.amela.service.ICustomerService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CustomerCRUDAspect {

    @Before("execution(public * com.amela.service.CustomerService.*(..))")  //point-cut expression
    public void log(JoinPoint joinPoint)
    {
        System.out.println("EmployeeCRUDAspect.log() : " + joinPoint.getSignature().getName());
    }
}
