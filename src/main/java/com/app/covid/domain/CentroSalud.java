package com.app.covid.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Entity(name = "centro_salud")
public class CentroSalud {
	@Id
	@Column
	private Long id;

	@Column
	private String nombre;

	@Column
	private String direccion;

	@Column(nullable = true)
	private Integer cantidad;

	@ManyToOne
	@JoinColumn(name = "id_lote", referencedColumnName = "id", nullable = true)
	private Lote lote;

	@ManyToOne
	@JoinColumn(name = "id_centro_poblado", referencedColumnName = "id", nullable = false)
	private CentroPoblado centroPoblado;

}
