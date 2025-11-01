package com.uniQuindio.gestionDesastres.model.estructuras.listaDobleEnlazada;

import java.util.Iterator;

public class ListaDobleEnlazada<E extends Comparable<E>> implements Iterable<E> {
    private NodoDoble<E> primero;
    private NodoDoble<E> ultimo;
    private int tam;

    public ListaDobleEnlazada(){
        this.primero = null;
        this.ultimo = null;
        this.tam = 0;
    }

    public void agregarPrimero(E dato){
        NodoDoble<E> newNodo = new NodoDoble<>(dato);

        if (primero == null) {
            primero = newNodo;
            ultimo = newNodo;
            newNodo.setSiguiente(newNodo);
            newNodo.setAnterior(newNodo);
        } else {
            newNodo.setSiguiente(primero);
            newNodo.setAnterior(ultimo);

            primero.setAnterior(newNodo);
            ultimo.setSiguiente(newNodo);

            primero = newNodo;
        }
        tam++;
    }

    public void agregarUltimo(E dato){
        NodoDoble<E> newNodo = new NodoDoble<>(dato);

        if(this.ultimo == null){
            this.primero = newNodo;
            this.ultimo = newNodo;
        } else {
            this.ultimo.setSiguiente(newNodo);
            newNodo.setAnterior(this.ultimo);
            this.ultimo = newNodo;
        }
        this.tam++;
    }

    public void agregarEnPosicion(E dato, int posicion){
        if(posicion < 0 || posicion > this.tam){
            System.out.println("Posición inválida");
            return;
        }

        if(posicion == 0){
            agregarPrimero(dato);
        } else if(posicion == this.tam){
            agregarUltimo(dato);
        } else {
            NodoDoble<E> actual = this.primero;
            int contador = 0;

            while(contador < posicion){
                actual = actual.getSiguiente();
                contador++;
            }
            NodoDoble<E> anterior = actual.getAnterior();
            NodoDoble<E> newNodo = new NodoDoble<>(dato);

            newNodo.setAnterior(anterior);
            newNodo.setSiguiente(actual);
            anterior.setSiguiente(newNodo);
            this.tam++;
        }
    }
    public void agregarOrdenado(E dato){
        NodoDoble<E> newNodo = new NodoDoble<>(dato);

        if (primero == null) {
            primero = newNodo;
            primero.setSiguiente(primero);
            primero.setAnterior(primero);
            tam++;
            return;
        }

        NodoDoble<E> actual = primero;

        if (dato.compareTo(primero.getDato()) < 0) {
            NodoDoble<E> ultimo = primero.getAnterior();

            newNodo.setSiguiente(primero);
            newNodo.setAnterior(ultimo);
            primero.setAnterior(newNodo);
            ultimo.setSiguiente(newNodo);
            primero = newNodo;
            tam++;
            return;
        }

        while (actual.getSiguiente() != primero &&
                dato.compareTo(actual.getSiguiente().getDato()) > 0) {
            actual = actual.getSiguiente();
        }

        NodoDoble<E> siguiente = actual.getSiguiente();
        newNodo.setSiguiente(siguiente);
        newNodo.setAnterior(actual);
        actual.setSiguiente(newNodo);
        siguiente.setAnterior(newNodo);

        tam++;
    }

    public void mostrar() {
        if (primero == null) {
            System.out.println("Lista vacía");
            return;
        }
        NodoDoble<E> actual = primero;
        do {
            System.out.print(actual.getDato() + " ");
            actual = actual.getSiguiente();
        } while (actual != primero);
        System.out.println();

    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private NodoDoble<E> actual = primero;

            @Override
            public boolean hasNext() {
                return actual != null;
            }

            @Override
            public E next() {
                E dato = actual.getDato();
                actual = actual.getSiguiente();
                return dato;
            }
        };
    }
}
