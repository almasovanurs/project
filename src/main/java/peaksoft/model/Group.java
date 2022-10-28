package peaksoft.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String groupName;
    private String dateOfStart;
    private String dateOfFinish;

    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Course> courseList;

    @OneToMany(mappedBy = "groups", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Student> students;

    @ManyToOne
    private Company company;

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", dateOfStart='" + dateOfStart + '\'' +
                ", dateOfFinish='" + dateOfFinish + '\'' +
                ", courseList=" + courseList +
                ", student=" + students +
                ", company=" + company +
                '}';
    }
}

