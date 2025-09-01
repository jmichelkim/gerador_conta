package caixa.ada.controller;

import caixa.ada.DTO.ClienteDTO;
import caixa.ada.DTO.RelatorioDTO;
import caixa.ada.model.Conta;
import caixa.ada.model.log.OperacaoLog;
import caixa.ada.repository.log.OperacaoLogRepository;
import caixa.ada.service.ContaService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.resteasy.reactive.RestPath;

import java.util.List;

@Path("/contas")
public class ContaController {

    private final ContaService contaService;
    private final OperacaoLogRepository logRepository;

    public ContaController(ContaService contaService, OperacaoLogRepository logRepository) {

        this.contaService = contaService;
        this.logRepository = logRepository;
    }

    @POST
    @Transactional
    @RolesAllowed({"admin", "user"})
    @Operation(summary = "Cadastra nova conta",
            description = "Cadastra nova conta para o cliente, vinculando a uma agência")
    public Response addConta(ClienteDTO clienteDTO){
        this.contaService.cadastrarConta(clienteDTO);
        return Response.status(Response.Status.CREATED).
                entity("Conta Cadastrada com sucesso").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    @Operation(summary = "Listar todas contas cadastradas",
            description = "Retorna uma lista de contas cadastrados")
    public List<Conta> getContas() {
        return this.contaService.getContas();
    }


    @GET
    @RolesAllowed({"admin", "user"})
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Busca conta cadastrada",
            description = "Busca conta cadastrada pelo 'id'")
    @Path ("{id}")
    public Response findConta(@RestPath Long id){
        return Response.ok(contaService.findById(id)).build();

    }

    @PATCH
    @Transactional
    @RolesAllowed({"admin", "user"})
    @Operation(summary = "Encerra conta cadastrada",
            description = "Encerra conta pelo 'id'")
    @Path ("{id}")
    public Response closeConta(@RestPath Long id){
        this.contaService.encerrarConta(id);
        return Response.status(Response.Status.OK)
                .entity("Conta encerrada com sucesso").build();
    }

    @PUT
    @Transactional
    @Operation(summary = "Altera cliente conta cadastrada",
            description = "Altera dados do cliente de uma conta cadastrada")
    @Path ("{id}")
    public Response alterarConta(@RestPath Long id, ClienteDTO clienteDTO){
        this.contaService.alterarConta(id, clienteDTO);
        return Response.status(Response.Status.OK)
                .entity("Conta a alterada com sucesso").build();
    }

    @DELETE
    @Transactional
    @RolesAllowed({"admin"})
    @Operation(summary = "Exclui conta cadastrada",
            description = "Deleta conta pelo 'id'")
    @Path("{id}")
    public Response deleteConta(@RestPath Long id){
        this.contaService.deletarConta(id);
        return Response.status(Response.Status.OK)
                .entity("Conta excluída com sucesso").build();
    }

    @GET
    @Path("/agencia/{idAgencia}/relatorio")
    //@RolesAllowed({"admin", "user"})
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Gera relatório por agência",
            description = "Retorna um relatório com todas as contas ativas da agência informada")
    public Response gerarRelatorioPorAgencia(@RestPath Long idAgencia) {
        RelatorioDTO relatorio = contaService.gerarRelatorioPorAgencia(idAgencia);
        return Response.ok(relatorio).build();
    }

    @GET
    @Path("{id}/historico")
    public List<OperacaoLog> historicoPorConta(@PathParam("id") Long id) {
        return logRepository.findByContaId(id);
    }

    @GET
    @Path("clientes/{id}/historico")
    public List<OperacaoLog> historicoPorCliente(@PathParam("id") Long id) {
        return logRepository.findByClienteId(id);
    }
}
