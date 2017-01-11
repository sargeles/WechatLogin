package util;

import json.OAuth2AccessToken;
import json.OAuth2AccessTokenAdapter;
import json.UserInfo;
import json.UserInfoGsonAdapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class WechatGsonBuilder {

  public static final GsonBuilder INSTANCE = new GsonBuilder();

  static {
    INSTANCE.disableHtmlEscaping();
    INSTANCE.registerTypeAdapter(OAuth2AccessToken.class, new OAuth2AccessTokenAdapter());
    INSTANCE.registerTypeAdapter(UserInfo.class, new UserInfoGsonAdapter());
  }

  public static Gson create() {
    return INSTANCE.create();
  }

}
