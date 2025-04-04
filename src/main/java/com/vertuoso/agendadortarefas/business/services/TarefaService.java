package com.vertuoso.agendadortarefas.business.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vertuoso.agendadortarefas.business.dto.TarefaDTO;
import com.vertuoso.agendadortarefas.business.mapper.TarefaConverter;
import com.vertuoso.agendadortarefas.business.mapper.TarefaUpdateConverter;
import com.vertuoso.agendadortarefas.infrastructure.entities.TarefaEntity;
import com.vertuoso.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.vertuoso.agendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.vertuoso.agendadortarefas.infrastructure.repositories.TarefaRepository;
import com.vertuoso.agendadortarefas.infrastructure.security.JwtUtil;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaConverter tarefaConverter;
    private final TarefaUpdateConverter tarefaUpdateConverter;
    private final JwtUtil jwtUtil;

    public TarefaService(TarefaRepository tarefaRepository, TarefaConverter tarefaConverter, TarefaUpdateConverter tarefaUpdateConverter, JwtUtil jwtUtil) {
        this.tarefaRepository = tarefaRepository;
        this.tarefaConverter = tarefaConverter;
        this.tarefaUpdateConverter = tarefaUpdateConverter;
        this.jwtUtil = jwtUtil;
    }

    public TarefaDTO gravarTarefa(TarefaDTO tarefaDTO, String token){
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        tarefaDTO.setDataCriacao(LocalDateTime.now());
        tarefaDTO.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        tarefaDTO.setEmailUsuario(email);
        TarefaEntity entity = tarefaConverter.paraTarefaEntity(tarefaDTO);

        return tarefaConverter.paraTarefaDTO(
                tarefaRepository.save(entity));
    }

    public List<TarefaDTO> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal){
        StatusNotificacaoEnum status = StatusNotificacaoEnum.PENDENTE;

        return tarefaConverter.paraListaTarefasDTO(tarefaRepository.findByDataEventoBetweenAndStatusNotificacaoEnum(dataInicial, dataFinal, status));
    }

    public List<TarefaDTO> buscaTarefasPorEmail(String token){
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        List<TarefaEntity> listaTarefas = tarefaRepository.findByEmailUsuario(email);

        return tarefaConverter.paraListaTarefasDTO(listaTarefas);
    }

    public void deletaTarefaPorId(String id){
        try {
            tarefaRepository.deleteById(id);
        }catch(ResourceNotFoundException e){
            throw new ResourceNotFoundException("Erro ao deletar tarefa por id, id inexistente: "+ id,
                                                e.getCause());
        }
    }

    public TarefaDTO alteraStatus(StatusNotificacaoEnum status, String id){
        try {
            TarefaEntity entity = tarefaRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("Tarefa não encontrada " + id));

            entity.setStatusNotificacaoEnum(status);
            return tarefaConverter.paraTarefaDTO(tarefaRepository.save(entity));
        } catch(ResourceNotFoundException e){
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa "+ e.getCause());
        }
    }

    public TarefaDTO updateTarefas(TarefaDTO tarefaDTO, String id){
        try {
            TarefaEntity entity = tarefaRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("Tarefa não encontrada " + id));
            tarefaUpdateConverter.updateTarefas(tarefaDTO, entity);
            return tarefaConverter.paraTarefaDTO(tarefaRepository.save(entity));
        } catch(ResourceNotFoundException e){
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa "+ e.getCause());
        }
    }
}
