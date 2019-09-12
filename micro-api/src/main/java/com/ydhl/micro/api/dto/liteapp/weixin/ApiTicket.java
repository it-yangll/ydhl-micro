package com.ydhl.micro.api.dto.liteapp.weixin;

/**
 * @ClassName ApiTicket
 * @Description 微信支付用
 * @Author: zhengew
 * @Date: 2019-6-20 19:41:21
 * @Version: 1.0
 */
public class ApiTicket {
    private String ticket;
    private long expires_in;

    public String getTicket() {
        return ticket;
    }
    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
    public long getExpires_in() {
        return expires_in;
    }
    public void setExpires_in(long expires_in) {
        //原expires_in是有效时长，比如：7200，现改为过期的时间戳
        this.expires_in = System.currentTimeMillis() + (expires_in - 100) * 1000;
    }
}
