package com.springjpa.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.springjpa.domain.abstratas.ABaseDomain;
import com.springjpa.domain.enums.eRequestState;

import lombok.*;
import org.aspectj.bridge.Message;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "request_stage")
public class RequestStage extends ABaseDomain {
	
	private static final long serialVersionUID = 1L;

	@Column(columnDefinition = "text")
	@NotBlank(message =  "Descrição deve ser preenchida")
	@NotNull(message =  "Descrição obrigatória")
	private String description;

	@Column(name = "date", nullable = false)
	private LocalDateTime date;

	@Column(length = 12, nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private eRequestState state;

	@ManyToOne
	@JoinColumn(name = "request_id", nullable = false)
	@NotNull(message = "Request obrigatório")
	private Request request;

	@ManyToOne
	@JoinColumn(name = "owner_id", nullable = false)
	@NotNull(message = "Owner Obrigatório")
	private Users owner;

}
