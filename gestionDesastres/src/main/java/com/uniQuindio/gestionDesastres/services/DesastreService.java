// java
package com.uniQuindio.gestionDesastres.services;

import com.uniQuindio.gestionDesastres.model.ColaPrioridad;
import com.uniQuindio.gestionDesastres.model.Desastre;
import com.uniQuindio.gestionDesastres.model.Equipo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DesastreService {

    private final ColaPrioridadService colaPrioridadService;
    // almacenamiento en memoria simple; sustituir por repositorio si se añade persistencia
    private final List<Desastre> desastres = new ArrayList<>();

    public DesastreService(ColaPrioridadService colaPrioridadService) {
        this.colaPrioridadService = colaPrioridadService;
    }

    // Guardar o actualizar un desastre en la lista en memoria
    public Desastre guardarDesastre(Desastre desastre) {
        // si existe por id, reemplaza; si no, lo agrega
        if (desastre.getIdDesastre() != null) {
            desastres.removeIf(d -> desastre.getIdDesastre().equals(d.getIdDesastre()));
        }
        desastres.add(desastre);
        return desastre;
    }

    // Obtener todos los desastres
    public List<Desastre> listarDesastres() {
        return new ArrayList<>(desastres);
    }

    // Buscar por id sencillo
    public Desastre obtenerPorId(String id) {
        return desastres.stream()
                .filter(d -> id != null && id.equals(d.getIdDesastre()))
                .findFirst()
                .orElse(null);
    }

    // Encolar un desastre en la cola de prioridad gestionada por ColaPrioridadService
    public void encolarDesastre(Desastre desastre) {
        colaPrioridadService.agregarElemento(desastre);
    }

    // Atender siguiente desastre desde la cola de prioridad
    public Desastre atenderSiguiente() {
        return colaPrioridadService.atenderSiguiente();
    }

    // Asignar un equipo a un desastre (usa la lógica del modelo Desastre)
    public void asignarEquipo(Desastre desastre, Equipo equipo) {
        if (desastre == null || equipo == null) return;
        desastre.asignarEquipo(equipo);
    }

    // Mostrar equipos asignados de un desastre (delegado al modelo; devuelve la lista para tests/consumo)
    public List<Equipo> obtenerEquiposAsignados(Desastre desastre) {
        if (desastre == null) return new ArrayList<>();
        return new ArrayList<>(desastre.getEquiposAsignados());
    }

    // Exponer la cola interna si se necesita (p. ej. para pruebas)
    public ColaPrioridad<Desastre> obtenerColaInterna() {
        return colaPrioridadService.obtenerColaInterna();
    }

    // Eliminar desastre por id
    public boolean eliminarPorId(String id) {
        return desastres.removeIf(d -> id != null && id.equals(d.getIdDesastre()));
    }
}