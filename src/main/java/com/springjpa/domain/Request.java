package com.springjpa.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springjpa.domain.abstratas.ABaseDomain;
import com.springjpa.domain.enums.eRequestState;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "request")
public class Request  extends ABaseDomain {
	
	private static final long serialVersionUID = 1L;

	@Column(length = 55, nullable = false)
	@NotBlank(message = "Subject é de preenchimento obrigatório")
	@NotNull(message = "Subject é de preenchimento obrigatório")
	private String subject;
	
	@Column(columnDefinition = "text")
	@NotBlank(message = "Descrição é de preenchimento obrigatório")
	@NotNull(message = "Descrição é de preenchimento obrigatório")
	private String description;
	
	@Column(name = "creation_date", nullable = false)
	private LocalDateTime creationDate;
	
	@Column(length = 12, nullable = false)
	@Enumerated(EnumType.ORDINAL)
	@NotNull
	private eRequestState state;
	
	@ManyToOne
	@JoinColumn(name = "owner_id", nullable = false)
	private Users owner;

	@Getter(onMethod = @__(@JsonIgnore))
	@OneToMany(mappedBy = "request")
	private List<RequestStage> stages = new ArrayList<RequestStage>();

}
