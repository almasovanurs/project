package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Course;
import peaksoft.service.CompanyService;
import peaksoft.service.CourseService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("courses")
public class CourseController {

    private final CourseService service;
    private final CompanyService companyService;

    @Autowired
    public CourseController(CompanyService companyService, CourseService service) {
        this.companyService = companyService;
        this.service = service;
    }

    @GetMapping()
    public String getCourses(@RequestParam("companyId") Long companyId, Model model) {
        List<Course> courses = new ArrayList<>();
        try {
            for (Course i : service.getAllCourse()) {
                if (i.getCompany().getId().equals(companyId)) {
                    courses.add(i);
                }
            }
        } catch (NullPointerException e) {
            System.out.println("It is empty!");
        }
        model.addAttribute("course", courses);
        return "/course/courses";
    }

    @GetMapping("/newCourse")
    public String newCourse( Model model, @RequestParam("companyId") Long companyId) {
        model.addAttribute("course", new Course());
        return "/course/addCourse";
    }

    @PostMapping("saveCourse")
    public String saveCourse(@ModelAttribute("course") Course course, @RequestParam("companyId") Long id) {
        course.setCompany(companyService.getById(id));
        service.saveCourse(course);
        return "redirect:/course?companyId=" + id;
    }

    @GetMapping("/{id}/update")
    public String updateCourse(@PathVariable("id") Long id, Model model){
        model.addAttribute("course", service.getById(id));
        return "course/update";
    }

    @PostMapping("/{id}")
    public String updateCourse(@PathVariable("id") Long id,@RequestParam("companyId") Long companyId,@ModelAttribute("course") Course course){

        course.setCompany(companyService.getById(companyId));
        service.updateCourse(course);
        return "redirect:/course?companyId="+companyId;
    }

    @DeleteMapping("/{id}/delete")
    public String deleteCourse(@PathVariable("id") Long id, @RequestParam("companyId") Long companyId) {
        service.removeCourseById(id);
        return "redirect:/course?companyId=" + companyId;


    }
}

