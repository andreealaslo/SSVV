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
            return;
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
            return;
        }
        assertFalse(true);
    }

    @Test
    public void addAssignmentWithEmptyDescription(){
        setup();
        Tema tema3 = new Tema("id1", "", 3,2);
        try {
            service.addTema(tema3);
        } catch (ValidationException exception) {
            assertTrue(exception.getMessage().equals("Descriere invalida!"));
            return;
        }
        assertFalse(true);
    }

    @Test
    public void addAssignmentWithDeadlineSmallerThan0(){
        setup();
        Tema tema4 = new Tema("id4", "descriere", -1,2);
        try {
            service.addTema(tema4);
        } catch (ValidationException exception) {
            assertTrue(exception.getMessage().equals("Deadlineul trebuie sa fie intre 1-14."));
            return;
        }
        assertFalse(true);
    }

    @Test
    public void addAssignmentWithDeadlineBiggerThan14(){
        setup();
        Tema tema5 = new Tema("id5", "descriere", 18,3);
        try {
            service.addTema(tema5);
        } catch (ValidationException exception) {
            assertTrue(exception.getMessage().equals("Deadlineul trebuie sa fie intre 1-14."));
            return;
        }
        assertFalse(true);
    }

    @Test
    public void addAssignmentWithPrimireSmallerThan0(){
        setup();
        Tema tema6 = new Tema("id6", "descriere", 2,-2);
        try {
            service.addTema(tema6);
        } catch (ValidationException exception) {
            assertTrue(exception.getMessage().equals("Saptamana primirii trebuie sa fie intre 1-14."));
            return;
        }
        assertFalse(true);
    }
    @Test
    public void addAssignmentWithPrimireBiggerThan14(){
        setup();
        Tema tema7 = new Tema("id7", "descriere", 2,16);
        try {
            service.addTema(tema7);
        } catch (ValidationException exception) {
            assertTrue(exception.getMessage().equals("Saptamana primirii trebuie sa fie intre 1-14."));
            return; 
        }
        assertFalse(true);
    }

    @Test
    public void addAssignmentWithSUCCESS(){
        setup();
        Tema temaOK = new Tema("idOK", "descriereOK", 3,2);
        try {
            service.addTema(temaOK);
        } catch (ValidationException exception) {
            System.out.println(exception);
            assertFalse(true);
        }
        assert (service.findTema("idOK") != null);
    }

}
