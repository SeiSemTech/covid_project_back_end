package com.app.covid.domain;

import java.util.Date;
import javax.persistence.*;

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
@Table(name = "paciente")
public class Paciente {

	@Id
	@Column
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String nombre_paciente;

	@Column
	private String apellido_paciente;

	@Column
	private String id_cedula;
	@Column
	private String tipo_documento;
	@Column
	private Date fecha_nacimiento;

	@ManyToOne
	@JoinColumn(name = "id_centro_salud", referencedColumnName = "id", nullable = false)
	private CentroSalud centroSalud;

	@ManyToOne
	@JoinColumn(name = "id_etapa", referencedColumnName = "id", nullable = false)
	private Etapa etapa;

	@ManyToOne
	@JoinColumn(name = "id_estado_paciente", referencedColumnName = "id", nullable = false)
	private EstadoPaciente estadoPaciente;

	@ManyToOne
	@JoinColumn(name = "id_ocupacion", referencedColumnName = "id", nullable = false)
	private Ocupacion ocupacion;

}
