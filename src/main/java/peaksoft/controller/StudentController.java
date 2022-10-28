package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Student;
import peaksoft.service.GroupService;
import peaksoft.service.StudentService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;
    private final GroupService groupService;

    @Autowired
    public StudentController(StudentService service, GroupService groupService) {
        this.service = service;
        this.groupService = groupService;
    }

    @GetMapping()
    public String index(@RequestParam("groupId") Long groupId , Model model) {
        List<Student> students = new ArrayList<>();
        try{
            for(Student i : service.getAllStudent()){
                if(i.getGroups().getId().equals(groupId)){
                    students.add(i);
                }
            }
        }
        catch(NullPointerException e){
            System.out.println("It is empty!");
        }
        model.addAttribute("students", students);
        return "/student/student";
    }

    @GetMapping("/newStudent")
    public String saveStudent(@RequestParam("groupId") Long groupId,Model model) {
        model.addAttribute("student", new Student());
        return "/student/NewStudent";
    }

    @PostMapping("/saveStudent")
    public String createStudent(@ModelAttribute("student") Student student, @RequestParam("groupId") Long courseId) {
        student.setGroups(groupService.getById(courseId));
        service.saveStudent(student);
        return "redirect:/student?groupId="+courseId;
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("student", service.getById(id));
        return "/student/update";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("student") Student student, @PathVariable("id") Long id,@RequestParam("groupId") Long groupId) {
        student.setGroups(groupService.getById(groupId));
        service.updateStudent(id, student);
        return "redirect:/student?groupId="+groupId;
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id,@RequestParam("groupId") Long groupId) {
        service.removeStudentById(id);
        return "redirect:/student?groupId="+groupId;
    }
}
