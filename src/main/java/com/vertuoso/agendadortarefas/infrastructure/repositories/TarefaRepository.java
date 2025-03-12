package com.vertuoso.agendadortarefas.infrastructure.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vertuoso.agendadortarefas.infrastructure.entities.TarefaEntity;

@Repository
public interface TarefaRepository extends MongoRepository<TarefaEntity, String> {
}
