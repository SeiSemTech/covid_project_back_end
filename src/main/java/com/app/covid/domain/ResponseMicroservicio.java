package com.app.covid.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.app.covid.domain.Etapa.EtapaBuilder;

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
public class ResponseMicroservicio {

	private boolean error;
	private Long x;
	private Long y;
	private String distance;
	private String id;
	private String address;

}
