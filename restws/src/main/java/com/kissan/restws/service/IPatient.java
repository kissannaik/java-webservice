package com.kissan.restws.service;

import com.kissan.restws.beans.Patient;
import com.kissan.restws.exception.PatientBusinessException;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/patientservice")
@Consumes("application/xml,application/json")
@Produces("application/xml,application/json")
public interface IPatient {

    @GET
    @Path("/patients")
    List<Patient> getPatients();

    @GET
    @Path("/patients/{id}")
    Patient getPatient(@PathParam("id") Long id);

    @POST
    @Path("/patients")
    Response addPatient(Patient patient);

    @PUT
    @Path("/patients")
    Response updatePatient(Patient patient) throws PatientBusinessException;

    @DELETE
    @Path("/patients/{id}")
    Response deletePatient(@PathParam("id") Long id);
}
