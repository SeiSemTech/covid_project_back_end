package com.app.covid.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Entity
@Table
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Example {

  @Id
  Long id;
  @Column(nullable = false, updatable= false)
  String valueExample;
  String description;

}
