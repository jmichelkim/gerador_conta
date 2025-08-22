package caixa.ada.controller;

import caixa.ada.DTO.ClienteDTO;
import caixa.ada.model.Conta;
import caixa.ada.service.ContaService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;

@Path("/contas")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @POST
    @Transactional
    public RestResponse cadastrarConta(ClienteDTO clienteDTO, @Context UriInfo uriInfo){
        this.contaService.cadastrarConta(clienteDTO);
        return RestResponse.created(uriInfo.getAbsolutePath());
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
    @Path ("{id}")
    public RestResponse buscarConta(@RestPath Long id){
        return RestResponse.ok(contaService.findById(id));
    }

    @PATCH
    @Transactional
    @Path ("{id}")
    public RestResponse<Void> encerrarConta(@RestPath Long id){
        this.contaService.encerrarConta(id);
        return RestResponse.ok();
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public RestResponse<Void> deletarConta (@RestPath Long id){
        this.contaService.deletarConta(id);
        return RestResponse.ok();
    }


}
