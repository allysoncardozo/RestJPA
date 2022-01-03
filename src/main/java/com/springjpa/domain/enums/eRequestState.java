package com.springjpa.domain.enums;

public enum eRequestState {
	OPEN(0),
	IN_PROGRESS(1),
	CLOSED(2);
	
	private int valor;

	eRequestState(int _value) {
        this.valor = _value;
    }

    public int getValor() {
        return valor;
    }
}
