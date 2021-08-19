package test.pojo1;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.xml.bind.annotation.XmlElement;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JacksonXmlRootElement(localName = "data")
public class Data {

    /*
    1. getter setter
    2. annotations xml
    3. annotations json
     */

    @JsonProperty
    @JacksonXmlProperty(localName = "data")
    private Data data;

    @JsonProperty
    @XmlElement
    private Integer id;

    @JsonProperty
    @XmlElement
    private String email;

    @JsonProperty
    @XmlElement
    private String first_name;

    @JsonProperty
    @XmlElement
    private String last_name;

    @JsonProperty
    @XmlElement
    private String avatar;

    @JsonProperty
    @JacksonXmlProperty(localName = "support")
    private Support support;

    //for setter
    public Data() {

    }

    //https://stackabuse.com/serialize-and-deserialize-xml-in-java-with-jackson/
    public Data(Integer id, String email, String first_name, String last_name, String avatar, Support support) {
        this.id = id;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.avatar = avatar;
        this.support = support;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Support getSupport() {
        return support;
    }

    public void setSupport(Support support) {
        this.support = support;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return id.equals(data.id) && email.equals(data.email) && first_name.equals(data.first_name) && last_name.equals(data.last_name) && avatar.equals(data.avatar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, first_name, last_name, avatar);
    }
}
