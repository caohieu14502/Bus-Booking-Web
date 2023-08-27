/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.pojo;

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
    @Size(max = 30)
    @Column(name = "name")
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "type_cost")
    private Float typeCost;
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

    public Float getTypeCost() {
        return typeCost;
    }

    public void setTypeCost(Float typeCost) {
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
