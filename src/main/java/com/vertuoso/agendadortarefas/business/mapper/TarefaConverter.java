package com.vertuoso.agendadortarefas.business.mapper;

import org.mapstruct.Mapper;

import com.vertuoso.agendadortarefas.business.dto.TarefaDTO;
import com.vertuoso.agendadortarefas.infrastructure.entities.TarefaEntity;

@Mapper(componentModel = "spring")
public interface TarefaConverter {

    TarefaEntity paraTarefaEntity(TarefaDTO tarefaDTO);
    TarefaDTO paraTarefaDTO(TarefaEntity tarefaEntity);
}
