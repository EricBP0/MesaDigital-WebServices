package com.example.mesadigitalwebservices.controler;

import com.example.mesadigitalwebservices.dto.Comanda.RequestNewPedidoDto;
import com.example.mesadigitalwebservices.entity.mesa.Comanda;
import com.example.mesadigitalwebservices.entity.mesa.Pedido;
import com.example.mesadigitalwebservices.repository.mesa.ComandaRepository;
import com.example.mesadigitalwebservices.repository.mesa.PedidoRepository;
import com.example.mesadigitalwebservices.repository.user.UserRepository;
import com.example.mesadigitalwebservices.util.CoreUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController()
@RequestMapping("/mesa")
public class MesaController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ComandaRepository comandaRepository;
    @Autowired
    private PedidoRepository pedidoRepository;

    @PostMapping("/comanda/create")
    public ResponseEntity<?> createComanda(@RequestBody Comanda comanda) {
        if(CoreUtils.validadeComanda(comanda)){
            comanda.setDataDeEntrada(new Date());
            comandaRepository.save(comanda);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/comanda/edit")
    public ResponseEntity<?> updateComanda(@RequestBody Comanda comanda) {
        if(CoreUtils.validadeComanda(comanda)){
            comandaRepository.save(comanda);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/comanda/delete")
    public ResponseEntity<?> deleteComanda(@RequestBody Comanda comanda) {
        if(CoreUtils.validadeComanda(comanda)){
            comandaRepository.delete(comanda);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/comanda/pedido/create")
    public ResponseEntity<?> createNewPedido(@RequestBody RequestNewPedidoDto novoPedido) {
        if(CoreUtils.validadeComanda(novoPedido.comanda)){
            if(novoPedido.comanda != novoPedido.pedido.getComanda()){
                novoPedido.pedido.setComanda(novoPedido.comanda);
            }
            pedidoRepository.save(novoPedido.pedido);
            if(!comandaRepository.existsById(novoPedido.comanda.getId())){
                comandaRepository.save(novoPedido.comanda);
            }
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/comanda/pedido/edit")
    public ResponseEntity<?> updatePedido(@RequestBody Pedido pedido) {
        if(CoreUtils.validadePedido(pedido)){
            pedidoRepository.save(pedido);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/comanda/pedido/delete")
    public ResponseEntity<?> deletePedido(@RequestBody Pedido pedido) {
        if(CoreUtils.validadePedido(pedido)){
            pedidoRepository.delete(pedido);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
