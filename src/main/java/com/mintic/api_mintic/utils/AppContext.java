package com.mintic.api_mintic.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class AppContext implements ApplicationContextAware{

    private static ApplicationContext CONTEXTO;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    
        CONTEXTO = applicationContext;
        
    }

    public static Object getBean(String beanName){
        return CONTEXTO.getBean(beanName);
    }
    
}
