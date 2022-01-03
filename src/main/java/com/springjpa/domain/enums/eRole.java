package com.springjpa.domain.enums;

public enum eRole {
	ADMINISTRATOR(0),
	SIMPLE(1);	

	private int valor;

	eRole(int _value) {
        this.valor = _value;
    }

    public int getValor() {
        return valor;
    }
}