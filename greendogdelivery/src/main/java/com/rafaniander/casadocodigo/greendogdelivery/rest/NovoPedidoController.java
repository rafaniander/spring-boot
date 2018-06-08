package com.rafaniander.casadocodigo.greendogdelivery.rest;

import com.rafaniander.casadocodigo.greendogdelivery.dto.RespostaDTO;
import com.rafaniander.casadocodigo.greendogdelivery.entity.Cliente;
import com.rafaniander.casadocodigo.greendogdelivery.entity.Item;
import com.rafaniander.casadocodigo.greendogdelivery.entity.Pedido;
import com.rafaniander.casadocodigo.greendogdelivery.repository.ClienteRepository;
import com.rafaniander.casadocodigo.greendogdelivery.repository.ItemRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NovoPedidoController {

    private final ClienteRepository clienteRepository;
    private final ItemRepository itemRepository;

    public NovoPedidoController(ClienteRepository clienteRepository, ItemRepository itemRepository) {
        this.clienteRepository = clienteRepository;
        this.itemRepository = itemRepository;
    }

    @GetMapping("/rest/pedido/novo/{clienteId}/{listaDeItens}")
    public RespostaDTO novo(@PathVariable("clienteId") Long clienteId, @PathVariable("listaDeItens") String listaDeItens) {

        RespostaDTO dto = new RespostaDTO();
        try {
            Cliente c = clienteRepository.findOne(clienteId);
            String[] listaDeItensID = listaDeItens.split(",");

            Pedido pedido = new Pedido();
            double valorTotal = 0;
            List<Item> itensPedidos = new ArrayList<>();
            for (String itemId : listaDeItensID) {
                Item item = itemRepository.findOne(Long.parseLong(itemId));
                itensPedidos.add(item);
                valorTotal += item.getPreco();
            }
            pedido.setItens(itensPedidos);
            pedido.setValorTotal(valorTotal);
            pedido.setData(new Date());
            pedido.setCliente(c);
            c.getPedidos().add(pedido);
            this.clienteRepository.saveAndFlush(c);

            List<Long> pedidosID = new ArrayList<>();
            for (Pedido p : c.getPedidos()) {
                pedidosID.add(p.getId());
            }
            Long ultimoPedido = Collections.max(pedidosID);
            dto = new RespostaDTO(ultimoPedido, valorTotal, "Pedido efetuado com sucesso");
        } catch (Exception e) {
            dto.setMensagem("Erro: " + e.getMessage());
        }
        return dto;
    }

}
