package com.springjpa.repository;

import com.springjpa.domain.Request;
import com.springjpa.domain.Users;
import com.springjpa.domain.enums.eRequestState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    @Query(value = "select * from request r where owner_id = ?1", nativeQuery = true)
	public List<Request> findAllByOwnerId(Long ownerId);

    public Page<Request> findAllByOwnerId(Long ownerId, PageRequest pageable);


    public List<Request> findAllByOwner(Users owner);

    @Query(value = "UPDATE request SET state = ?2 state WHERE id = ?1", nativeQuery = true)
	Request updateRequestState(Long id, int _state);

    List<Request> findTop2000By();




//Alguns exemplos de como se trazer dados sem necessitar digitar a query
//    Produto findByNome(String nome);
//
//    // Equivalente ao like, mas não precisamos nos preocupar com o sinal de percentual.
//    // Podemos usar também EndingWith, Containing.
//    List<Produto> findByNomeStartingWith(String nome);
//
//    // Ordenando pelo nome.
//    List<Produto> findByNomeStartingWithOrderByNome(String nome);
//
//    // Não levar em consideração a caixa.
//    List<Produto> findByNomeStartingWithIgnoreCase(String nome);
//
//    // Pesquisando por duas propriedades: nome e ativo.
//    List<Produto> findByNomeStartingWithIgnoreCaseAndAtivoEquals(String nome, boolean ativo);
//
//    // Nesse caso, precisamos usar o sinal de percentual em nossas consultas.
//    List<Produto> findByNomeLike(String nome);
//
//    // Podemos usar também IsNotNull ou NotNull.
//    List<Produto> findByDescricaoIsNull();
//
//    // Quando você quer negar o que passa no parâmetro
//    List<Produto> findByNomeNot(String nome);
//
//    // Todos os produtos com os IDs passados no varargs. Poderia usar NotIn para negar os IDs.
//    List<Produto> findByIdIn(Long... ids);
//
//    // Todos onde a propriedade ativo é true. Poderia ser falso, usando False.
//    List<Produto> findByAtivoTrue();
//
//    // Buscar onde a data de cadastro é depois do parâmetro passado.
//    // Pode ser usado Before também.
//    List<Produto> findByCadastroAfter(Date data);
//
//    // Buscar onde a data cadastro está dentro de um período.
//    List<Produto> findByCadastroBetween(Date inicio, Date fim);
//
//    // Todos com quantidade "menor que". Poderia ser usado
//    // também LessThanEqual, GreaterThan, GreaterThanEqual.
//    List<Produto> findByQuantidadeLessThan(int quantidade);

//    Recuperando por demanda
//    http://localhost:8080/produtos?ordenacao=nome&direcao=DESC
//    @GetMapping
//    public Page<Produto> pesquisar(
//            @RequestParam(defaultValue = "0") int pagina,
//            @RequestParam(defaultValue = "10") int porPagina,
//            @RequestParam(defaultValue = "nome") String ordenacao,
//            @RequestParam(defaultValue = "ASC") Sort.Direction direcao) {
//        return produtos.findAll(new PageRequest(pagina, porPagina, new Sort(direcao, ordenacao)));
//    }

}
