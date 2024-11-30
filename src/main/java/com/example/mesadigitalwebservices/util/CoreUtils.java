package com.example.mesadigitalwebservices.util;

import com.example.mesadigitalwebservices.entity.mesa.Categoria;
import com.example.mesadigitalwebservices.entity.mesa.Comanda;
import com.example.mesadigitalwebservices.entity.mesa.Pedido;
import com.example.mesadigitalwebservices.entity.mesa.Produto;
import com.example.mesadigitalwebservices.repository.user.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class CoreUtils {

    static UserRepository userRepository;

    public static boolean validadeComanda(Comanda comanda){
        if(comanda.getNumeroMesa() == null && comanda.getAtendente() == null){
            return false;
        }
        return userRepository.existsById(comanda.getAtendente().getId());
    }

    public static boolean validateProduto(Produto produto) {
        return !produto.getNome().isEmpty() && !produto.getDescricao().isEmpty() && produto.getValor().intValue() < 0;
    }

    public static boolean validadePedido(Pedido pedido) {
        return pedido.getComanda() != null;
    }
}
