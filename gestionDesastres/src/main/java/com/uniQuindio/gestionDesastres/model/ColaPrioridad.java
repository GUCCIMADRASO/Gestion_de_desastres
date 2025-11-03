package com.uniQuindio.gestionDesastres.model;

import java.util.PriorityQueue;

public class ColaPrioridad<T extends Comparable<T>> {
    private PriorityQueue<T> colaPrioridad;

    public ColaPrioridad() {
        colaPrioridad = new PriorityQueue<>();
    }

    // Agregar un elemento a la cola
    public void agregarElemento(T elemento) {
        colaPrioridad.offer(elemento);
        System.out.println("Elemento agregado a la cola: " + elemento);
    }

    // Atender  el elemento con mayor prioridad
    public T atenderSiguiente() {
        T siguiente = colaPrioridad.poll();
        if (siguiente != null) {
            System.out.println("Atendiendo elemento: " + siguiente);
        } else {
            System.out.println("No hay elementos en la cola.");
        }
        return siguiente;
    }

    // Mostrar la cola actual
    public void mostrarCola() {
        System.out.println("Cola de prioridad actual:");
        if (colaPrioridad.isEmpty()) {
            System.out.println("Vacía");
        } else {
            for (T elemento : colaPrioridad) {
                System.out.println("- " + elemento);
            }
        }
    }

    // Saber si la cola está vacía
    public boolean estaVacia() {
        return colaPrioridad.isEmpty();
    }

    // Obtener el tamaño de la cola
    public int tam() {
        return colaPrioridad.size();
    }

}
