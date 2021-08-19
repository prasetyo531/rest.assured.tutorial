package test.deserialize;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "limit",
        "page",
        "total_page"
})


public class Pagination {

    @JsonProperty("limit")
    private Integer limit;
    @JsonProperty("page")
    private Integer page;
    @JsonProperty("total_page")
    private Integer totalPage;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("limit")
    public Integer getLimit() {
        return limit;
    }

    @JsonProperty("limit")
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @JsonProperty("page")
    public Integer getPage() {
        return page;
    }

    @JsonProperty("page")
    public void setPage(Integer page) {
        this.page = page;
    }

    @JsonProperty("total_page")
    public Integer getTotalPage() {
        return totalPage;
    }

    @JsonProperty("total_page")
    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
