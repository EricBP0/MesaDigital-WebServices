package com.example.mesadigitalwebservices.service;

import com.example.mesadigitalwebservices.dto.ProdutoIngredienteDto;
import com.example.mesadigitalwebservices.entity.mesa.Ingrediente;
import com.example.mesadigitalwebservices.entity.mesa.Produto;
import com.example.mesadigitalwebservices.entity.mesa.ProdutoIngrediente;
import com.example.mesadigitalwebservices.repository.mesa.IngredienteRepository;
import com.example.mesadigitalwebservices.repository.mesa.ProdutoIngredienteRepository;
import com.example.mesadigitalwebservices.repository.mesa.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoIngredienteService {

    @Autowired
    private ProdutoIngredienteRepository produtoIngredienteRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Transactional(rollbackOn = Exception.class)
    public boolean removeProdutoIngrediente(ProdutoIngredienteDto produtoIngredienteDto) throws Exception {
        if(produtoRepository.existsById(produtoIngredienteDto.idProduto)){
            Optional<Produto> produto = produtoRepository.findById(produtoIngredienteDto.idProduto);
            Optional<List<ProdutoIngrediente>> produtoIngredientes = produtoIngredienteRepository.findAllByProdutoId(produtoIngredienteDto.idProduto);
            if(produto.isPresent() && produtoIngredientes.isPresent()){
                if(produto.get().getQuantidade() > 0) {
                    produto.get().setQuantidade(produto.get().getQuantidade() - 1);
                    for (ProdutoIngrediente produtoIngrediente : produtoIngredientes.get()) {
                        Optional<Ingrediente> ingrediente = ingredienteRepository.findById(produtoIngrediente.getIngrediente().getId());
                        if (ingrediente.isPresent()) {
                            if (ingrediente.get().getQuantidade() > 0) {
                                ingrediente.get().setQuantidade(ingrediente.get().getQuantidade() - 1);
                                ingredienteRepository.save(ingrediente.get());
                            } else {
                                throw new Exception("Sem quantidade para realizar o pedido!");
                            }
                        }
                    }
                    produtoRepository.save(produto.get());
                    return true;
                }else{
                    throw new Exception("Sem quantidade para realizar o pedido!");
                }
            }
        }
        return false;
    }
}
