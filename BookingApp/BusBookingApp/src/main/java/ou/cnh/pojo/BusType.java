/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author zedmo
 */
@Entity
@Table(name = "bus_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BusType.findAll", query = "SELECT b FROM BusType b"),
    @NamedQuery(name = "BusType.findById", query = "SELECT b FROM BusType b WHERE b.id = :id"),
    @NamedQuery(name = "BusType.findByName", query = "SELECT b FROM BusType b WHERE b.name = :name"),
    @NamedQuery(name = "BusType.findByTypeCost", query = "SELECT b FROM BusType b WHERE b.typeCost = :typeCost")})
public class BusType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @NotNull(message = "{busType.name.notNull}")
    @Size(max = 30, min = 5, message = "{busType.name.lengthErr}")
    @Column(name = "name")
    private String name;
    @NotNull(message = "{busType.typeCost.notNull}")
    @Max(value=5, message = "{busType.typeCost.max}")  @Min(value=1, message = "{busType.typeCost.min}")//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "type_cost")
    private Double typeCost;
    @JsonIgnore
    @OneToMany(mappedBy = "busTypeId")
    private Set<Bus> busSet;

    public BusType() {
    }

    public BusType(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTypeCost() {
        return typeCost;
    }

    public void setTypeCost(Double typeCost) {
        this.typeCost = typeCost;
    }

    @XmlTransient
    public Set<Bus> getBusSet() {
        return busSet;
    }

    public void setBusSet(Set<Bus> busSet) {
        this.busSet = busSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BusType)) {
            return false;
        }
        BusType other = (BusType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ou.cnh.pojo.BusType[ id=" + id + " ]";
    }
    
}
