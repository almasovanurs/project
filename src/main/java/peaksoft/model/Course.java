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
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseName;
    private String duration;

    @ManyToOne
    private Company company;

    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "courseList", cascade = {CascadeType.MERGE, CascadeType.REMOVE} )
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Group> groupList;

    @OneToOne(mappedBy = "course",
            cascade = CascadeType.REMOVE)
    private Teacher teacher;

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", duration='" + duration + '\'' +
                ", company=" + company +
                ", group=" + groupList +
                ", teacher=" + teacher +
                '}';
    }
}

