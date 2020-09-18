package com.kissan.restws.service;

import com.kissan.restws.beans.Patient;
import com.kissan.restws.exception.PatientBusinessException;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PatientImpl implements IPatient{
    private Map<Long, Patient> patientsMap = new HashMap<>();
    private Long currentPatient = 123L;

    public PatientImpl(){
        Patient patient = new Patient();
        patient.setId(currentPatient);
        patient.setName("John");

        patientsMap.put(patient.getId(), patient);
    }

    @Override
    public List<Patient> getPatients() {
        return new ArrayList<>(patientsMap.values());
    }

    @Override
    public Patient getPatient(Long id) {
        return patientsMap.get(id);
    }

    @Override
    public Response addPatient(Patient patient) {

        patient.setId(++currentPatient);
        patientsMap.put(patient.getId(), patient);

        return Response.ok(patient).build();
    }

    @Override
    public Response updatePatient(Patient patient) throws PatientBusinessException {
        Response response = null;

        Patient currentPatient = patientsMap.get(patient.getId());
        if(currentPatient != null){
            patientsMap.put(patient.getId(), patient);
            response = Response.ok().build();
        }else {
            throw new PatientBusinessException("Patient Not Found");
        }

        return response;
    }

    @Override
    public Response deletePatient(Long id) {
        Response response = Response.notModified().build();

        Patient currentPatient = patientsMap.get(id);
        if(currentPatient != null){
            patientsMap.remove(id);
            response = Response.ok().build();
        }

        return response;
    }

}
