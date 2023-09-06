/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author zedmo
 */
@Entity
@Table(name = "offline_bill")
@PrimaryKeyJoinColumn(name="bill_id") 
public class OfflineBill extends Bill{
    @Column(name = "client_name")
    private String clientName;
    @Column(name = "client_phone_number")
    private String clientPhoneNumber;
    
    public OfflineBill() {
        super();
    }
    
    public OfflineBill(String name, String phone) {
        super();
        this.clientName = name;
        this.clientPhoneNumber = phone;
    }

    /**
     * @return the clientName
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * @param clientName the clientName to set
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * @return the clientPhoneNumber
     */
    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    /**
     * @param clientPhoneNumber the clientPhoneNumber to set
     */
    public void setClientPhoneNumber(String clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }

}
