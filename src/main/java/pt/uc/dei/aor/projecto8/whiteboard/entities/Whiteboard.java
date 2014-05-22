/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.aor.projecto8.whiteboard.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Users
 */
@Entity
@Table(name = "whiteboard")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Whiteboard.findAll", query = "SELECT w FROM Whiteboard w"),
    @NamedQuery(name = "Whiteboard.findByWhiteboardId", query = "SELECT w FROM Whiteboard w WHERE w.whiteboardId = :whiteboardId"),
    @NamedQuery(name = "Whiteboard.findByName", query = "SELECT w FROM Whiteboard w WHERE w.name = :name")
})
public class Whiteboard implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "whiteboard_id")
    private Integer whiteboardId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Lob
    @Column(length = 100000)
    private byte[] imagedata;
    @JoinColumn(name = "users_username", referencedColumnName = "username")
    @ManyToOne(optional = false)
    private Users usersUsername;

    public Whiteboard() {
    }

    public Whiteboard(Integer whiteboardId) {
        this.whiteboardId = whiteboardId;
    }

    public Whiteboard(String name, byte[] imagedata, Users usersUsername) {
        this.name = name;
        this.imagedata = imagedata;
        this.usersUsername = usersUsername;
    }
    

    
    public Integer getWhiteboardId() {
        return whiteboardId;
    }

    public void setWhiteboardId(Integer whiteboardId) {
        this.whiteboardId = whiteboardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Users getUsersUsername() {
        return usersUsername;
    }

    public void setUsersUsername(Users usersUsername) {
        this.usersUsername = usersUsername;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (whiteboardId != null ? whiteboardId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Whiteboard)) {
            return false;
        }
        Whiteboard other = (Whiteboard) object;
        if ((this.whiteboardId == null && other.whiteboardId != null) || (this.whiteboardId != null && !this.whiteboardId.equals(other.whiteboardId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pt.uc.dei.aor.projecto8.whiteboard.entities.Whiteboard[ whiteboardId=" + whiteboardId + " ]";
    }

    /**
     * @return the imagedata
     */
    public byte[] getImagedata() {
        return imagedata;
    }

    /**
     * @param imagedata the imagedata to set
     */
    public void setImagedata(byte[] imagedata) {
        this.imagedata = imagedata;
    }

   

}
