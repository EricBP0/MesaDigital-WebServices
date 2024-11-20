package com.example.mesadigitalwebservices.controler;

import com.example.mesadigitalwebservices.dto.Comanda.*;
import com.example.mesadigitalwebservices.entity.financeiro.Estoque;
import com.example.mesadigitalwebservices.entity.mesa.*;
import com.example.mesadigitalwebservices.entity.user.User;
import com.example.mesadigitalwebservices.repository.financeiro.EstoqueRepository;
import com.example.mesadigitalwebservices.repository.mesa.*;
import com.example.mesadigitalwebservices.repository.user.UserRepository;
import com.example.mesadigitalwebservices.util.CoreUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/mesa")
public class MesaController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ComandaRepository comandaRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PedidoProdutoRepository pedidoProdutoRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private EstoqueRepository estoqueRepository;

    @PostMapping("/comanda/create")
    public ResponseEntity<?> createComanda(@RequestBody ComandaDto comanda) {
        if(comandaRepository.existsById(comanda.numComanda)){
            return ResponseEntity.badRequest().body("Comanda already exists");
        }else{
            Comanda comandaEntity = new Comanda();
            Optional<User> user = userRepository.findById(comanda.funcionarioId);
            if(user.isPresent()){
                comandaEntity.setDataDeEntrada(new Date());
                comandaEntity.setId(comanda.numComanda);
                comandaEntity.setAtendente(user.get());
                comandaEntity.setNumeroMesa(comanda.numMesa);
                comandaEntity.setStatus("ABERTA");
                comandaRepository.save(comandaEntity);
                return ResponseEntity.ok().build();
            }
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
    @GetMapping("/comanda/get-all")
    public ResponseEntity<?> getAllComanda() {
        List<Comanda> comandaList = comandaRepository.findAll();
        return ResponseEntity.ok().body(comandaList);
    }
    @GetMapping("/comanda/get")
    public ResponseEntity<?> getComanda(@RequestParam Long id) {
        Optional<Comanda> comanda = comandaRepository.findById(id);
        if(comanda.isPresent()){
            return ResponseEntity.ok().body(comanda.get());
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/comanda/pedido/create")
    public ResponseEntity<?> createNewPedido(@RequestBody RequestNewPedidoDto novoPedido) {
        if(novoPedido.categoriaId != null){
            Optional<Categoria> categoria = categoriaRepository.findById(novoPedido.categoriaId);
            Optional<Comanda> comanda = comandaRepository.findById(novoPedido.comandaId);
            if(categoria.isPresent() && comanda.isPresent()){

                Pedido pedido = new Pedido();
                pedido.setComanda(comanda.get());
                pedido.setStatus("LANCADO");
                pedido.setDataCriacao(new Date());
                pedido.setDescricao(novoPedido.observacao);
                pedidoRepository.save(pedido);

                for(ProdutoDto produtoDto : novoPedido.produtos){
                    Optional<Produto> produto = produtoRepository.findById(produtoDto.produtoId);
                    if(produto.isPresent()){
                        for(int i =0 ; i<=produtoDto.quantidade; i++ ){
                            PedidoProduto pedidoProduto = new PedidoProduto();
                            pedidoProduto.setPedido(pedido);
                            pedidoProduto.setProduto(produto.get());
                            pedidoProdutoRepository.save(pedidoProduto);
                        }
                    }else{
                        return ResponseEntity.badRequest().build();
                    }
                }
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.badRequest().build();
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

    @GetMapping("/comanda/pedidos")
    public ResponseEntity<?> getAllPedidosByComanda(@RequestParam Long comandaId) {
        ComandaPedidos comandaPedidos = new ComandaPedidos();
        comandaPedidos.pedidos = new ArrayList<>();
        List<Pedido> pedidoList = pedidoRepository.findAllByComandaId(comandaId);
        for(Pedido pedido : pedidoList){
            List<PedidoProduto> p = pedidoProdutoRepository.findPedidoProdutoByPedido(pedido);
            List<Produto> produtoList = new ArrayList<>();
            for(PedidoProduto pedidoProduto : p){
                produtoList.add(pedidoProduto.getProduto());
            }
            PedidoDto pedidoDto = new PedidoDto();
            pedidoDto.produtos = new ArrayList<>();
            pedidoDto.pedido = pedido;
            pedidoDto.produtos.addAll(produtoList);

            comandaPedidos.pedidos.add(pedidoDto);
        }
        comandaPedidos.idComanda = comandaId;

        return ResponseEntity.ok().body(comandaPedidos);
    }

    @PutMapping("/comanda/fechar")
    public ResponseEntity<?> fecharComanda(@RequestParam Long comandaId) {
        Optional<Comanda> comanda = comandaRepository.findById(comandaId);
        if(comanda.isPresent()){
            if(comanda.get().getStatus().equals("ABERTA")){
                comanda.get().setStatus("FECHADA");
                comandaRepository.save(comanda.get());
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.badRequest().build();
    }


    @GetMapping("/estoque/get-all")
    public ResponseEntity<?> getAllEstoque() {
        List<Estoque> estoqueList = estoqueRepository.findAll();
        return ResponseEntity.ok().body(estoqueList);
    }

    @GetMapping("/produtos/get-all")
    public ResponseEntity<?> getAllProdutos() {
        List<Produto> produtoList = produtoRepository.findAll();
        return ResponseEntity.ok().body(produtoList);
    }

    @PostMapping("/estoque/create")
    public ResponseEntity<?> createEstoque(@RequestParam String nomeEstoque) {
        Estoque estoque = new Estoque();
        estoque.setNome(nomeEstoque);
        estoqueRepository.save(estoque);
        return ResponseEntity.ok().build();
    }
}
