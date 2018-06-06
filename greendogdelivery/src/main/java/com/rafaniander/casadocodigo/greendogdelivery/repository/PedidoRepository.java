package com.rafaniander.casadocodigo.greendogdelivery.repository;

import com.rafaniander.casadocodigo.greendogdelivery.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
