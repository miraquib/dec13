package login; /**
 * Created by Aqib on 12/9/2016.
 */


import org.boon.json.annotations.JsonProperty;

public class Person {

    Long id;

    @JsonProperty("first_name")

    private String firstName;

    @JsonProperty("last_name")

    private String lastName;

    public Long getId() {

        return id;

    }

    public void setId(Long id) {

        this.id = id;

    }

    public String getFirstName() {

        return firstName;

    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;

    }

    public String getLastName() {

        return lastName;

    }

    public void setLastName(String lastName) {

        this.lastName = lastName;

    }

}
