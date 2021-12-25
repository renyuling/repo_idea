package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {
    @Autowired
    private CourseContentService courseContentService;
    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId(Integer courseId) {
        List<CourseSection> list = courseContentService.findSectionAndLessonByCourseId(courseId);
        ResponseResult responseResult = new ResponseResult(true, 200, "章节及课程内容查询成功", list);
        return responseResult;
    }

    /*
    * 回显章节对应的课程信息
    * */
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(Integer courseId) {
        Course course = courseContentService.findCourseByCourseId(courseId);
        ResponseResult responseResult = new ResponseResult(true, 200, "查询课程信息成功", course);
        return responseResult;
    }

    /*新增&更新章节信息*/
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection){
        if (courseSection.getId() == null) {
            courseContentService.saveSection(courseSection);
            return new ResponseResult(true, 200, "新增章节成功", null);
        }
        else {
            courseContentService.updateSection(courseSection);
            return new ResponseResult(true, 200, "修改章节成功", null);
        }
    }

    /*
    * 修改章节状态
    * */
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(int id, int status) {
        courseContentService.updateSectionStatus(id, status);

        //数据响应
        HashMap<Object, Object> map = new HashMap<>();
        map.put("status",status);
        ResponseResult responseResult = new ResponseResult(true, 200, "修改章节状态成功", map);
        return responseResult;
    }

    @RequestMapping("/saveLesson")
    public ResponseResult saveLesson(CourseLesson courseLesson) {
        courseContentService.saveLesson(courseLesson);
        ResponseResult responseResult = new ResponseResult(true, 200, "新增课时成功", null);
        return responseResult;

    }
}
