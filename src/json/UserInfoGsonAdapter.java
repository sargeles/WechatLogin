package json;

import java.lang.reflect.Type;



import util.GsonHelper;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class UserInfoGsonAdapter implements JsonDeserializer<UserInfo> {

  @Override
  public UserInfo deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    JsonObject o = json.getAsJsonObject();
    UserInfo UserInfo = new UserInfo();
    Integer subscribe = GsonHelper.getInteger(o, "subscribe");
    if (subscribe != null) {
      UserInfo.setSubscribe(!new Integer(0).equals(subscribe));
    }
    UserInfo.setCity(GsonHelper.getString(o, "city"));
    UserInfo.setCountry(GsonHelper.getString(o, "country"));
    UserInfo.setHeadImgUrl(GsonHelper.getString(o, "headimgurl"));
    UserInfo.setLanguage(GsonHelper.getString(o, "language"));
    UserInfo.setNickname(GsonHelper.getString(o, "nickname"));
    UserInfo.setOpenId(GsonHelper.getString(o, "openid"));
    UserInfo.setProvince(GsonHelper.getString(o, "province"));
    UserInfo.setSubscribeTime(GsonHelper.getLong(o, "subscribe_time"));
    UserInfo.setUnionId(GsonHelper.getString(o, "unionid"));
    Integer sexId = GsonHelper.getInteger(o, "sex");
    UserInfo.setRemark(GsonHelper.getString(o, "remark"));
    UserInfo.setGroupId(GsonHelper.getInteger(o, "groupid"));
    UserInfo.setTagIds(GsonHelper.getIntArray(o, "tagid_list"));
    UserInfo.setSexId(sexId);
    if(new Integer(1).equals(sexId)) {
      UserInfo.setSex("ÄÐ");
    } else if (new Integer(2).equals(sexId)) {
      UserInfo.setSex("Å®");
    } else {
      UserInfo.setSex("Î´Öª");
    }
    return UserInfo;
  }

}
