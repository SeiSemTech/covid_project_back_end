package com.app.covid.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.app.covid.domain.Departamento.DepartamentoBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "municipio")
public class Municipio {

	@Id
	@Column
	private Long id;

	@Column
	private String nombre_municipio;

	@ManyToOne
	@JoinColumn(name = "id_departamento", referencedColumnName = "id", nullable = false)
	private Departamento departamento;

}
