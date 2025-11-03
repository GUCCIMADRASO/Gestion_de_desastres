package com.uniQuindio.gestionDesastres.mapping.dto;

import com.uniQuindio.gestionDesastres.model.TipoDesastre;
import com.uniQuindio.gestionDesastres.model.Ubicacion;
import java.time.LocalDate;

public record DesastreDto(int magnitud, String nombre, String idDesastre, TipoDesastre tipoDesastre,
                             int personasAfectadas, LocalDate fecha, Ubicacion ubicacion){}

