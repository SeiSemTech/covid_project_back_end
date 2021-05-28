package com.app.covid.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name = "centro_poblado")
public class CentroPoblado {
	@Id
	@Column
	private Long id;

	@Column
	private String nombre_barrio;

	@Column
	private String tipo_centro_poblado;

	@Column
	private String longitud;

	@Column
	private String latitud;

	@Column
	private String nombre_distrito;

	@Column
	private String anm;

	@Column
	private String nombre_area_metropolitana;

	@ManyToOne
	@JoinColumn(name = "id_municipio", referencedColumnName = "id", nullable = false)
	private Municipio municipio;

}
