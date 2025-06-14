package com.corso.rubrica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.corso.rubrica.model.Utente;

public interface UtenteRepository extends JpaRepository<Utente, Integer>{
	List<Utente> findByNomeLikeAndCognomeLike(String nome, String cognome);
	
	@Query("select u from Utente u where u.nome like :nome")
	List<Utente> findByNomeLike(@Param("nome") String nome);
	
	List<Utente> findByCognomeLike(String cognome);	

}
