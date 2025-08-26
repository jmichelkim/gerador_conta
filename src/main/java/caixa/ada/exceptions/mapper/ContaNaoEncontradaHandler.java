package caixa.ada.exceptions.mapper;

import caixa.ada.exceptions.AgenciaNaoEncontradaException;
import caixa.ada.exceptions.ContaNaoEncontradaException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ContaNaoEncontradaHandler implements ExceptionMapper<ContaNaoEncontradaException> {

    @Override
    public Response toResponse(ContaNaoEncontradaException e){
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(e.getMessage())
                .build();
    }
}
