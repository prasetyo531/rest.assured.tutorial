package test.deserialize;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "username",
        "fullname",
        "email",
        "profile_picture",
        "is_follow",
        "follow_date",
        "follower_count",
        "following_count",
        "review_count",
        "points",
        "is_verified"
})

public class Rows {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("username")
    private String username;
    @JsonProperty("fullname")
    private String fullname;
    @JsonProperty("email")
    private String email;
    @JsonProperty("profile_picture")
    private String profilePicture;
    @JsonProperty("is_follow")
    private Boolean isFollow;
    @JsonProperty("follow_date")
    private String followDate;
    @JsonProperty("follower_count")
    private Integer followerCount;
    @JsonProperty("following_count")
    private Integer followingCount;
    @JsonProperty("review_count")
    private Integer reviewCount;
    @JsonProperty("points")
    private Integer points;
    @JsonProperty("is_verified")
    private Boolean isVerified;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    @JsonProperty("username")
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("fullname")
    public String getFullname() {
        return fullname;
    }

    @JsonProperty("fullname")
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("profile_picture")
    public String getProfilePicture() {
        return profilePicture;
    }

    @JsonProperty("profile_picture")
    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    @JsonProperty("is_follow")
    public Boolean getIsFollow() {
        return isFollow;
    }

    @JsonProperty("is_follow")
    public void setIsFollow(Boolean isFollow) {
        this.isFollow = isFollow;
    }

    @JsonProperty("follow_date")
    public String getFollowDate() {
        return followDate;
    }

    @JsonProperty("follow_date")
    public void setFollowDate(String followDate) {
        this.followDate = followDate;
    }

    @JsonProperty("follower_count")
    public Integer getFollowerCount() {
        return followerCount;
    }

    @JsonProperty("follower_count")
    public void setFollowerCount(Integer followerCount) {
        this.followerCount = followerCount;
    }

    @JsonProperty("following_count")
    public Integer getFollowingCount() {
        return followingCount;
    }

    @JsonProperty("following_count")
    public void setFollowingCount(Integer followingCount) {
        this.followingCount = followingCount;
    }

    @JsonProperty("review_count")
    public Integer getReviewCount() {
        return reviewCount;
    }

    @JsonProperty("review_count")
    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    @JsonProperty("points")
    public Integer getPoints() {
        return points;
    }

    @JsonProperty("points")
    public void setPoints(Integer points) {
        this.points = points;
    }

    @JsonProperty("is_verified")
    public Boolean getIsVerified() {
        return isVerified;
    }

    @JsonProperty("is_verified")
    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
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
