/*
 * FileName: MerchantDO.java
 * Author:   liubb
 * Date:     2016年3月21日 下午7:47:23
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.client.domain.merchant;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.membercenter.client.domain.CertificatesDO;

/**
 * 〈一句话功能简述〉<br> 
 * 〈店铺基本信息〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）    
 * @since [产品/模块版本] （可选）
 */
public class MerchantInfoDO implements Serializable {

    /**
     */
    private static final long serialVersionUID = -5779782919095019435L;
    
    
    //卖家Id
    private long sellerId;
    
    //店铺名称
    private String name;

    //城市code
    private String cityCode;
    
    //城市名称
    private String cityName;
    
    //营业时间
    private String serviceTime;

    //人均消费
    private long avgprice;
    
    //客服电话
    private String serviceTel;
    
    //店铺联系电话
    private String merchantTel;
    
    //店铺图标
    private String icon;
    
    //店铺 店招
    private String merchantBackPic;
    
    //店铺提供服务
    private List<CertificatesDO> certificates;
    
    //店铺地址
    private String  merchantAddress;
    
    //经度
    private double longitude;
    
    //纬度
    private double  latitude;

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }

    public long getAvgprice() {
        return avgprice;
    }

    public void setAvgprice(long avgprice) {
        this.avgprice = avgprice;
    }

    public String getServiceTel() {
        return serviceTel;
    }

    public void setServiceTel(String serviceTel) {
        this.serviceTel = serviceTel;
    }

    public String getMerchantTel() {
        return merchantTel;
    }

    public void setMerchantTel(String merchantTel) {
        this.merchantTel = merchantTel;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMerchantBackPic() {
        return merchantBackPic;
    }

    public void setMerchantBackPic(String merchantBackPic) {
        this.merchantBackPic = merchantBackPic;
    }

    public List<CertificatesDO> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<CertificatesDO> certificates) {
        this.certificates = certificates;
    }

    public String getMerchantAddress() {
        return merchantAddress;
    }

    public void setMerchantAddress(String merchantAddress) {
        this.merchantAddress = merchantAddress;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    
}
