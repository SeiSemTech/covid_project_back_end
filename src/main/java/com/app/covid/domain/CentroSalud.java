package com.app.covid.domain;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name = "centro_salud")
public class CentroSalud {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private int cantidad;
	@XmlElement(required = true)
	@JsonProperty("depa_nombre")
	@Column
	private String depa_nombre;
	@XmlElement(required = true)
	@Column
	@JsonProperty("muni_nombre")
	private String muni_nombre;
	@XmlElement(required = true)
	@JsonProperty("sede_nombre")
	@Column
	private String sede_nombre;
	@XmlElement(required = true)
	@JsonProperty("direccion")
	@Column
	private String direccion;
	@XmlElement(required = true)
	@JsonProperty("telefono")
	@Column
	private String telefono;
	@XmlElement(required = true)
	@JsonProperty("email")
	@Column
	private String email;
	@Column
	@XmlElement(required = true)
	@JsonProperty("naju_nombre")
	private String naju_nombre;
	@XmlElement(required = true)
	@JsonProperty("fecha_corte_reps")
	@Column
	private String fecha_corte_reps;

	@ManyToOne
	@JoinColumn(name = "id_lote", referencedColumnName = "id", nullable = true)
	private Lote lote;


}
