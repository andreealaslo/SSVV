package test;

import domain.Tema;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class TestClassAssignment {

    public Service service;
    public String filenameStudent = "fisiere/Studenti.xml";
    public String filenameTema = "fisiere/Teme.xml";
    public String filenameNota = "fisiere/Note.xml";

    public void setup(){
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
    }

    @Test
    public void addAssignmentWithEmptyId(){
        setup();
        Tema tema1 = new Tema("", "descriere", 3,2);
        try {
            service.addTema(tema1);
        } catch (ValidationException exception) {
            assertTrue(exception.getMessage().equals("Numar tema invalid!"));
            return; // Test passed
        }
        assertFalse(true);
    }

    @Test
    public void addAssignmentWithNullId(){
        setup();
        Tema tema2 = new Tema(null, "descriere", 3,2);
        try {
            service.addTema(tema2);
        } catch (ValidationException exception) {
            assertTrue(exception.getMessage().equals("Numar tema invalid!"));
            return; // Test passed
        }
        assertFalse(true);
    }

    @Test
    public void addAssignmentWithEmptyDescription(){
        setup();
        Tema tema1 = new Tema("id1", "", 3,2);
        try {
            service.addTema(tema1);
        } catch (ValidationException exception) {
            assertTrue(exception.getMessage().equals("Descriere invalida!"));
            return; // Test passed
        }
        assertFalse(true);
    }

}
