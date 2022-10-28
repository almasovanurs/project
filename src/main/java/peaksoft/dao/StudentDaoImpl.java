package peaksoft.dao;

import org.springframework.stereotype.Repository;
import peaksoft.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class StudentDaoImpl implements StudentDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Student saveStudent(Student student) {
        entityManager.persist(student);
        return student;
    }

    @Override
    public void removeStudentById(Long id) {

        entityManager.remove(getById(id));
    }

    @Override
    public Student getById(Long id) {
        return entityManager.find(Student.class,id);
    }

    @Override
    public List<Student> getAllStudent() {
        //return entityManager.createQuery("select s from Student s",Student.class).getResultList();
        return null;
    }

    @Override
    public void updateStudent(Long id, Student student) {
        Student student1 = getById(id);
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setEmail(student.getEmail());
        student1.setLearningFormat(student.getLearningFormat());
        entityManager.merge(student);
    }
}

