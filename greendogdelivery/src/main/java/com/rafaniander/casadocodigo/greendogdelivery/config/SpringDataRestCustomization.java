package com.rafaniander.casadocodigo.greendogdelivery.config;

import com.rafaniander.casadocodigo.greendogdelivery.entity.Item;
import com.rafaniander.casadocodigo.greendogdelivery.repository.ClienteRepository;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.stereotype.Component;

@Component
public class SpringDataRestCustomization extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Item.class, ClienteRepository.class);
    }

}