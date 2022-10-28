package peaksoft.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "company")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    public String locatedCountry;

    @OneToMany(fetch=FetchType.EAGER ,cascade = {CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "company")
    public List<Course> course;

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", locatedCountry='" + locatedCountry + '\'' +
                ", course=" + course +
                '}';
    }
}

