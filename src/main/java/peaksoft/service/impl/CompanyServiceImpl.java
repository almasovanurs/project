package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.dao.CompanyDao;
import peaksoft.model.Company;
import peaksoft.service.CompanyService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final CompanyDao dao;

    @Autowired
    public CompanyServiceImpl(CompanyDao dao) {
        this.dao = dao;
    }

    @Override
    public Company saveCompany(Company company) {
        dao.saveCompany(company);
        return company;
    }

    @Override
    public void removeCompanyById(Long id) {
        dao.removeCompanyById(id);
    }

    @Override
    public Company getById(Long id) {
        return dao.getById(id);
    }

    @Override
    public List<Company> getAllCompany() {
        return dao.getAllCompany();
    }

    @Override
    public void update(Long id,Company company) {
        dao.update(id,company);
    }
}
