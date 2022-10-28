package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.dao.TeacherDao;
import peaksoft.model.Teacher;
import peaksoft.service.TeacherService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

    private final TeacherDao dao;

    @Autowired
    public TeacherServiceImpl(TeacherDao dao) {
        this.dao = dao;
    }

    @Override
    public Teacher saveTeacher(Teacher teacher) {
        return dao.saveTeacher(teacher);
    }

    @Override
    public void removeTeacherById(Teacher teacher) {
        dao.removeTeacherById(teacher);
    }

    @Override
    public Teacher getById(Long id) {
        return dao.getById(id);
    }

    @Override
    public List<Teacher> getAllTeacher() {
        return dao.getAllTeacher();
    }

    @Override
    public void updateTeacher(Long id, Teacher teacher) {
        dao.updateTeacher(id, teacher);
    }
}

