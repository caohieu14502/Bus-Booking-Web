/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author zedmo
 */
@Entity
@Table(name = "trip")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trip.findAll", query = "SELECT t FROM Trip t"),
    @NamedQuery(name = "Trip.findById", query = "SELECT t FROM Trip t WHERE t.id = :id"),
    @NamedQuery(name = "Trip.findBySetOffDay", query = "SELECT t FROM Trip t WHERE t.setOffDay = :setOffDay"),
    @NamedQuery(name = "Trip.findByHolidayCost", query = "SELECT t FROM Trip t WHERE t.holidayCost = :holidayCost"),
    @NamedQuery(name = "Trip.findByState", query = "SELECT t FROM Trip t WHERE t.state = :state"),
    @NamedQuery(name = "Trip.findByPrice", query = "SELECT t FROM Trip t WHERE t.price = :price"),
    @NamedQuery(name = "Trip.findBySetOffTime", query = "SELECT t FROM Trip t WHERE t.setOffTime = :setOffTime")})
public class Trip implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "set_off_day")
    @Temporal(TemporalType.DATE)
    private Date setOffDay;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "holiday_cost")
    private Float holidayCost;
    @Column(name = "state")
    private Integer state;
    @Column(name = "price")
    private Double price;
    @Column(name = "set_off_time")
    @Temporal(TemporalType.TIME)
    private Date setOffTime;
    @JoinColumn(name = "bus_id", referencedColumnName = "id")
    @ManyToOne
    private Bus busId;
    @JoinColumn(name = "route_id", referencedColumnName = "id")
    @ManyToOne
    private Route routeId;
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    @ManyToOne
    private User driverId;
    @OneToMany(mappedBy = "tripId")
    private Set<Ticket> ticketSet;
    
    @Transient
    private String setOffDayString;
    @Transient
    private String setOffTimeString;

    public Trip() {
    }

    public Trip(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getSetOffDay() {
        return setOffDay;
    }

    public void setSetOffDay(Date setOffDay) {
        this.setOffDay = setOffDay;
    }

    public Float getHolidayCost() {
        return holidayCost;
    }

    public void setHolidayCost(Float holidayCost) {
        this.holidayCost = holidayCost;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getSetOffTime() {
        return setOffTime;
    }

    public void setSetOffTime(Date setOffTime) {
        this.setOffTime = setOffTime;
    }

    public Bus getBusId() {
        return busId;
    }

    public void setBusId(Bus busId) {
        this.busId = busId;
    }

    public Route getRouteId() {
        return routeId;
    }

    public void setRouteId(Route routeId) {
        this.routeId = routeId;
    }

    public User getDriverId() {
        return driverId;
    }

    public void setDriverId(User driverId) {
        this.driverId = driverId;
    }

    @XmlTransient
    public Set<Ticket> getTicketSet() {
        return ticketSet;
    }

    public void setTicketSet(Set<Ticket> ticketSet) {
        this.ticketSet = ticketSet;
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
        if (!(object instanceof Trip)) {
            return false;
        }
        Trip other = (Trip) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ou.cnh.pojo.Trip[ id=" + id + " ]";
    }

    /**
     * @return the setOffDayString
     */
    public String getSetOffDayString() {
        return setOffDayString;
    }

    /**
     * @param setOffDayString the setOffDayString to set
     */
    public void setSetOffDayString(String setOffDayString) {
        this.setOffDayString = setOffDayString;
    }

    /**
     * @return the setOffTimeString
     */
    public String getSetOffTimeString() {
        return setOffTimeString;
    }

    /**
     * @param setOffTimeString the setOffTimeString to set
     */
    public void setSetOffTimeString(String setOffTimeString) {
        this.setOffTimeString = setOffTimeString;
    }
    
}