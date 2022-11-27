package com.netbiis.lojavirtual.api;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.netbiis.lojavirtual.model.*;

public class LojaVirtualDB {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPANovissimo");
	private static EntityManager em = emf.createEntityManager();
	private static EntityTransaction tx = em.getTransaction();
	
	
	// Operações cliente Clientes
	/******************************************************************************/
	public static List<Cliente> consultarTodosClientes(){
		TypedQuery<Cliente> clienteQuery = em.createQuery("from Cliente", Cliente.class);
		List<Cliente> clientes = clienteQuery.getResultList();
		return clientes;
	}
	
	public static Cliente consultarCliente(String cpf) {
		Cliente cli;
		cli = em.find(Cliente.class, cpf);
		return cli;
	}
	
	public static Cliente criarCliente(Cliente novoCliente) {
		tx.begin();
		em.persist(novoCliente);
		tx.commit();
		novoCliente = consultarCliente(novoCliente.getCpf());
		return novoCliente;
	}
	
	public static void alterarCliente(Cliente cli) {
		tx.begin();
		em.merge(cli);
		tx.commit();
	}
	
	public static void excluirCliente(Cliente cli) {
		tx.begin();
		em.remove(cli);
		tx.commit();
	}

	/******************************************************************************/
	// Operações Cursos
	/******************************************************************************/

	public static List<Curso> consultarTodosCursos(){
		TypedQuery<Curso> cursoQuery = em.createQuery("from Curso", Curso.class);
		List<Curso> cursos = cursoQuery.getResultList();
		return cursos;
	}
	
	public static Curso consultarCurso(int cursoId) {
		Curso curso;
		curso = em.find(Curso.class, cursoId);
		return curso;
	}
	
	public static Curso consultarCursoByTitulo(String titulo) {
		TypedQuery<Curso> clienteQuery = em.createQuery("from Curso where titulo='" + titulo + "'", Curso.class);
		List<Curso> cursos = clienteQuery.getResultList();

		return cursos.get(0);
	}
	
	public static Curso criarCurso(Curso novoCurso) {
		tx.begin();
		em.persist(novoCurso);
		tx.commit();
		novoCurso = consultarCursoByTitulo(novoCurso.getTitulo());
		return novoCurso;
	}
	
	public static void alterarCurso(Curso curso) {
		tx.begin();
		em.merge(curso);
		tx.commit();
	}
	
	public static void excluirCurso(Curso curso) {
		tx.begin();
		em.remove(curso);
		tx.commit();
	}

	/******************************************************************************/
	// Operações Pagamentos
	/******************************************************************************/

	public static List<Pagamento> consultarTodosPagamentos(){
		TypedQuery<Pagamento> clienteQuery = em.createQuery("from Pagamento", Pagamento.class);
		List<Pagamento> clientes = clienteQuery.getResultList();
		return clientes;
	}
	
	public static Pagamento consultarPagamento(PagamentoId id) {
		Pagamento pagamento;
		pagamento = em.find(Pagamento.class, id);
		return pagamento;
	}
	
	public static Pagamento criarPagamento(Pagamento novoPagamento) {
		tx.begin();
		em.persist(novoPagamento);
		tx.commit();
		novoPagamento = consultarPagamento(novoPagamento.getId());
		return novoPagamento;
	}
	
	public static void alterarPagamento(Pagamento pagamento) {
		tx.begin();
		em.merge(pagamento);
		tx.commit();
	}
	
	public static void excluirPagamento(Pagamento pagamento) {
		tx.begin();
		em.remove(pagamento);
		tx.commit();
	}
	/******************************************************************************/
}
