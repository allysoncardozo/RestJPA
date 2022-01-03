package com.springjpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.springjpa.domain.abstratas.ABaseDomain;
import com.springjpa.domain.enums.eRole;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity(name = "users")
public class Users extends ABaseDomain {

	private static final long serialVersionUID = 1L;

	@Column(length = 100, nullable = false)
	private String name;

	@Column(length = 200, nullable = false, unique = true)
	@Email
	private String email;

	@Getter(onMethod = @__(@JsonIgnore))
	@Setter(onMethod = @__(@JsonProperty))
	@Column(length = 100, nullable = false)
	@Size(min = 7, max = 99, message = "Senha tamanho minimo de 7 e maximo de 99 caracteres")
	private String password;

	@Column(length = 20, nullable = false)
	@Enumerated(EnumType.ORDINAL)
	@NotNull(message = "Role não pode ser nulo")
	private eRole role;

	@Getter(onMethod = @__(@JsonIgnore))
	@OneToMany(mappedBy = "owner")
	private List<Request> requests = new ArrayList<Request>();

	@Getter(onMethod = @__(@JsonIgnore))
	@OneToMany(mappedBy = "owner")
	private List<RequestStage> stages = new ArrayList<RequestStage>();
}
