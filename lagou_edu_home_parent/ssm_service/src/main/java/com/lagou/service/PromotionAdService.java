package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;

import java.util.List;

public interface PromotionAdService {

    /*
     * 分页查询广告信息
     * */
    public PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionAdVO promotionAdVO);

    /*
     * 新增广告信息
     * */
    public void  savePromotionAd(PromotionAd promotionAd);

    /*
     * 修改广告信息
     * */
    public void updatePromotionAd(PromotionAd promotionAd);

    /*
     * 根据ID查询广告信息
     * */
    public PromotionAd findPromotionAdById(int id);

    /*
     * 修改广告状态
     * */
    public void updatePromotionAdStatus(int id, int status);
}
