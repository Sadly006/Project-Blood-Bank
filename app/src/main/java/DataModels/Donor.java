package DataModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Donor {

@SerializedName("name")
@Expose
private String name;
@SerializedName("contact")
@Expose
private String contact;
@SerializedName("location")
@Expose
private String location;

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getContact() {
return contact;
}

public void setContact(String contact) {
this.contact = contact;
}
public String getLocation() {
return location;
}

public void setLocation(String location) {
this.location = location;
}

}