package com.rafaniander.casadocodigo.greendogdelivery.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import com.rafaniander.casadocodigo.greendogdelivery.entity.Cliente;
import com.rafaniander.casadocodigo.greendogdelivery.entity.Item;
import com.rafaniander.casadocodigo.greendogdelivery.entity.Pedido;
import com.rafaniander.casadocodigo.greendogdelivery.repository.ClienteRepository;
import com.rafaniander.casadocodigo.greendogdelivery.repository.ItemRepository;
import com.rafaniander.casadocodigo.greendogdelivery.repository.PedidoRepository;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoRepository pedidoRepository;
    private final ItemRepository itemRepository;
    private final ClienteRepository clienteRepository;
    private final String PEDIDO_URI = "pedidos/";

    public PedidoController(PedidoRepository pedidoRepository, ItemRepository itemRepository,
            ClienteRepository clienteRepository) {
        this.pedidoRepository = pedidoRepository;
        this.itemRepository = itemRepository;
        this.clienteRepository = clienteRepository;
    }

    @GetMapping("/")
    public ModelAndView list() {
        Iterable<Pedido> pedidos = this.pedidoRepository.findAll();
        return new ModelAndView(PEDIDO_URI + "list", "pedidos", pedidos);
    }

    @GetMapping("{id}")
    public ModelAndView view(@PathVariable("id") Pedido pedido) {
        return new ModelAndView(PEDIDO_URI + "view", "pedido", pedido);
    }

    @GetMapping("/novo")
    public ModelAndView createForm(@ModelAttribute Pedido pedido) {

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("todosItens", itemRepository.findAll());
        model.put("todosClientes", clienteRepository.findAll());
        return new ModelAndView(PEDIDO_URI + "form", model);

    }

    @PostMapping(params = "form")
    public ModelAndView create(@Valid Pedido pedido, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return new ModelAndView(PEDIDO_URI + "form", "formErrors", result.getAllErrors());
        }

        if (pedido.getId() != null) {
            Pedido pedidoParaAlterar = pedidoRepository.findOne(pedido.getId());
            Cliente c = clienteRepository.findOne(pedidoParaAlterar.getCliente().getId());
            pedidoParaAlterar.setItens(pedido.getItens());
            double valorTotal = 0;
            for (Item i : pedido.getItens()) {
                valorTotal += i.getPreco();
            }
            pedidoParaAlterar.setData(pedido.getData());
            pedidoParaAlterar.setValorTotal(valorTotal);
            c.getPedidos().remove(pedidoParaAlterar);
            c.getPedidos().add(pedidoParaAlterar);
            this.clienteRepository.save(c);
        } else {
            Cliente c = clienteRepository.findOne(pedido.getCliente().getId());
            double valorTotal = 0;
            for (Item i : pedido.getItens()) {
                valorTotal += i.getPreco();
            }
            pedido.setValorTotal(valorTotal);
            pedido = this.pedidoRepository.save(pedido);
            c.getPedidos().add(pedido);
            this.clienteRepository.save(c);
        }
        redirect.addFlashAttribute("globalMessage", "Pedido gravado com sucesso");
        return new ModelAndView("redirect:/" + PEDIDO_URI + "{pedido.id}", "pedido.id", pedido.getId());
    }

    @GetMapping(value = "alterar/{id}")
    public ModelAndView alterarForm(@PathVariable("id") Pedido pedido) {

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("todosItens", itemRepository.findAll());
        model.put("todosClientes", clienteRepository.findAll());
        model.put("pedido", pedido);

        return new ModelAndView(PEDIDO_URI + "form", model);
    }

    @GetMapping(value = "remover/{id}")
    public ModelAndView remover(@PathVariable("id") Long id, RedirectAttributes redirect) {

        Pedido pedidoParaRemover = pedidoRepository.findOne(id);

        Cliente c = clienteRepository.findOne(pedidoParaRemover.getCliente().getId());
        c.getPedidos().remove(pedidoParaRemover);

        this.clienteRepository.save(c);
        this.pedidoRepository.delete(id);

        Iterable<Pedido> pedidos = this.pedidoRepository.findAll();

        ModelAndView mv = new ModelAndView(PEDIDO_URI + "list", "pedidos", pedidos);
        mv.addObject("globalMessage", "Pedido removido com sucesso");

        return mv;
    }

}