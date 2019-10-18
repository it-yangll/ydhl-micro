package com.ydhl.micro.base.weixin.dto;

import com.alibaba.fastjson.JSONObject;
import com.ydhl.micro.api.dto.liteapp.weixin.WxBeanStringSuper;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;


/**
 * @ClassName BindWxDTO
 * @Description 后台获取微信openId绑定账号
 * @Author Ly
 * @Date 2019/5/29 14:13
 * @Version 1.0
 **/
@ApiModel(value = "后台获取微信openId绑定账号请求参数")
@Data
@ToString
public class BeanWxDTO extends WxBeanStringSuper {

    /**
     * 微信用户UserId
     */
    @Data
    @ToString
    public static class UserId extends BeanWxDTO {
        /**
         * 微信用户Id
         */
        private String UserId;
        /**
         * 手机设备号(由企业微信在安装时随机生成，删除重装会改变，升级不受影响)
         */
        private String DeviceId;
        /**
         * 返回码
         */
        private String errcode;
        /**
         * 对返回码的文本描述内容
         */
        private String errmsg;
        /**
         * 成员票据，最大为512字节。
         * scope为snsapi_userinfo或snsapi_privateinfo，且用户在应用可见范围之内时返回此参数。
         * 后续利用该参数可以获取用户信息或敏感信息。
         */
        private String user_ticket;
        /**
         * user_ticket的有效时间（秒），随user_ticket一起返回
         */
        private String expires_in;

    }

    /**
     * 微信用户UserInfo
     */
    @Data
    @ToString
    public static class UserInfo extends BeanWxDTO {

        Integer errcode;        //返回码
        String errmsg;          //对返回码的文本描述内容
        String userid;          //成员UserID。对应管理端的帐号，企业内必须唯一。不区分大小写，长度为1~64个字节
        String name;            //成员名称
        String mobile;          //手机号码，第三方仅通讯录应用可获取
        Integer[] department;   //成员所属部门id列表，仅返回该应用有查看权限的部门id
        Integer[] order;        //部门内的排序值，默认为0。数量必须和department一致，数值越大排序越前面。值范围是[0, 2^32)
        String position;        //职务信息；第三方仅通讯录应用可获取
        String gender;          //性别。0表示未定义，1表示男性，2表示女性
        String email;           //邮箱，第三方仅通讯录应用可获取
        Integer[] is_leader_in_dept;//表示在所在的部门内是否为上级。；第三方仅通讯录应用可获取
        String avatar;          //头像url。注：如果要获取小图将url最后的”/0”改成”/100”即可。第三方仅通讯录应用可获取
        String telephone;       //座机。第三方仅通讯录应用可获取
        Integer enable;         //成员启用状态。1表示启用的成员，0表示被禁用。注意，服务商调用接口不会返回此字段
        String alias;           //别名；第三方仅通讯录应用可获取
        //List extattr;           //扩展属性，第三方仅通讯录应用可获取
        Integer status;         //激活状态: 1=已激活，2=已禁用，4=未激活。已激活代表已激活企业微信或已关注微工作台（原企业号）。未激活代表既未激活企业微信又未关注微工作台（原企业号）。
        String qr_code;         //员工个人二维码，扫描可添加为外部联系人(注意返回的是一个url，可在浏览器上打开该url以展示二维码)；第三方仅通讯录应用可获取
        //List external_profile;  //成员对外属性，字段详情见对外属性；第三方仅通讯录应用可获取
        String external_position;//对外职务，如果设置了该值，则以此作为对外展示的职务，否则以position来展示。
        String address;         //地址

    }

    /**
     * 获取微信UserId
     * @param body
     * @return
     */
    public static UserId getUserIdBody(String body){
        return JSONObject.toJavaObject(JSONObject.parseObject(body),UserId.class);
    }

    /**
     * 获取微信当前用户信息
     * @param body
     * @return
     */
    public static UserInfo getUserInfoBody(String body){
        return JSONObject.toJavaObject(JSONObject.parseObject(body),UserInfo.class);
    }


}
