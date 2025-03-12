package com.vertuoso.agendadortarefas.infrastructure.entities;

import java.time.LocalDateTime;

import com.vertuoso.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("tarefa")
public class TarefaEntity {

    @Id
    private String id;
    private String nomeTarefa;
    private String descricao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataEvento;
    private String emailUsuario;
    private LocalDateTime dataAlteracao;
    private StatusNotificacaoEnum statusNotificacaoEnum;

    public TarefaEntity() {
    }

    public TarefaEntity(String id, String nomeTarefa, String descricao, LocalDateTime dataCriacao, LocalDateTime dataEvento, String emailUsuario, LocalDateTime dataAlteracao, StatusNotificacaoEnum statusNotificacaoEnum) {
        this.id = id;
        this.nomeTarefa = nomeTarefa;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.dataEvento = dataEvento;
        this.emailUsuario = emailUsuario;
        this.dataAlteracao = dataAlteracao;
        this.statusNotificacaoEnum = statusNotificacaoEnum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomeTarefa() {
        return nomeTarefa;
    }

    public void setNomeTarefa(String nomeTarefa) {
        this.nomeTarefa = nomeTarefa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataEvento(){
        return dataEvento;
    }

    public void setDataEvento(LocalDateTime dataEvento){
        this.dataEvento = dataEvento;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public LocalDateTime getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(LocalDateTime dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public StatusNotificacaoEnum getStatusNotificacaoEnum() {
        return statusNotificacaoEnum;
    }

    public void setStatusNotificacaoEnum(StatusNotificacaoEnum statusNotificacaoEnum) {
        this.statusNotificacaoEnum = statusNotificacaoEnum;
    }
}
