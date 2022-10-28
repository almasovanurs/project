package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Company;
import peaksoft.service.CompanyService;



@Controller
public class CompanyController {

    private final CompanyService service;

    @Autowired
    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String getAllCompany(Model model) {
        model.addAttribute("companies", service.getAllCompany());
        return "/company/companies";
    }

    @GetMapping("/newCompany")
    public String newCompany(Model model) {
        model.addAttribute("company", new Company());
        return "/company/addCompany";
    }

    @PostMapping("/save")
    public String saveCompany(@ModelAttribute("company") Company company) {
        service.saveCompany(company);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String getById(Model model, @PathVariable("id") long id) {
        model.addAttribute("company", service.getById(id));
        return "company/update";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("company") Company company, @PathVariable("id") Long id) {
        service.update(id, company);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        service.removeCompanyById(id);
        return "redirect:/";
    }
}


