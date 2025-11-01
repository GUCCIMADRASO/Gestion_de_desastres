package com.uniQuindio.gestionDesastres.model.estructuras.pila;

public class NodoPila<E> {
    private E dato;
    private NodoPila<E> siguiente;

    public NodoPila(E dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public E getDato() {
        return dato;
    }

    public void setDato(E dato) {
        this.dato = dato;
    }

    public NodoPila<E> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoPila<E> siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public String toString() {
        return "NodoPila{" +
                "dato=" + dato +
                '}';
    }
}
