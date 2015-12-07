package com.yimayhd.membercenter.client.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @table merchant
 * @author hdh
 **/
public class MerchantDO implements Serializable {

    private static final long serialVersionUID = 1L;


    private long id; // 

    private long sellerId; // 

    private String name; // 

    private String partnerId; // 支付宝合作者id

    private String sellerPrivateKey; // 商家公钥（参与生成签名）

    private String alipayPublicKey; // 支付宝公钥（参与生成签名）

    private String sellerAlipayId; // 商家支付宝账号

    private String sellerAppId; // 商家开发者的支付宝应用号，与支付宝签约后自动生成

    private Date gmtCreated; //

    private Date gmtModified; //

    private long extLong_1; //

    private long extLong_2; //

    private String extText_1; //

    private String extText_2; //

    private int type; //


    public void setId(long id){
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setSellerId(long sellerId){
        this.sellerId = sellerId;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPartnerId(String partnerId){
        this.partnerId = partnerId;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setSellerPrivateKey(String sellerPrivateKey){
        this.sellerPrivateKey = sellerPrivateKey;
    }

    public String getSellerPrivateKey() {
        return sellerPrivateKey;
    }

    public void setAlipayPublicKey(String alipayPublicKey){
        this.alipayPublicKey = alipayPublicKey;
    }

    public String getAlipayPublicKey() {
        return alipayPublicKey;
    }

    public void setSellerAlipayId(String sellerAlipayId){
        this.sellerAlipayId = sellerAlipayId;
    }

    public String getSellerAlipayId() {
        return sellerAlipayId;
    }

    public void setSellerAppId(String sellerAppId){
        this.sellerAppId = sellerAppId;
    }

    public String getSellerAppId() {
        return sellerAppId;
    }

    public void setGmtCreated(Date gmtCreated){
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtModified(Date gmtModified){
        this.gmtModified = gmtModified;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public long getExtLong_1() {
        return extLong_1;
    }

    public void setExtLong_1(long extLong_1) {
        this.extLong_1 = extLong_1;
    }

    public long getExtLong_2() {
        return extLong_2;
    }

    public void setExtLong_2(long extLong_2) {
        this.extLong_2 = extLong_2;
    }

    public String getExtText_1() {
        return extText_1;
    }

    public void setExtText_1(String extText_1) {
        this.extText_1 = extText_1;
    }

    public String getExtText_2() {
        return extText_2;
    }

    public void setExtText_2(String extText_2) {
        this.extText_2 = extText_2;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}