package test;

import domain.Student;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertFalse;
@RunWith(JUnit4.class)
public class TestClass {
    public static Service service;

//    @BeforeAll
//    public static void setup() {
//
//
//    }

    @Test
    public void addStudent_Success() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "C:\\Users\\user\\Desktop\\SSVV\\LabAssiAsseProjectV02\\fisiere\\Studenti.xml";
        String filenameTema = "C:\\Users\\user\\Desktop\\SSVV\\LabAssiAsseProjectV02\\fisiere\\Teme.xml";
        String filenameNota = "C:\\Users\\user\\Desktop\\SSVV\\LabAssiAsseProjectV02\\fisiere\\Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        TestClass.service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        String id = "test";
        String nume = "ana";
        int grupa = 934;
        String email = "aaaa@gmail.com";
        Student student = new Student(id, nume, grupa, email);
        try {
            service.addStudent(student);
        } catch (ValidationException exception) {
            System.out.println(exception);
            assertFalse(true);
        }
        assert(service.findStudent(id) != null);

    }
}
