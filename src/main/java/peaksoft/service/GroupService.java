package peaksoft.service;

import peaksoft.model.Group;

import java.util.List;

public interface GroupService {
    Group saveGroup(Group gq);

    void removeGroupById(Long id);

    Group getById(Long id);

    List<Group> getAllGroup();

    void updateGroup(Long id, Group group);
}
