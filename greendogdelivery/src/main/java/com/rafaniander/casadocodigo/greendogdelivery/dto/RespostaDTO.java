package com.rafaniander.casadocodigo.greendogdelivery.dto;

public class RespostaDTO {

    private Long pedido;
    private Double valorTotal;
    private String mensagem;

    public RespostaDTO(Long pedido, Double valorTotal, String mensagem) {
        this.pedido = pedido;
        this.valorTotal = valorTotal;
        this.mensagem = mensagem;
    }

    public RespostaDTO() {
    }

    public Long getPedido() {
        return pedido;
    }

    public void setPedido(Long pedido) {
        this.pedido = pedido;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

}
