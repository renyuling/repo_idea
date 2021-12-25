package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;
    /*
    * 广告分页查询
    * */
    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllAdByPage(PromotionAdVO promotionAdVO) {

        PageInfo<PromotionAd> pageInfo = promotionAdService.findAllPromotionAdByPage(promotionAdVO);
        ResponseResult responseResult = new ResponseResult(true, 200, "广告分页查询成功", pageInfo);
        return responseResult;
    }


    /*
     * 图片上传
     * */
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {

        // 1.判断文件是否为空
        if (file.isEmpty()) {
            throw new RuntimeException();
        }
        // 2.获取项目部署路径
        String realPath = request.getServletContext().getRealPath("/");
        String substring = realPath.substring(0, realPath.indexOf("ssm_web"));

        // 3.获取源文件名
        String originalFilename = file.getOriginalFilename();

        // 4.生成新文件名(当前时间的时间戳+文件的后缀名）
        String newFileName = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));

        // 5.文件上传
        String uploadPath = substring + "upload\\";
        File filePath = new File(uploadPath, newFileName);

        // 如果目录不存在则创建目录
        if(!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录：" + filePath);
        }
        // 图片进行了真正的上传
        file.transferTo(filePath);

        // 6.将文件名和文件路径返回进行响应
        HashMap<String, String> map = new HashMap<>();
        map.put("fileName", newFileName);
        map.put("filePath", "http://localhost:8080/upload/" + newFileName);
        ResponseResult responseResult = new ResponseResult(true, 200, "图片上传成功", map);
        return responseResult;

    }

    /*
    * 新增广告
    * */
    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult  saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd) {

        if (promotionAd.getId() == null) {
            promotionAdService.savePromotionAd(promotionAd);
            ResponseResult responseResult = new ResponseResult(true, 200, "新增广告成功", null);
            return responseResult;
        }
        else {
            promotionAdService.updatePromotionAd(promotionAd);
            ResponseResult responseResult = new ResponseResult(true, 200, "修改广告信息成功", null);
            return responseResult;
        }
    }

    /*
     * 根据ID查询广告信息
     * */
    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(Integer id) {
        PromotionAd promotionAdById = promotionAdService.findPromotionAdById(id);
        ResponseResult responseResult = new ResponseResult(true, 200, "成功根据id查询广告信息", promotionAdById);
        return responseResult;
    }

    /*
    * 修改广告状态
    * */
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(int id, int status) {
        promotionAdService.updatePromotionAdStatus(id, status);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("status", status);
        ResponseResult responseResult = new ResponseResult(true, 200, "成功修改广告状态", map);
        return responseResult;
    }

}
