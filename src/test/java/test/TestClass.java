package test;

import domain.Student;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
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
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)

public class TestClass {
    public Service service;
    public String filenameStudent = "fisiere/Studenti.xml";
    public String filenameTema = "fisiere/Teme.xml";
    public String filenameNota = "fisiere/Note.xml";


    @Test
    public void addStudentSuccess() {

        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

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
        assert (service.findStudent(id) != null);
    }

    @Test
    public void addStudentWithEmptyIdFails() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        String id = "";
        String nume = "ana";
        int grupa = 934;
        String email = "aaaa@gmail.com";
        Student student = new Student(id, nume, grupa, email);
        try {
            service.addStudent(student);
        } catch (ValidationException exception) {
            assertTrue(exception.getMessage().equals("Id incorect!"));
            return; // Test passed
        }
        assertFalse(true);
    }

    @Test
    public void addStudentWithNullIdFails() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        String id = null;
        String nume = "ana";
        int grupa = 934;
        String email = "aaaa@gmail.com";
        Student student = new Student(id, nume, grupa, email);
        try {
            service.addStudent(student);
        } catch (ValidationException exception) {
            assertTrue(exception.getMessage().equals("Id incorect!"));
            return; // Test passed
        }
        assertFalse(true);
    }

    @Test
    public void addStudentWithDuplicateIdFails() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        String id = "testfvdf";
        String nume = "ana";
        int grupa = 934;
        String email = "aaaa@gmail.com";
        Student student = new Student(id, nume, grupa, email);

        try {
             service.addStudent(student);
        } catch (ValidationException exception) {
            assertFalse(true);
        }
        Student studentWithDuplicateId = new Student("testfvdf", "otherName", 999, "otheremail@example.com");
        Student result = service.addStudent(studentWithDuplicateId);
        assert(result==studentWithDuplicateId);
    }

    @Test
    public void addStudentWithValidNameSuccess() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        String id = "test1";
        String nume = "ana";
        int grupa = 934;
        String email = "aaaa@gmail.com";
        Student student = new Student(id, nume, grupa, email);
        try {
            service.addStudent(student);
        } catch (ValidationException exception) {
            assertFalse(true);
        }
        assertTrue(service.findStudent(id) != null);
    }

    @Test
    public void addStudentWithEmptyNameFails() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        String id = "test2";
        String nume = "";
        int grupa = 934;
        String email = "aaaa@gmail.com";
        Student student = new Student(id, nume, grupa, email);
        try {
            service.addStudent(student);
        } catch (ValidationException exception) {
            assertTrue(exception.getMessage().equals("Nume incorect!"));
            return; // Test passed
        }
        assertFalse(true);
    }

    @Test
    public void addStudentWithNullNameFails() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        String id = "test3";
        String nume = null;
        int grupa = 934;
        String email = "aaaa@gmail.com";
        Student student = new Student(id, nume, grupa, email);
        try {
            service.addStudent(student);
        } catch (ValidationException exception) {
            assertTrue(exception.getMessage().equals("Nume incorect!"));
            return; // Test passed
        }
        assertFalse(true);
    }

    @Test
    public void addStudentWithValidEmailSuccess() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        String id = "test4";
        String nume = "ana";
        int grupa = 934;
        String email = "aaaa@gmail.com";
        Student student = new Student(id, nume, grupa, email);
        try {
            service.addStudent(student);
        } catch (ValidationException exception) {
            assertFalse(true);
        }
        assertTrue(service.findStudent(id) != null);
    }

    @Test
    public void addStudentWithEmptyEmailFails() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        String id = "test5";
        String nume = "ana";
        int grupa = 934;
        String email = "";
        Student student = new Student(id, nume, grupa, email);
        try {
            service.addStudent(student);
        } catch (ValidationException exception) {
            assertTrue(exception.getMessage().equals("Email incorect!"));
            return; // Test passed
        }
        assertFalse(true);
    }

    @Test
    public void addStudentWithNullEmailFails() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        String id = "test6";
        String nume = "ana";
        int grupa = 934;
        String email = null;
        Student student = new Student(id, nume, grupa, email);
        try {
            service.addStudent(student);
        } catch (ValidationException exception) {
            assertTrue(exception.getMessage().equals("Email incorect!"));
            return; // Test passed
        }
        assertFalse(true);
    }

    @Test
    public void addStudentWithValidGroupSuccess() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        String id = "test7";
        String nume = "ana";
        int grupa = 934;
        String email = "aaaa@gmail.com";
        Student student = new Student(id, nume, grupa, email);
        try {
            service.addStudent(student);
        } catch (ValidationException exception) {
            assertFalse(true);
        }
        assertTrue(service.findStudent(id) != null);
    }

    @Test
    public void addStudentWithInvalidGroupFails() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        String id = "test8";
        String nume = "ana";
        int grupa = -1;
        String email = "aaaa@gmail.com";
        Student student = new Student(id, nume, grupa, email);
        try {
            service.addStudent(student);
        } catch (ValidationException exception) {
            assertTrue(exception.getMessage().equals("Grupa incorecta!"));
            return; // Test passed
        }
        assertFalse(true);
    }

}
