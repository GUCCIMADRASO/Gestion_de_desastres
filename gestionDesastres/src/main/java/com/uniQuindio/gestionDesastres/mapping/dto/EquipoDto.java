package com.uniQuindio.gestionDesastres.mapping.dto;

import com.uniQuindio.gestionDesastres.model.TipoEquipo;

public record EquipoDto(String idEquipo, int integrantesDisponibles, TipoEquipo tipoEquipo){}