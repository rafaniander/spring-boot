package com.rafaniander.casadocodigo.greendogdelivery.repository;

import com.rafaniander.casadocodigo.greendogdelivery.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
