package com.dealmacha.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public abstract class AbstractDomain implements IDomain {

    protected String id;

    /* (non-Javadoc)
     * @see com.eelectricals.domain.IDomain#getId()
     */
    @Override
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id", unique = true, nullable = false, length = 100)
    public String getId() {
        return id;
    }

    /* (non-Javadoc)
     * @see com.dealmacha.domain.IDomain#setId(java.lang.String)
     */
    @Override
    public void setId(final String id) {
        this.id = id;
    }

    /* @Override
     @Column(name = "user_created", nullable = false, length = 45)
     public String getUserCreated() {
         return this.userCreated;
     }

     @Override
     public void setUserCreated(String userCreated) {
         this.userCreated = userCreated;
     }

     @Override
     @Column(name = "user_modified", nullable = false, length = 45)
     public String getUserModified() {
         return this.userModified;
     }

     @Override
     public void setUserModified(String userModified) {
         this.userModified = userModified;
     }

     @Override
     @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
     @Column(name = "date_created", length = 19)
     public DateTime getDateCreated() {
         return dateCreated;
     }

     @Override
     public void setDateCreated(DateTime dateCreated) {
         this.dateCreated = dateCreated;
     }

     @Override
     @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
     @Column(name = "date_modified", length = 19)
     public DateTime getDateModified() {
         return dateModified;
     }

     @Override
     public void setDateModified(DateTime dateModified) {
         this.dateModified = dateModified;
     }
    */
}
