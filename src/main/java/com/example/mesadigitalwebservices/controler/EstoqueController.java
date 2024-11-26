package com.example.mesadigitalwebservices.controler;

import com.example.mesadigitalwebservices.dto.cardapio.CategoriaDto;
import com.example.mesadigitalwebservices.dto.cardapio.ProdutoCreateDto;
import com.example.mesadigitalwebservices.dto.estoque.IngredienteDto;
import com.example.mesadigitalwebservices.entity.financeiro.Estoque;
import com.example.mesadigitalwebservices.entity.mesa.Categoria;
import com.example.mesadigitalwebservices.entity.mesa.Ingrediente;
import com.example.mesadigitalwebservices.entity.mesa.Produto;
import com.example.mesadigitalwebservices.entity.mesa.ProdutoIngrediente;
import com.example.mesadigitalwebservices.repository.financeiro.EstoqueRepository;
import com.example.mesadigitalwebservices.repository.mesa.CategoriaRepository;
import com.example.mesadigitalwebservices.repository.mesa.IngredienteRepository;
import com.example.mesadigitalwebservices.repository.mesa.ProdutoIngredienteRepository;
import com.example.mesadigitalwebservices.repository.mesa.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private EstoqueRepository estoqueRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private IngredienteRepository ingredienteRepository;
    @Autowired
    private ProdutoIngredienteRepository produtoIngredienteRepository;

    @PostMapping("/categoria/create")
    public ResponseEntity<?> createCategoria(@RequestBody CategoriaDto categoria) {
        if(categoria.nome.isEmpty() || !categoriaRepository.existsByNome(categoria.nome) || categoria.tipo.isEmpty()) {
            Categoria categoriaEntity = new Categoria();
            categoriaEntity.setNome(categoria.nome);
            categoriaEntity.setTipo(categoria.tipo);
            categoriaEntity.setDescricao(categoria.descricao);

            categoriaRepository.save(categoriaEntity);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/produto/create")
    public ResponseEntity<?> addProduct(@RequestBody ProdutoCreateDto produtoDto) {
        if(!produtoRepository.existsByNome(produtoDto.nome)
                && estoqueRepository.existsById(produtoDto.estoqueId)
                && categoriaRepository.existsById(produtoDto.categoriaId)) {
            Optional<Categoria> categoria = categoriaRepository.findById(produtoDto.categoriaId);
            Optional<Estoque> estoque = estoqueRepository.findById(produtoDto.estoqueId);
            if(categoria.isPresent() && estoque.isPresent()) {
                    Produto produtoEntity = new Produto();
                    produtoEntity.setNome(produtoDto.nome);
                    produtoEntity.setDataCadastro(new Date());
                    produtoEntity.setEstoque(estoque.get());
                    produtoEntity.setCategoria(categoria.get());
                    produtoEntity.setDescricao(produtoDto.descricao);
                    produtoEntity.setQuantidade(produtoDto.quantidade);
                    produtoEntity.setValor(produtoDto.valor);
                    for(IngredienteDto ingredienteDto : produtoDto.ingredientes) {
                        if(ingredienteRepository.existsById(ingredienteDto.id)) {
                            Optional<Ingrediente> ingrediente = ingredienteRepository.findById(ingredienteDto.id);
                            if(ingrediente.isPresent()) {
                                ProdutoIngrediente produtoIngredienteEntity = new ProdutoIngrediente();
                                produtoIngredienteEntity.setProduto(produtoEntity);
                                produtoIngredienteEntity.setIngrediente(ingrediente.get());
                                produtoIngredienteEntity.setQuantidade(ingredienteDto.quantidade);
                                produtoIngredienteRepository.save(produtoIngredienteEntity);
                            }
                        }
                    }

                    produtoRepository.save(produtoEntity);
                    return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/produto/delete")
    public ResponseEntity<?> removeProduct(@RequestParam Long produtoId) {
        if(produtoRepository.existsById(produtoId)) {
            Optional<Produto> produto = produtoRepository.findById(produtoId);
            produtoRepository.delete(produto.get());

            return ResponseEntity.ok().build();
        }
        return null;
    }

    @PutMapping("/produto/update")
    public ResponseEntity<?> updateProduct(@RequestBody ProdutoCreateDto produtoDto) {
        if(produtoRepository.existsByNome(produtoDto.nome)
                && estoqueRepository.existsById(produtoDto.estoqueId)
                && categoriaRepository.existsById(produtoDto.categoriaId)) {
            Optional<Categoria> categoria = categoriaRepository.findById(produtoDto.categoriaId);
            Optional<Estoque> estoque = estoqueRepository.findById(produtoDto.estoqueId);
            if(categoria.isPresent() && estoque.isPresent()) {
                Produto produtoEntity = produtoRepository.findById(produtoDto.id).get();
                produtoEntity.setNome(produtoDto.nome);
                produtoEntity.setDataCadastro(new Date());
                produtoEntity.setEstoque(estoque.get());
                produtoEntity.setCategoria(categoria.get());
                produtoEntity.setDescricao(produtoDto.descricao);
                produtoEntity.setQuantidade(produtoDto.quantidade);
                produtoEntity.setValor(produtoDto.valor);

                //Validação/Execução muito propícia a virar uma proc no BD kkkkkk
                for(IngredienteDto ingredienteDto : produtoDto.ingredientes) {
                   Optional<List<ProdutoIngrediente>> produtoIngredienteList = produtoIngredienteRepository.findAllByProdutoId(produtoEntity.getId());
                   for(ProdutoIngrediente produtoIngrediente : produtoIngredienteList.get()) {
                       Optional<Ingrediente> ingrediente = ingredienteRepository.findById(ingredienteDto.id);
                       if(ingrediente.isPresent()) {
                           if(!produtoIngrediente.getIngrediente().equals(ingrediente.get())) {
                               produtoIngredienteRepository.delete(produtoIngrediente);
                               ProdutoIngrediente produtoIngredienteEntity = new ProdutoIngrediente();
                               produtoIngredienteEntity.setProduto(produtoEntity);
                               produtoIngredienteEntity.setIngrediente(ingrediente.get());
                               produtoIngredienteEntity.setQuantidade(ingredienteDto.quantidade);
                               produtoIngredienteRepository.save(produtoIngredienteEntity);
                           }
                       }else{
                            return ResponseEntity.badRequest().build();
                       }
                   }
                }

                produtoRepository.save(produtoEntity);
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/ingrediente/create")
    public ResponseEntity<?> addIngredient(@RequestBody IngredienteDto ingredienteDto) {
        if(!ingredienteRepository.existsByNome(ingredienteDto.nome)){
            Ingrediente ingredienteEntity = new Ingrediente();
            ingredienteEntity.setNome(ingredienteDto.nome);
            ingredienteEntity.setDescricao(ingredienteDto.descricao);
            ingredienteEntity.setQuantidade(ingredienteDto.quantidade);
            ingredienteEntity.setUnidade(ingredienteDto.unidade);

            ingredienteRepository.save(ingredienteEntity);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/ingrediente/delete")
    public ResponseEntity<?> deleteIngredient(@RequestParam Long ingredienteId) {
        if(ingredienteRepository.existsById(ingredienteId)) {
            Optional<Ingrediente> ingrediente = ingredienteRepository.findById(ingredienteId);
            ingredienteRepository.delete(ingrediente.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/ingrediente/update")
    public ResponseEntity<?> updateIngredient(@RequestBody IngredienteDto ingredienteDto) {
        if(ingredienteRepository.existsByNome(ingredienteDto.nome)){
            Optional<Ingrediente> ingrediente = ingredienteRepository.findById(ingredienteDto.id);
            if(ingrediente.isPresent()) {
                ingrediente.get().setNome(ingredienteDto.nome);
                ingrediente.get().setDescricao(ingredienteDto.descricao);
                ingrediente.get().setQuantidade(ingredienteDto.quantidade);
                ingrediente.get().setUnidade(ingredienteDto.unidade);
                ingredienteRepository.save(ingrediente.get());

                return ResponseEntity.ok().build();
            }
        }
        return null;
    }

    @GetMapping("/produto/get")
    public ResponseEntity<?> getProduct(@RequestParam Long produtoId) {
        if(produtoRepository.existsById(produtoId)) {
            Optional<Produto> produto = produtoRepository.findById(produtoId);
            if(produto.isPresent()) {
                return ResponseEntity.ok().body(produto.get());
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/ingrediente/get")
    public ResponseEntity<?> getIngredient(@RequestParam Long ingredienteId) {
        if(ingredienteRepository.existsById(ingredienteId)) {
            Optional<Ingrediente> ingrediente = ingredienteRepository.findById(ingredienteId);
            if(ingrediente.isPresent()) {
                return ResponseEntity.ok().body(ingrediente.get());
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/ingrediente/get-all")
    public ResponseEntity<?> getIngredientList(){
        return ResponseEntity.ok().body(ingredienteRepository.findAll());
    }

    @GetMapping("/produto/get-all")
    public ResponseEntity<?> getProductList(){
        return ResponseEntity.ok().body(produtoRepository.findAll());
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok().body(produtoIngredienteRepository.findAll());
    }
}
