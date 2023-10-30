package ch.puzzle.okr.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
public class Organisation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence_organisation")
    private Long id;

    @NotNull
    @NotBlank
    private String orgName;

    @ManyToMany(mappedBy = "authorizationOrganisation")
    private List<Team> teams;

    @Enumerated(EnumType.STRING)
    @NotNull
    private OrganisationState state;

    public Organisation() {
    }

    private Organisation(Builder builder) {
        id = builder.id;
        setOrgName(builder.orgName);
        setTeams(builder.teams);
        setState(builder.state);
    }

    public Long getId() {
        return id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String name) {
        this.orgName = name;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public OrganisationState getState() {
        return this.state;
    }

    public void setState(OrganisationState state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Organisation that = (Organisation) o;
        return Objects.equals(id, that.id) && Objects.equals(orgName, that.orgName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orgName, teams);
    }

    @Override
    public String toString() {
        return "Organisation{" + "id=" + id + ", orgName='" + orgName + '}';
    }

    public static final class Builder {
        private Long id;
        private String orgName;

        private List<Team> teams;

        private OrganisationState state;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withOrgName(String name) {
            this.orgName = name;
            return this;
        }

        public Builder withState(OrganisationState state) {
            this.state = state;
            return this;
        }

        public Builder withTeams(List<Team> teams) {
            this.teams = teams;
            return this;
        }

        public Organisation build() {
            return new Organisation(this);
        }
    }
}
