package com.kissan.restws.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class PatientBusinessExceptionMapper implements ExceptionMapper<PatientBusinessException> {
    @Override
    public Response toResponse(PatientBusinessException e) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"status\":\"error\"");
        sb.append(",");
        sb.append("\"message\":");
        sb.append(e.getLocalizedMessage());
        sb.append("}");

        return Response.serverError().entity(sb.toString()).build();
    }
}
