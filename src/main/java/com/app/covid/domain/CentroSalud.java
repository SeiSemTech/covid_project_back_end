package com.app.covid.domain;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
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
@Entity
@JsonIgnoreProperties( ignoreUnknown = true )
public class CentroSalud  {

    @JsonProperty( "depa_nombre" )
	private String depa_nombre;
	@XmlElement( required = true )
    @JsonProperty( "muni_nombre" )
	private String muni_nombre;
	@XmlElement( required = true )
    @JsonProperty( "sede_nombre" )
	private String sede_nombre;
	@XmlElement( required = true )
    @JsonProperty( "direccion" )
	private String direccion;
	@XmlElement( required = true )
    @JsonProperty( "telefono" )
	private String telefono;
	@XmlElement( required = true )
    @JsonProperty( "email" )
	private String email;
	@XmlElement( required = true )
    @JsonProperty( "naju_nombre" )
	private String naju_nombre;
	@XmlElement( required = true )
    @JsonProperty( "fecha_corte_reps" )
	private String fecha_corte_reps;
	

}
