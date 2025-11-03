package com.uniQuindio.gestionDesastres.mapping.dto;

import com.uniQuindio.gestionDesastres.model.TipoRecurso;

public record RecursoDto(String id, String nombre, TipoRecurso tipo, int cantidad) {}
