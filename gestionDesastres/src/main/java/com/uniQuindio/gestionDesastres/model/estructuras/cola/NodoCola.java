package com.uniQuindio.gestionDesastres.model.estructuras.cola;

public class NodoCola<E> {
    private E dato;
    private NodoCola<E> siguiente;

    public NodoCola(E dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public E getDato() {
        return dato;
    }

    public void setDato(E dato) {
        this.dato = dato;
    }

    public NodoCola<E> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoCola<E> siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public String toString() {
        return "NodoCola{" +
                "dato=" + dato +
                ", siguiente=" + (siguiente != null ? "existe" : "null") +
                '}';
    }
}
