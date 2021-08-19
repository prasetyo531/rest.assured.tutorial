package test.pojo1;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import java.util.Objects;

public class Support {

    @JsonProperty
    @XmlElement
    private String url;

    @JsonProperty
    @XmlElement
    private String text;

    //for setter
    public Support() {

    }

    //https://stackabuse.com/serialize-and-deserialize-xml-in-java-with-jackson/
    public Support(String url, String text) {
        this.url = url;
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Support support = (Support) o;
        return url.equals(support.url) && text.equals(support.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, text);
    }
}
