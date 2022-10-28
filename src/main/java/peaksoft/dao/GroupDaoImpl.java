package peaksoft.dao;

import org.springframework.stereotype.Repository;
import peaksoft.model.Group;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class GroupDaoImpl implements GroupDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Group saveGroup(Group group) {
        entityManager.merge(group);
        return group;
    }

    @Override
    public void removeGroupById(Long id) {
        entityManager.remove(getById(id));
    }

    @Override
    public Group getById(Long id) {
        return entityManager.find(Group.class,id);
    }

    @Override
    public List<Group> getAllGroup() {
      //  return entityManager.createQuery("select g from Group g",Group.class).getResultList();
        return null;
    }

    @Override
    public void updateGroup(Long id, Group group) {
        Group group1 = getById(id);
        group1.setGroupName(group.getGroupName());
        group1.setDateOfStart(group.getDateOfStart());
        group1.setDateOfFinish(group.getDateOfFinish());
        entityManager.merge(group);
    }
}


