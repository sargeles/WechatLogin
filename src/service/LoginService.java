package service;

import config.ConfigStorage;
import json.UserInfo;
import json.OAuth2AccessToken;

public interface LoginService {

	/**
	 * <pre>
	 * ��֤��Ϣ��ȷ����΢�ŷ�����
	 * �������: http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421135319&token=&lang=zh_CN
	 * </pre>
	 */
	boolean checkSignature(String timestamp, String nonce, String signature);

	/**
	 * ��ȡaccess_token, ��ǿ��ˢ��access_token
	 * 
	 * @see #getAccessToken(boolean)
	 */
	String getAccessToken() throws Exception;

	/**
	 * <pre>
	 * ��ȡaccess_token���������̰߳�ȫ
	 * ���ڶ��߳�ͬʱˢ��ʱֻˢ��һ�Σ����ⳬ��2000��/�յĵ��ô�������
	 * 
	 * ����service�����з���������access_token�����ǵ��ô˷���
	 * 
	 * ����Ա�ڷǱ�Ҫ����¾�����Ҫ�������ô˷���
	 * 
	 * �������: http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140183&token=&lang=zh_CN
	 * </pre>
	 * 
	 * @param forceRefresh
	 *            ǿ��ˢ��
	 */
	String getAccessToken(boolean forceRefresh) throws Exception;

	/**
	 * <pre>
	 * ���������ʹ����վӦ����Ȩ��¼��url
	 * �������: <a href="https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1419316505&token=&lang=zh_CN">��վӦ��΢�ŵ�¼����ָ��</a>
	 * URL��ʽΪ��https://open.weixin.qq.com/connect/qrconnect?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect
	 * </pre>
	 * 
	 * @param redirectURI
	 *            �û���Ȩ��ɺ���ض������ӣ�����urlencode, �����ڻ����encode
	 * @param scope
	 *            Ӧ����Ȩ������ӵ�ж���������ö��ţ�,���ָ�����ҳӦ��Ŀǰ����дsnsapi_login����
	 * @param state
	 *            �Ǳ�����ڱ�������ͻص���״̬����Ȩ�����ԭ�����ظ����������ò��������ڷ�ֹcsrf��������վ����α�칥������
	 *            ������������ϸò�����������Ϊ�򵥵��������session����У��
	 * @return url
	 */
	String buildQrConnectUrl(String redirectURI, String scope, String state);

	/**
	 * <pre>
	 * ����oauth2��Ȩ��url����
	 * �������: http://mp.weixin.qq.com/wiki/index.php?title=��ҳ��Ȩ��ȡ�û�������Ϣ
	 * </pre>
	 * 
	 * @param redirectURI
	 *            �û���Ȩ��ɺ���ض������ӣ�����urlencode, �����ڻ����encode
	 * @return url
	 */
	String oauth2buildAuthorizationUrl(String redirectURI, String scope,
			String state);

	/**
	 * <pre>
	 * ��code��ȡoauth2��access token
	 * �������: http://mp.weixin.qq.com/wiki/index.php?title=��ҳ��Ȩ��ȡ�û�������Ϣ
	 * </pre>
	 */
	OAuth2AccessToken oauth2getAccessToken(String code) throws Exception;

	/**
	 * <pre>
	 * ˢ��oauth2��access token
	 * </pre>
	 */
	OAuth2AccessToken oauth2refreshAccessToken(String refreshToken)
			throws Exception;

	/**
	 * <pre>
	 * ��֤oauth2��access token�Ƿ���Ч
	 * </pre>
	 * 
	 */
	boolean oauth2validateAccessToken(OAuth2AccessToken oAuth2AccessToken);

	/**
	 * <pre>
	 * ��oauth2��ȡ�û���Ϣ, ��ǰ��������Ȩʱ��scope��snsapi_userinfo��ʱ��ſ���
	 * </pre>
	 * 
	 * @param lang
	 *            zh_CN, zh_TW, en
	 */
	UserInfo oauth2getUserInfo(OAuth2AccessToken oAuth2AccessToken, String lang)
			throws Exception;

	/**
	 * <pre>
	 * ���õ�΢��ϵͳ��Ӧϵͳ��æʱ��Ҫ�ȴ����� retrySleepMillis(ms) * 2^(���Դ��� - 1) �ٷ�������
	 * Ĭ�ϣ�1000ms
	 * </pre>
	 */
	void setRetrySleepMillis(int retrySleepMillis);

	/**
	 * <pre>
	 * ���õ�΢��ϵͳ��Ӧϵͳ��æʱ��������Դ���
	 * Ĭ�ϣ�5��
	 * </pre>
	 */
	void setMaxRetryTimes(int maxRetryTimes);

	/**
	 * ��ȡWxMpConfigStorage ����
	 * 
	 * @return WxMpConfigStorage
	 */
	ConfigStorage getWxMpConfigStorage();

}
