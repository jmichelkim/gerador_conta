package caixa.ada.exceptions.mapper;

import caixa.ada.exceptions.AgenciaNaoEncontradaException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class AgenciaNaoEncontradaHandler implements ExceptionMapper<AgenciaNaoEncontradaException> {

    @Override
    public Response toResponse(AgenciaNaoEncontradaException e){
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(e.getMessage())
                .build();
    }
}
