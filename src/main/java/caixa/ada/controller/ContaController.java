package caixa.ada.controller;

import caixa.ada.DTO.ClienteDTO;
import caixa.ada.model.Conta;
import caixa.ada.service.ContaService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.resteasy.reactive.RestPath;

import java.util.List;

@Path("/contas")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @POST
    @Transactional
    @Operation(summary = "Cadastra nova conta",
            description = "Cadastra nova conta para o cliente, vinculando a uma agência")
    public Response addConta(ClienteDTO clienteDTO, @Context UriInfo uriInfo){
        this.contaService.cadastrarConta(clienteDTO);
        return Response.status(Response.Status.CREATED).
                entity("Conta Cadastrada com sucesso").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Listar todas contas cadastradas",
            description = "Retorna uma lista de contas cadastrados")
    public List<Conta> getContas() {
        return this.contaService.getContas();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Busca conta cadastrada",
            description = "Busca conta cadastrada pelo 'id'")
    @Path ("{id}")
    public Response findConta(@RestPath Long id){
        return Response.ok(contaService.findById(id)).build();

    }

    @PATCH
    @Transactional
    @Operation(summary = "Encerra conta cadastrada",
            description = "Encerra conta pelo 'id'")
    @Path ("{id}")
    public Response closeConta(@RestPath Long id){
        this.contaService.encerrarConta(id);
        return Response.status(Response.Status.OK)
                .entity("Conta encerrada com sucesso").build();
    }

    @DELETE
    @Transactional
    @Operation(summary = "Apaga conta cadastrada",
            description = "Apaga conta pelo 'id'")
    @Path("{id}")
    public Response deleteConta(@RestPath Long id){
        this.contaService.deletarConta(id);
        return Response.status(Response.Status.OK)
                .entity("Conta excluída com sucesso").build();
    }
}
