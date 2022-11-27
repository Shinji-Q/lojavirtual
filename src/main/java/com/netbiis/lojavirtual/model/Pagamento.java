package com.netbiis.lojavirtual.model;
// Generated 25 de nov. de 2022 08:09:00 by Hibernate Tools 4.3.6.Final

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Pagamento generated by hbm2java
 */
@Entity
@Table(name = "pagamento", uniqueConstraints = @UniqueConstraint(columnNames = "pagamentoId"))
public class Pagamento implements java.io.Serializable {

	private PagamentoId id;
	private Cliente cliente;
	private Curso curso;
	private Date data;
	private int pagamentoId;

	public Pagamento() {
	}

	public Pagamento(PagamentoId id, Cliente cliente, Curso curso, Date data) {
		this.id = id;
		this.cliente = cliente;
		this.curso = curso;
		this.data = data;
	}
	
	public String toString() {
		return "cliente: %s, curso: %s, data: %s".formatted(this.cliente.getNome(), this.curso.getTitulo(), this.data.toString());
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "cursoId", column = @Column(name = "cursoId", nullable = false)),
			@AttributeOverride(name = "cpf", column = @Column(name = "cpf", nullable = false, length = 14)) })
	public PagamentoId getId() {
		return this.id;
	}

	public void setId(PagamentoId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cpf", nullable = false, insertable = false, updatable = false)
	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cursoId", nullable = false, insertable = false, updatable = false)
	public Curso getCurso() {
		return this.curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "data", nullable = false, length = 10)
	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Column(name = "pagamentoId", unique = true, nullable = false)
	public int getPagamentoId() {
		return this.pagamentoId;
	}

	public void setPagamentoId(int pagamentoId) {
		this.pagamentoId = pagamentoId;
	}

}
