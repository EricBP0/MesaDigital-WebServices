package com.example.mesadigitalwebservices.controler;

import com.example.mesadigitalwebservices.dto.cardapio.ResponseCardapioDto;
import com.example.mesadigitalwebservices.entity.mesa.Categoria;
import com.example.mesadigitalwebservices.entity.mesa.CategoriaTipo;
import com.example.mesadigitalwebservices.entity.mesa.Produto;
import com.example.mesadigitalwebservices.repository.mesa.CategoriaRepository;
import com.example.mesadigitalwebservices.repository.mesa.ProdutoRepository;
import com.example.mesadigitalwebservices.util.CoreUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/cardapio")
public class CardapioController {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping("/categoria/create")
    public ResponseEntity<?> createCategory(@RequestBody Categoria categoria) {
        if(CoreUtils.validateCategoria(categoria)){
            if(categoriaRepository.findByNome(categoria.getNome()).isPresent()){
                categoriaRepository.save(categoria);
            }else{
                return ResponseEntity.unprocessableEntity().body("Categoria já criada");
            }
            return ResponseEntity.ok("Categoria cadastrada com sucesso!");
        }
        return ResponseEntity.badRequest().body("Categoria com nome ou descrição vazias!");
    }

    @GetMapping("/categoria/todas")
    public ResponseEntity<List<Categoria>> findAll() {
        List<Categoria> categorias = categoriaRepository.findAll();
        if(categorias.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(categorias);
    }

    @PutMapping("/categoria/edit")
    public ResponseEntity<?> updateCategory(@RequestBody Categoria categoria) {
        if(CoreUtils.validateCategoria(categoria)){
            if(categoriaRepository.findByNome(categoria.getNome()).isPresent()){
                categoriaRepository.save(categoria);
                return ResponseEntity.ok("Categoria atualizada com sucesso!");
            }else{
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.badRequest().body("Categoria com nome ou descrição vazias!");
    }

    @PostMapping("/categoria/delete")
    public ResponseEntity<?> deleteCategory(@RequestBody Categoria categoria) {
        if(CoreUtils.validateCategoria(categoria)){
            if(categoriaRepository.findByNome(categoria.getNome()).isPresent()){
                categoriaRepository.delete(categoria);
                return ResponseEntity.ok("Categoria deletada com sucesso!");
            }else{
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.badRequest().body("Categoria com nome ou descrição vazias!");
    }

    @PostMapping("/produto/create")
    public ResponseEntity<?> createProduct(@RequestBody Produto produto) {
        if(CoreUtils.validateProduto(produto)){
            produtoRepository.save(produto);
            return ResponseEntity.ok("Produto criado com sucesso!");
        }else{
            return ResponseEntity.badRequest().body("Produto vazio!");
        }
    }

    @GetMapping("/produto/todos")
    public ResponseEntity<List<Produto>> findAllProdutos(){
        List<Produto> produtos = produtoRepository.findAll();
        if(produtos.isEmpty()){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(produtos);
        }
    }

    @PutMapping("/produto/edit")
    public ResponseEntity<?> updateProduct(@RequestBody Produto produto) {
        if(CoreUtils.validateProduto(produto)){
            if(produtoRepository.findById(produto.getId()).isPresent()){
                produtoRepository.save(produto);
                return ResponseEntity.ok("Produto modificado com sucesso!");
            }else{
                return ResponseEntity.notFound().build();
            }
        }else {
            return ResponseEntity.badRequest().body("Produto inválido!");
        }
    }

    @PostMapping("/produto/delete")
    public ResponseEntity<?> deleteProduct(@RequestBody Produto produto) {
        if(CoreUtils.validateProduto(produto)){
            if(produtoRepository.findById(produto.getId()).isPresent()){
                produtoRepository.delete(produto);
                return ResponseEntity.ok("Produto removido com sucesso!");
            }else {
                return ResponseEntity.notFound().build();
            }
        }else{
            return ResponseEntity.badRequest().body("Produto inválido!");
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getCardapio() {
        ResponseCardapioDto response = new ResponseCardapioDto();
        List<Produto> produtos = produtoRepository.findAllByCategoriaTipo(CategoriaTipo.COMIDA.toString());
        if (produtos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }else{
            response.produtoList = produtos;
        }
        return ResponseEntity.ok(response);
    }
}
