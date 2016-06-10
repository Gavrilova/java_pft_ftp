package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "addressbook")
@XStreamAlias("contact")


public class ContactData {
  @Id
  @Column(name = "id")
  @XStreamOmitField
  private int id = Integer.MAX_VALUE;

  @Column(name = "firstname")
  @Expose
  private String firstname;

  @Column(name = "middlename")
  @Expose
  private String middlename;

  @Column(name = "lastname")
  @Expose
  private String lastname;

  @Column(name = "nickname")
  @Expose
  private String nickname;

  @Column(name = "title")
  @Expose
  private String title;

  @Column(name = "address")
  @Type(type = "text")
  @Expose
  private String address;

  @Column(name = "company")
  @Expose
  private String company;

  @Column(name = "home")
  @Type(type = "text")
  @Expose
  private String homePhone;

  @Column(name = "mobile")
  @Type(type = "text")
  @Expose
  private String mobilePhone;

  @Column(name = "work")
  @Type(type = "text")
  @Expose
  private String workPhone;

  @Transient
  private String allPhones;

  @Column(name = "fax")
  @Type(type = "text")
  @Expose
  private String fax;

  @Column(name = "email")
  @Type(type = "text")
  //@XStreamOmitField
  @Expose
  private String email;

  @Column(name = "email2")
  @Type(type = "text")
  @Expose
  private String email2;

  @Column(name = "email3")
  @Type(type = "text")
  @Expose
  private String email3;

  @Transient
  private String allEmails;

  @Column(name = "homepage")
  @Type(type = "text")
  @Expose
  private String homepage;


  @Column(name = "photo")
  @Type(type = "text")
  @Expose
  private String photo = "";

  @ManyToMany (fetch = FetchType.EAGER ) //из базы данных будет извлекаться как можно больше информации за один заход
  @JoinTable (name = "address_in_groups",
          joinColumns = @JoinColumn(name = "id"),
          inverseJoinColumns = @JoinColumn (name = "group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();


  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withMiddlename(String middlename) {
    this.middlename = middlename;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withNickname(String nickname) {
    this.nickname = nickname;
    return this;
  }

  public ContactData withTitle(String title) {
    this.title = title;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withCompany(String company) {
    this.company = company;
    return this;
  }

  public ContactData withHomePhone(String home) {
    this.homePhone = home;
    return this;
  }

  public ContactData withMobilePhone(String mobile) {
    this.mobilePhone = mobile;
    return this;
  }

  public ContactData withWorkPhone(String work) {
    this.workPhone = work;
    return this;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withFax(String fax) {
    this.fax = fax;
    return this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData withHomepage(String homepage) {
    this.homepage = homepage;
    return this;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public int getId() {
    return id;
  }

  public String getFirstname() {
    if (firstname == null) {
      firstname = "";
    }
    return firstname;
  }

  public String getMiddlename() {
    if (middlename == null) {
      middlename = "";
    }
    return middlename;
  }

  public String getLastname() {
    if (lastname == null) {
      lastname = "";
    }
    return lastname;
  }

  public String getNickname() {
    if (nickname == null) {
      nickname = "";
    }
    return nickname;
  }

  public String getTitle() {
    if (title == null) {
      title = "";
    }
    return title;
  }

  public String getAddress() {
    if (address == null) {
      address = "";
    }
    return address;
  }

  public String getCompany() {
    if (company == null) {
      company = "";
    }
    return company;
  }

  public String getHomePhone() {
    if (homePhone == null) {
      homePhone = "";
    }
    return homePhone;
  }

  public String getMobilePhone() {
    if (mobilePhone == null) {
      mobilePhone = "";
    }
    return mobilePhone;
  }

  public String getWorkPhone() {
    if (workPhone == null) {
      workPhone = "";
    }
    return workPhone;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public String getFax() {
    if (fax == null) {
      fax = "";
    }
    return fax;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public String getEmail() {
    if (email == null) {
      email = "";
    }
    return email;
  }

  public String getEmail2() {
    if (email2 == null) {
      email2 = "";
    }
    return email2;
  }

  public String getEmail3() {
    if (email3 == null) {
      email3 = "";
    }
    return email3;
  }

  public String getHomepage() {

    if (homepage == null) {
      homepage = "";
    }
    return homepage;
  }


  public File getPhoto() {
    if (photo == null) {
      photo = "";
    }
    return new File(photo);
  }

  public Groups getGroups() {
    return new Groups(groups);
  } //множество превращается в объект типа Groups, при этом создается копия

  @Override
  public String toString() {
    return "ContactData{" +
            " id=" + id +
            ", firstname='" + firstname + '\'' +
            ", middlename='" + middlename + '\'' +
            ", lastname='" + lastname + '\'' +
            ", nickname='" + nickname + '\'' +
            ", title='" + title + '\'' +
            ", address='" + address + '\'' +
            ", company='" + company + '\'' +
            ", homePhone='" + homePhone + '\'' +
            ", mobilePhone='" + mobilePhone + '\'' +
            ", workPhone='" + workPhone + '\'' +
            ", allPhones='" + allPhones + '\'' +
            ", fax='" + fax + '\'' +
            ", email='" + email + '\'' +
            ", email2='" + email2 + '\'' +
            ", email3='" + email3 + '\'' +
            ", allEmails='" + allEmails + '\'' +
            ", homepage='" + homepage + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    if (middlename != null ? !middlename.equals(that.middlename) : that.middlename != null) return false;
    if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
    if (nickname != null ? !nickname.equals(that.nickname) : that.nickname != null) return false;
    if (title != null ? !title.equals(that.title) : that.title != null) return false;
    if (address != null ? !address.equals(that.address) : that.address != null) return false;
    if (company != null ? !company.equals(that.company) : that.company != null) return false;
    if (homePhone != null ? !homePhone.equals(that.homePhone) : that.homePhone != null) return false;
    if (mobilePhone != null ? !mobilePhone.equals(that.mobilePhone) : that.mobilePhone != null) return false;
    if (workPhone != null ? !workPhone.equals(that.workPhone) : that.workPhone != null) return false;
    if (allPhones != null ? !allPhones.equals(that.allPhones) : that.allPhones != null) return false;
    if (fax != null ? !fax.equals(that.fax) : that.fax != null) return false;
    if (email != null ? !email.equals(that.email) : that.email != null) return false;
    if (email2 != null ? !email2.equals(that.email2) : that.email2 != null) return false;
    if (email3 != null ? !email3.equals(that.email3) : that.email3 != null) return false;
    if (allEmails != null ? !allEmails.equals(that.allEmails) : that.allEmails != null) return false;
    return homepage != null ? homepage.equals(that.homepage) : that.homepage == null;

  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (middlename != null ? middlename.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
    result = 31 * result + (title != null ? title.hashCode() : 0);
    result = 31 * result + (address != null ? address.hashCode() : 0);
    result = 31 * result + (company != null ? company.hashCode() : 0);
    result = 31 * result + (homePhone != null ? homePhone.hashCode() : 0);
    result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
    result = 31 * result + (workPhone != null ? workPhone.hashCode() : 0);
    result = 31 * result + (allPhones != null ? allPhones.hashCode() : 0);
    result = 31 * result + (fax != null ? fax.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + (email2 != null ? email2.hashCode() : 0);
    result = 31 * result + (email3 != null ? email3.hashCode() : 0);
    result = 31 * result + (allEmails != null ? allEmails.hashCode() : 0);
    result = 31 * result + (homepage != null ? homepage.hashCode() : 0);
    return result;
  }

  public ContactData inGroup(GroupData group) {
    groups.add(group);
    return this;
  }
}
