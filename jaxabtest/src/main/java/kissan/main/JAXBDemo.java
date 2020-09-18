package kissan.main;

import kissan.beans.patient.Gender;
import kissan.beans.patient.Patient;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class JAXBDemo {

    public static void main(String[] args) {
        try {
            JAXBContext context = JAXBContext.newInstance(Patient.class);
            Marshaller marshaller = context.createMarshaller();

            Patient patient = new Patient();
            patient.setId(123);
            patient.setName("Kissan Naik");
            patient.setGender(Gender.M);
            patient.setAge(45);

            //Marshall Patient object
            StringWriter writer = new StringWriter();
            marshaller.marshal(patient, writer);
            System.out.println("Marshalled data = " + writer.toString());

            // UnMarshall Patient data
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Patient patient1 = (Patient)unmarshaller.unmarshal(new StringReader(writer.toString()));
            System.out.println("UnMarshalled data = " + patient1.getName());

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
