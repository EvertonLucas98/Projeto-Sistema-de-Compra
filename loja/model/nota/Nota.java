package loja.model.nota;

import loja.model.cliente.Cliente;
import java.time.LocalDate;

public class Nota {
    private String numero;
    private LocalDate data;
    private Cliente cliente;
    private ItemNota[] itens;

    // Construtor
    public Nota() {}
    
    public Nota(String numero, LocalDate data, Cliente cliente) {
        this.numero = numero;
        this.data = data;
        this.cliente = cliente;
        this.itens = new ItemNota[100];
    }
 
    // Getters e Setters
    public String getNumero() {
        return numero;
    }
    
    public LocalDate getData() {
        return data;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ItemNota[] getItens() {
        return itens;
    }

    public void setItens(ItemNota[] itens) {
        this.itens = itens;
    }
}