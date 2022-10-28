package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.dao.StudentDao;
import peaksoft.model.Student;
import peaksoft.service.StudentService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentDao dao;

    @Autowired
    public StudentServiceImpl(StudentDao dao) {
        this.dao = dao;
    }

    @Override
    public Student saveStudent(Student student) {
        return dao.saveStudent(student);
    }

    @Override
    public void removeStudentById(Long id) {
        dao.removeStudentById(id);
    }

    @Override
    public Student getById(Long id) {
        return dao.getById(id);
    }

    @Override
    public List<Student> getAllStudent() {
        return dao.getAllStudent();
    }

    @Override
    public void updateStudent( Long id,Student student) {
        dao.updateStudent(id,student);
    }
}

