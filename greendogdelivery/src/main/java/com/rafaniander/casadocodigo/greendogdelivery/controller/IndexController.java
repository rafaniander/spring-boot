package com.rafaniander.casadocodigo.greendogdelivery.controller;

import com.rafaniander.casadocodigo.greendogdelivery.dto.MensagemDTO;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@EnableAutoConfiguration
@RefreshScope 
@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/ambiente")
    public String ambiente() {
        return "ambiente";
    }

    @GetMapping("/delivery")
    public String delivery() {
        return "delivery/index";
    }

    @GetMapping("/properties")
    @ResponseBody
    public Properties properties() {
        return System.getProperties();
    }

    @Value("${mensagem:nenhuma}")
    private String message;

    @Value("${debug:0}")
    private String debug;

    @GetMapping("/oferta")
    @ResponseBody
    public MensagemDTO getMessage(HttpServletRequest request) {
        return new MensagemDTO(this.message, request.getServerName() + ":" + request.getServerPort(), this.debug);
    }

    @GetMapping("/servidor")
    @ResponseBody
    public String server(HttpServletRequest request) {
        return request.getServerName() + ":" + request.getServerPort();
    }

}
