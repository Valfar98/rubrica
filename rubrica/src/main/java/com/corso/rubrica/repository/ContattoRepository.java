package com.corso.rubrica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.corso.rubrica.model.Cliente;
import com.corso.rubrica.model.Contatto;

public interface ContattoRepository extends JpaRepository<Contatto, Integer> {
	List<Contatto> findByNomeLikeAndCognomeLike(String nome, String cognome);

	@Query("select c from Contatto c where c.nome like :nome")
	List<Contatto> findByNomeLike(@Param("nome") String nome);

	List<Contatto> findByCognomeLike(String cognome);

	List<Contatto> findByCliente(Cliente cliente);

	// Trova contatti per cliente e nome
	List<Contatto> findByClienteAndNomeLike(Cliente cliente, String nome);

	// Trova contatti per cliente e cognome
	List<Contatto> findByClienteAndCognomeLike(Cliente cliente, String cognome);

	// Trova contatti per cliente, nome e cognome
	List<Contatto> findByClienteAndNomeLikeAndCognomeLike(Cliente cliente, String nome, String cognome);

	// Elimina tutti i contatti di un cliente specifico
	void deleteByCliente(Cliente cliente);
}
