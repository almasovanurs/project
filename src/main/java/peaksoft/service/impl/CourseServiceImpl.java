package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.dao.CourseDao;
import peaksoft.model.Course;
import peaksoft.service.CourseService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    private final CourseDao courseDao;

    @Autowired
    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public Course saveCourse(Course course/*,Long id*/) {
        return courseDao.saveCourse(course/*,id*/);
    }

    @Override
    public void removeCourseById(Long id) {
        courseDao.removeCourseById(id);
    }

    @Override
    public Course getById(Long id) {
        return courseDao.getById(id);
    }

    @Override
    public List<Course> getAllCourse() {
        return courseDao.getAllCourse();
    }

    @Override
    public void updateCourse( Course course) {
        courseDao.updateCourse(course);
    }
}

