package com.vertuoso.agendadortarefas.controllers;

import java.time.LocalDateTime;
import java.util.List;

import com.vertuoso.agendadortarefas.business.dto.TarefaDTO;
import com.vertuoso.agendadortarefas.business.services.TarefaService;
import com.vertuoso.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.vertuoso.agendadortarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefas")
@Tag(name = "Tarefas", description = "Cadastro e atualização de tarefas")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefaController {

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @PostMapping
    @Operation(summary = "Salvar Tarefas de Usuários", description = "Cria uma nova tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa salva com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefaDTO> gravarTarefas(@RequestBody TarefaDTO dto,
                                                   @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(tarefaService.gravarTarefa(dto, token));
    }

    @GetMapping("/all")
    @Operation(summary = "Busca lista de tarefas por email de usuário",
            description = "Busca tarefas cadastradas por usuário")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    @ApiResponse(responseCode = "403", description = "Email não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<TarefaDTO>> buscaTarefasPorEmail(@RequestHeader("Authorization")String token){
        return ResponseEntity.ok(tarefaService.buscaTarefasPorEmail(token));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Busca tarefas por período", description = "Busca tarefas cadastradas por período")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<TarefaDTO>> buscaListaDeTarefasPorPeriodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataInicial,
                                                                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataFinal){

        return ResponseEntity.ok(tarefaService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal));
    }

    @DeleteMapping
    @Operation(summary = "Deleta tarefas por Id", description = "Deleta tarefas cadastradas por Id")
    @ApiResponse(responseCode = "200", description = "Tarefas deletadas")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    @ApiResponse(responseCode = "403", description = "Tarefa Id não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id") String id){
        tarefaService.deletaTarefaPorId(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Altera status de tarefas", description = "Altera status das tarefas cadastradas")
    @ApiResponse(responseCode = "200", description = "Tarefas alteradas")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    @ApiResponse(responseCode = "403", description = "Tarefa Id não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefaDTO> alteraStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                                             @RequestParam("id") String id){
        return ResponseEntity.ok(tarefaService.alteraStatus(status, id));
    }

    @PutMapping
    @Operation(summary = "Altera dados de tarefas", description = "Altera dados das tarefas cadastradas")
    @ApiResponse(responseCode = "200", description = "Tarefas alteradas")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    @ApiResponse(responseCode = "403", description = "Tarefa Id não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefaDTO> updateTarefas(@RequestBody TarefaDTO dto,
                                                   @RequestParam("id") String id){
        return ResponseEntity.ok(tarefaService.updateTarefas(dto, id));
    }
}
