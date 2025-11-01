package com.uniQuindio.gestionDesastres.model.estructuras.biCola;

public class NodoBiCola <E>{
    private NodoBiCola<E> anterior;
    private NodoBiCola<E> siguiente;
    private E dato;

    public NodoBiCola(E dato) {
        this.anterior = null;
        this.siguiente = null;
        this.dato = dato;
    }

    public NodoBiCola<E> getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoBiCola<E> anterior) {
        this.anterior = anterior;
    }

    public NodoBiCola<E> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoBiCola<E> siguiente) {
        this.siguiente = siguiente;
    }

    public E getDato() {
        return dato;
    }

    public void setDato(E dato) {
        this.dato = dato;
    }
}
