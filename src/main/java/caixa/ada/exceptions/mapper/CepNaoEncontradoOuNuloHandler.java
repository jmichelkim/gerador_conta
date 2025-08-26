package caixa.ada.exceptions.mapper;

import caixa.ada.exceptions.CepNaoEncontradoOuNuloException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CepNaoEncontradoOuNuloHandler implements ExceptionMapper<CepNaoEncontradoOuNuloException> {

    @Override
    public Response toResponse(CepNaoEncontradoOuNuloException e){
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(e.getMessage())
                .build();
    }
}
