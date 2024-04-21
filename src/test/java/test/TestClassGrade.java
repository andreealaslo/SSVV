package test;

import domain.Nota;
import domain.Student;
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

import java.time.LocalDate;

import static org.junit.Assert.assertFalse;

@RunWith(JUnit4.class)
public class TestClassGrade {

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
    public void addStudentWithSUCCESS() {
        //black box test
        setup();

        String id = "success";
        String nume = "andreea";
        int grupa = 934;
        String email = "andreea@gmail.com";
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
    public void addAssignmentWithSUCCESS(){
        //white box test
        setup();
        Tema temaOK = new Tema("idSuccess", "descriereSuccess", 3,2);
        try {
            service.addTema(temaOK);
        } catch (ValidationException exception) {
            System.out.println(exception);
            assertFalse(true);
        }
        assert (service.findTema("idSuccess") != null);
    }

    @Test
    public void addGradeWithSUCCESS(){
        //white box test
        setup();
        Nota notaOK = new Nota("idGradeSuccess", "test", "idOK", 10, LocalDate.of(2024,4,8));
        try {
            service.addNota(notaOK, "feedback");
        } catch (ValidationException exception) {
            System.out.println(exception);
            assertFalse(true);
        }
        assert (service.findNota("idGradeSuccess") != null);
    }

    @Test
    public void addStudentAssignmentGradeWithSUCCESS(){
        setup();
        String id = "idcusuccess";
        String nume = "stefana";
        int grupa = 934;
        String email = "stefana@gmail.com";
        Student student = new Student(id, nume, grupa, email);
        try {
            service.addStudent(student);
        } catch (ValidationException exception) {
            System.out.println(exception);
            assertFalse(true);
        }
        assert (service.findStudent(id) != null);

        Tema temaOK = new Tema("idCuSuccess", "descriereSuccess", 3,2);
        try {
            service.addTema(temaOK);
        } catch (ValidationException exception) {
            System.out.println(exception);
            assertFalse(true);
        }
        assert (service.findTema("idCuSuccess") != null);

        Nota notaOK = new Nota("idGradeCuSuccess", "idcusuccess", "idCuSuccess", 10, LocalDate.of(2024,4,8));
        try {
            service.addNota(notaOK, "feedback");
        } catch (ValidationException exception) {
            System.out.println(exception);
            assertFalse(true);
        }
        assert (service.findNota("idGradeCuSuccess") != null);

    }

    @Test
    public void addStudentAssignmenWithSUCCESS(){
        setup();
        String id = "idcusuccess2";
        String nume = "stefanaaaa";
        int grupa = 934;
        String email = "stefanaaaaa@gmail.com";

        Student student = new Student(id, nume, grupa, email);
        try {
            service.addStudent(student);
        } catch (ValidationException exception) {
            System.out.println(exception);
            assertFalse(true);
        }
        assert (service.findStudent(id) != null);

        Tema temaOK = new Tema("idCuSuccess2", "descriereSuccess2", 4,3);
        try {
            service.addTema(temaOK);
        } catch (ValidationException exception) {
            System.out.println(exception);
            assertFalse(true);
        }
        assert (service.findTema("idCuSuccess2") != null);
    }

    @Test
    public void addStudentAssignmentAlreadyCreatedGradeWithSUCCESS(){
        setup();
        Nota notaOK = new Nota("idGradeCuSuccess2", "idcusuccess2", "idCuSuccess2", 10, LocalDate.of(2024,4,21));
        try {
            service.addNota(notaOK, "feedback");
        } catch (ValidationException exception) {
            System.out.println(exception);
            assertFalse(true);
        }
        assert (service.findNota("idGradeCuSuccess2") != null);

    }




}
