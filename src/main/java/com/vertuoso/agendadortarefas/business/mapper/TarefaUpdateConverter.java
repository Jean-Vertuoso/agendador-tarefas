package com.vertuoso.agendadortarefas.business.mapper;

import com.vertuoso.agendadortarefas.business.dto.TarefaDTO;
import com.vertuoso.agendadortarefas.infrastructure.entities.TarefaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateConverter {

    void updateTarefas(TarefaDTO tarefaDTO, @MappingTarget TarefaEntity tarefaEntity);
}