package com.uniQuindio.gestionDesastres.model.estructuras.listaSimpleEnlazada;

import java.util.Iterator;

public class ListaSimpleEnlazada<E> implements Iterable<E>{
    private NodoSimple<E> primero;
    private int tam;

    public ListaSimpleEnlazada(){
        this.primero = null;
        this.tam = 0;
    }

    public void agregarOrdenado(E dato) {
        NodoSimple<E> newNodo = new NodoSimple<>(dato);

        if (primero == null || ((Comparable<E>)dato).compareTo(primero.getDato()) < 0) {
            agregarPrimero(dato);
            return;
        }

        NodoSimple<E> actual = primero;
        while (actual.getSiguiente() != null &&
                ((Comparable<E>)dato).compareTo(actual.getSiguiente().getDato()) > 0) {
            actual = actual.getSiguiente();
        }

        newNodo.setSiguiente(actual.getSiguiente());
        actual.setSiguiente(newNodo);
        tam++;
    }


    public void agregarPrimero(E dato){
        NodoSimple<E> newNodo = new NodoSimple<>(dato);

        if(this.primero == null){
            this.primero = newNodo;
            this.tam++;
        }
        else{
            newNodo.setSiguiente(this.primero);
            this.primero = newNodo;
            this.tam++;
        }
    }

    public void agregarUltimo(E dato){
        NodoSimple<E> newNodo = new NodoSimple<>(dato);

        if(this.primero == null){
            this.primero = newNodo;
            this.tam++;
        }
        else{
            NodoSimple<E> actual = this.primero;

            while(actual.getSiguiente() != null){
                actual = actual.getSiguiente();
            }

            actual.setSiguiente(newNodo);
            this.tam++;
        }
    }

    public void agregarEnPosicion(E dato, int posicion){
        if(posicion < 0 || posicion > this.tam){
            System.out.println("Posición inválida");
            return;
        }

        NodoSimple<E> newNodo = new NodoSimple<>(dato);

        if(posicion == 0){
            agregarPrimero(dato);
        }
        else{
            NodoSimple<E> actual = this.primero;
            int contador = 0;

            while(contador < posicion - 1){
                actual = actual.getSiguiente();
                contador++;
            }

            newNodo.setSiguiente(actual.getSiguiente());
            actual.setSiguiente(newNodo);
            this.tam++;
        }
    }

    public void mostrar(){
        NodoSimple<E> actual = this.primero;
        String mensaje = "{ ";

        while(actual != null){
            mensaje += actual.getDato() + " ";
            actual = actual.getSiguiente();
        }

        System.out.println(mensaje + "}");
    }

    public void listaVacia(){
        NodoSimple<E> newNodo = new NodoSimple<>(null);
    }

    public boolean esVacia(){
        if(this.primero == null){
            return true;
        }
        else{
            return false;
        }
    }

    public int localizar(E dato){
        NodoSimple<E> actual = this.primero;
        int posicion = 0;

        while(actual != null){
            if(actual.getDato().equals(dato)){
                return posicion;
            }
            actual = actual.getSiguiente();
            posicion++;
        }

        return -1;
    }

    public void suprimir(E dato){
        if(esVacia()){
            System.out.println("La lista está vacía");
            return;
        }

        if(this.primero.getDato().equals(dato)){
            this.primero = this.primero.getSiguiente();
            this.tam--;
            return;
        }

        NodoSimple<E> actual = this.primero;
        NodoSimple<E> anterior = null;

        while(actual != null && !actual.getDato().equals(dato)){
            anterior = actual;
            actual = actual.getSiguiente();
        }

        if(actual == null){
            System.out.println("El dato no se encuentra en la lista");
            return;
        }

        anterior.setSiguiente(actual.getSiguiente());
        this.tam--;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private NodoSimple<E> actual = primero;

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
