package com.joaojunio_dev.taskHub.data.dto.security;

import java.util.Date;
import java.util.Objects;

public class TokenDTO {

    private String username;
    private Boolean authenticaded;
    private Date created;
    private Date expiration;
    private String accessToken;
    private String refreshToken;

    public TokenDTO() {}

    public TokenDTO(String username, Boolean authenticaded, Date created, Date expiration, String accessToken, String refreshToken) {
        this.username = username;
        this.authenticaded = authenticaded;
        this.created = created;
        this.expiration = expiration;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getAuthenticaded() {
        return authenticaded;
    }

    public void setAuthenticaded(Boolean authenticaded) {
        this.authenticaded = authenticaded;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TokenDTO tokenDTO = (TokenDTO) o;
        return Objects.equals(getUsername(), tokenDTO.getUsername()) && Objects.equals(getAuthenticaded(), tokenDTO.getAuthenticaded()) && Objects.equals(getCreated(), tokenDTO.getCreated()) && Objects.equals(getExpiration(), tokenDTO.getExpiration()) && Objects.equals(getAccessToken(), tokenDTO.getAccessToken()) && Objects.equals(getRefreshToken(), tokenDTO.getRefreshToken());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getAuthenticaded(), getCreated(), getExpiration(), getAccessToken(), getRefreshToken());
    }
}
