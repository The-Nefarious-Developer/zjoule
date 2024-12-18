package com.developer.nefarious.zjoule.plugin.models;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

/**
 * The {@code AccessToken} class represents an OAuth2 access token with its
 * related details. This class includes fields for storing the token itself, its
 * type, expiration information, and additional metadata. The {@code receivedAt}
 * field is used to track the time when the token was generated or received,
 * which is important for determining its validity.
 *
 * <p>
 * This class also provides utility methods to check whether the token is valid,
 * based on its expiration time.
 * </p>
 */
public class AccessToken {

	@SerializedName("access_token")
	private String accessToken;

	@SerializedName("token_type")
	private String tokenType;

	@SerializedName("expires_in")
	private long expiresIn;

	@SerializedName("scope")
	private String scope;

	@SerializedName("jti")
	private String jti;

	// Additional field to store the time the token is received
	private Date receivedAt;

	/**
	 * Default constructor that initializes the {@code receivedAt} field to the
	 * current time.
	 * <p>
	 * This is used to track when the token was received so that its validity can be
	 * determined later based on the {@code expiresIn} value.
	 * </p>
	 */
	public AccessToken() {
		this.receivedAt = new Date();
	}

	public String getAccessToken() {
		return accessToken;
	}

	public long getExpiresIn() {
		return expiresIn;
	}

	public String getJti() {
		return jti;
	}

	public Date getReceivedAt() {
		return receivedAt;
	}

	public String getScope() {
		return scope;
	}

	public String getTokenType() {
		return tokenType;
	}

	/**
	 * Checks if the access token is still valid based on the {@code expiresIn} and
	 * {@code receivedAt} values.
	 *
	 * @return {@code true} if the token is still valid; {@code false} otherwise.
	 */
	public Boolean isValid() {
		if (receivedAt == null) {
			return false;
		}
		long currentTime = new Date().getTime();
		// CHECKSTYLE:OFF
		// expiresIn is in seconds, convert to milliseconds
		long expirationTime = receivedAt.getTime() + (expiresIn * 1000);
		// CHECKSTYLE:ON
		return currentTime < expirationTime;
	}

	public void setAccessToken(final String accessToken) {
		this.accessToken = accessToken;
	}

	public void setExpiresIn(final long expiresIn) {
		this.expiresIn = expiresIn;
	}

	public void setJti(final String jti) {
		this.jti = jti;
	}

	public void setReceivedAt(final Date receivedAt) {
		this.receivedAt = receivedAt;
	}

	public void setScope(final String scope) {
		this.scope = scope;
	}

	public void setTokenType(final String tokenType) {
		this.tokenType = tokenType;
	}

}