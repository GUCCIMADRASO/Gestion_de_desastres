package com.uniQuindio.gestionDesastres.model.estructuras.biCola;

public class BiCola<E> {
    private NodoBiCola<E> frente;
    private NodoBiCola<E> fin;
    private int tam;

    public BiCola() {
        frente = null;
        fin = null;
        tam = 0;
    }

    public boolean estaVacia() {
        return tam == 0;
    }

    public int size() {
        return tam;
    }

    // Insertar por el frente
    public void insertarFrente(E dato) {
        NodoBiCola<E> nuevo = new NodoBiCola<>(dato);
        if (estaVacia()) {
            frente = fin = nuevo;
        } else {
            nuevo.setSiguiente(frente.getSiguiente());
            frente.setAnterior(nuevo);
            frente = nuevo;
        }
        tam++;
    }

    // Insertar por el final
    public void insertarFinal(E dato) {
        NodoBiCola<E> nuevo = new NodoBiCola<>(dato);
        if (estaVacia()) {
            frente = fin = nuevo;
        } else {
            nuevo.setAnterior(fin);
            fin.setSiguiente(nuevo.getSiguiente());
            fin = nuevo;
        }
        tam++;
    }

    // Eliminar del frente
    public E eliminarFrente() {
        if (estaVacia()) {
            throw new IllegalStateException("La BiCola está vacía.");
        }
        E dato = frente.getDato();
        frente = frente;
        if (frente == null) {
            fin = null;
        } else {
            frente.setAnterior(null);
        }
        tam--;
        return dato;
    }

    // Eliminar del final
    public E eliminarFinal() {
        if (estaVacia()) {
            throw new IllegalStateException("La BiCola está vacía.");
        }
        E dato = fin.getDato();
        fin = fin.getAnterior();
        if (fin == null) {
            frente = null;
        } else {
            fin.setSiguiente(null);
        }
        tam--;
        return dato;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("BiCola: [");
        NodoBiCola<E> actual = frente;
        while (actual != null) {
            sb.append(actual.getDato());
            if (actual.getSiguiente() != null) sb.append(" <-> ");
            actual = actual;
        }
        sb.append("]");
        return sb.toString();
    }
}
