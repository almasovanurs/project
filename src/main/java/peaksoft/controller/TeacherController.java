package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Teacher;
import peaksoft.service.CourseService;
import peaksoft.service.TeacherService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService service;
    private final CourseService courseService;

    @Autowired
    public TeacherController(TeacherService service, CourseService courseService) {
        this.service = service;
        this.courseService = courseService;
    }

    @GetMapping()
    public String index(@RequestParam("courseId") Long courseId, Model model) {
        List<Teacher> teachers = new ArrayList<>();
        try {
            for (Teacher i : service.getAllTeacher()) {
                if (i.getCourse().getId().equals(courseId)) {
                    teachers.add(i);
                }
            }
        } catch (NullPointerException e) {
            System.out.println("It is empty!");
        }
        model.addAttribute("teachers", teachers);
        return "/teacher/teacher";
    }

    @GetMapping("/newTeacher")
    public String company(@RequestParam("courseId") Long courseId, Model model) {
        model.addAttribute("teacher", new Teacher());
        return "/teacher/newTeacher";
    }

    @PostMapping("/saveTeacher")
    public String getCompany(@ModelAttribute("teacher") Teacher teacher, @RequestParam("courseId") Long id) {
        teacher.setCourse(courseService.getById(id));
        service.saveTeacher(teacher);
        return "redirect:/teacher?courseId=" + id;
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("teacher", service.getById(id));
        return "/teacher/update";
    }

    @PatchMapping("/{id}/update")
    public String update(@ModelAttribute("teacher") Teacher teacher, @PathVariable("id") Long id, @RequestParam("courseId") Long courseId) {
        teacher.setCourse(courseService.getById(courseId));
        service.updateTeacher(id, teacher);
        return "redirect:/teacher?courseId=" + courseId;
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@RequestParam("courseId") Long courseId, @PathVariable("id") Long teacherId) {
        service.removeTeacherById(service.getById(teacherId));
        return "redirect:/teacher?courseId=" + courseId;
    }
}

