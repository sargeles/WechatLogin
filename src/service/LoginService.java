package service;

import config.ConfigStorage;
import json.UserInfo;
import json.OAuth2AccessToken;

public interface LoginService {

	/**
	 * <pre>
	 * 验证消息的确来自微信服务器
	 * 详情请见: http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421135319&token=&lang=zh_CN
	 * </pre>
	 */
	boolean checkSignature(String timestamp, String nonce, String signature);

	/**
	 * 获取access_token, 不强制刷新access_token
	 * 
	 * @see #getAccessToken(boolean)
	 */
	String getAccessToken() throws Exception;

	/**
	 * <pre>
	 * 获取access_token，本方法线程安全
	 * 且在多线程同时刷新时只刷新一次，避免超出2000次/日的调用次数上限
	 * 
	 * 另：本service的所有方法都会在access_token过期是调用此方法
	 * 
	 * 程序员在非必要情况下尽量不要主动调用此方法
	 * 
	 * 详情请见: http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140183&token=&lang=zh_CN
	 * </pre>
	 * 
	 * @param forceRefresh
	 *            强制刷新
	 */
	String getAccessToken(boolean forceRefresh) throws Exception;

	/**
	 * <pre>
	 * 构造第三方使用网站应用授权登录的url
	 * 详情请见: <a href="https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1419316505&token=&lang=zh_CN">网站应用微信登录开发指南</a>
	 * URL格式为：https://open.weixin.qq.com/connect/qrconnect?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect
	 * </pre>
	 * 
	 * @param redirectURI
	 *            用户授权完成后的重定向链接，无需urlencode, 方法内会进行encode
	 * @param scope
	 *            应用授权作用域，拥有多个作用域用逗号（,）分隔，网页应用目前仅填写snsapi_login即可
	 * @param state
	 *            非必填，用于保持请求和回调的状态，授权请求后原样带回给第三方。该参数可用于防止csrf攻击（跨站请求伪造攻击），
	 *            建议第三方带上该参数，可设置为简单的随机数加session进行校验
	 * @return url
	 */
	String buildQrConnectUrl(String redirectURI, String scope, String state);

	/**
	 * <pre>
	 * 构造oauth2授权的url连接
	 * 详情请见: http://mp.weixin.qq.com/wiki/index.php?title=网页授权获取用户基本信息
	 * </pre>
	 * 
	 * @param redirectURI
	 *            用户授权完成后的重定向链接，无需urlencode, 方法内会进行encode
	 * @return url
	 */
	String oauth2buildAuthorizationUrl(String redirectURI, String scope,
			String state);

	/**
	 * <pre>
	 * 用code换取oauth2的access token
	 * 详情请见: http://mp.weixin.qq.com/wiki/index.php?title=网页授权获取用户基本信息
	 * </pre>
	 */
	OAuth2AccessToken oauth2getAccessToken(String code) throws Exception;

	/**
	 * <pre>
	 * 刷新oauth2的access token
	 * </pre>
	 */
	OAuth2AccessToken oauth2refreshAccessToken(String refreshToken)
			throws Exception;

	/**
	 * <pre>
	 * 验证oauth2的access token是否有效
	 * </pre>
	 * 
	 */
	boolean oauth2validateAccessToken(OAuth2AccessToken oAuth2AccessToken);

	/**
	 * <pre>
	 * 用oauth2获取用户信息, 当前面引导授权时的scope是snsapi_userinfo的时候才可以
	 * </pre>
	 * 
	 * @param lang
	 *            zh_CN, zh_TW, en
	 */
	UserInfo oauth2getUserInfo(OAuth2AccessToken oAuth2AccessToken, String lang)
			throws Exception;

	/**
	 * <pre>
	 * 设置当微信系统响应系统繁忙时，要等待多少 retrySleepMillis(ms) * 2^(重试次数 - 1) 再发起重试
	 * 默认：1000ms
	 * </pre>
	 */
	void setRetrySleepMillis(int retrySleepMillis);

	/**
	 * <pre>
	 * 设置当微信系统响应系统繁忙时，最大重试次数
	 * 默认：5次
	 * </pre>
	 */
	void setMaxRetryTimes(int maxRetryTimes);

	/**
	 * 获取WxMpConfigStorage 对象
	 * 
	 * @return WxMpConfigStorage
	 */
	ConfigStorage getWxMpConfigStorage();

}
