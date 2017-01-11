package json;

import java.io.Serializable;

import util.WechatGsonBuilder;


public class OAuth2AccessToken implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5163129444623243321L;

	private String accessToken;

	private int expiresIn = -1;

	private String refreshToken;

	private String openId;

	private String scope;

	private String unionId;

	public String getRefreshToken() {
		return this.refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getOpenId() {
		return this.openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getScope() {
		return this.scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getAccessToken() {
		return this.accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public int getExpiresIn() {
		return this.expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getUnionId() {
		return this.unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public static OAuth2AccessToken fromJson(String json) {
		return WechatGsonBuilder.create().fromJson(json,
				OAuth2AccessToken.class);
	}

	@Override
	public String toString() {
		return "WxMpOAuth2AccessToken{" + "accessToken='" + this.accessToken
				+ '\'' + ", expiresTime=" + this.expiresIn + ", refreshToken='"
				+ this.refreshToken + '\'' + ", openId='" + this.openId + '\''
				+ ", scope='" + this.scope + '\'' + ", unionId='"
				+ this.unionId + '\'' + '}';
	}
}
