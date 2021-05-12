package com.app.covid.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Collection;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lote")
public class Lote {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private int numero;

	@Column
	private int cantidad;

	@Column
	private double costo;

	@Column
	private Date fecha;

	@ManyToOne
	@JoinColumn(name="id_laboratorio", referencedColumnName = "id", nullable = false)
	private Laboratorio laboratorio;

	@ManyToOne
	@JoinColumn(name="id_estado", referencedColumnName = "id", nullable = false)
	private Estado_lote estado;


}
