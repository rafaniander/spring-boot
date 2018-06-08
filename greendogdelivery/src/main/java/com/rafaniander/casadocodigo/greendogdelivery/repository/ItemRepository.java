package com.rafaniander.casadocodigo.greendogdelivery.repository;

import com.rafaniander.casadocodigo.greendogdelivery.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// @Repository
@RepositoryRestResource(collectionResourceRel = "itens", path = "itens")
public interface ItemRepository extends JpaRepository<Item, Long> {

}
