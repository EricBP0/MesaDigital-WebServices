package com.example.mesadigitalwebservices.controler;

import com.example.mesadigitalwebservices.dto.cardapio.CategoriaDto;
import com.example.mesadigitalwebservices.dto.cardapio.ProdutoCreateDto;
import com.example.mesadigitalwebservices.dto.cardapio.ResponseCardapioDto;
import com.example.mesadigitalwebservices.entity.mesa.Categoria;
import com.example.mesadigitalwebservices.entity.mesa.CategoriaTipo;
import com.example.mesadigitalwebservices.entity.mesa.Produto;
import com.example.mesadigitalwebservices.entity.mesa.ProdutoIngrediente;
import com.example.mesadigitalwebservices.repository.financeiro.EstoqueRepository;
import com.example.mesadigitalwebservices.repository.mesa.CategoriaRepository;
import com.example.mesadigitalwebservices.repository.mesa.ProdutoIngredienteRepository;
import com.example.mesadigitalwebservices.repository.mesa.ProdutoRepository;
import com.example.mesadigitalwebservices.util.CoreUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/cardapio")
public class CardapioController {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private EstoqueRepository estoqueRepository;
    @Autowired
    private ProdutoIngredienteRepository produtoIngredienteRepository;

    @PostMapping("/categoria/create")
    public ResponseEntity<?> categoriaCreate(@RequestBody CategoriaDto categoriaDto) {
        if(!categoriaRepository.existsByNome(categoriaDto.nome)){
            Categoria categoria = new Categoria();
            categoria.setNome(categoriaDto.nome);
            categoria.setDescricao(categoriaDto.descricao);
            categoria.setTipo(categoriaDto.tipo);
            categoriaRepository.save(categoria);

            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/categoria/get-all")
    public ResponseEntity<?> getAllCategoria() {
        List<Categoria> categoriaList = categoriaRepository.findAll();
        return ResponseEntity.ok().body(categoriaList);
    }

    @PutMapping("/categoria/edit")
    public ResponseEntity<?> updateCategory(@RequestBody Categoria categoria) {
        if(categoriaRepository.findByNome(categoria.getNome()).isPresent()){
            categoriaRepository.save(categoria);
            return ResponseEntity.ok("Categoria atualizada com sucesso!");
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/categoria/delete")
    public ResponseEntity<?> deleteCategory(@RequestBody Long categoriaId) {
        Optional<Categoria> categoria = categoriaRepository.findById(categoriaId);
        if(categoria.isPresent()){
            List<Produto> produtos = produtoRepository.findAllByCategoria(categoria.get());
            for(Produto produto : produtos){
                Optional<List<ProdutoIngrediente>> produtoIngredientes = produtoIngredienteRepository.findAllByProdutoId(produto.getId());
                produtoIngredientes.ifPresent(ingredientes -> produtoIngredienteRepository.deleteAll(ingredientes));
            }
            produtoRepository.deleteAll(produtos);
            categoriaRepository.delete(categoria.get());
            return ResponseEntity.ok("Categoria deletada com sucesso!");
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/produto/create")
    public ResponseEntity<?> createProduct(@RequestBody ProdutoCreateDto produto) {
        if(!produtoRepository.existsByNome(produto.nome)){
            Produto p = new Produto();
            p.setNome(produto.nome);
            p.setDescricao(produto.descricao);
            p.setCategoria(categoriaRepository.findById(produto.categoriaId).get());
            p.setEstoque(estoqueRepository.findById(produto.estoqueId).get());
            p.setValor(produto.valor);
            p.setQuantidade(produto.quantidade);
            p.setDataCadastro(new Date());

            produtoRepository.save(p);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body("Produto ja existe!");
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
        response.produtoList = new ArrayList<>();
        List<Produto> produtos = produtoRepository.findAllByCategoriaTipo(CategoriaTipo.COMIDA.toString());
        if (produtos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }else{
            for(Produto produto : produtos){
                if(produto.getDataExclusao() == null){
                    response.produtoList.add(produto);
                }
            }
        }
        return ResponseEntity.ok(response);
    }
}
