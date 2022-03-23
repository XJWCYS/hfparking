package com.cys.hfparking.controller;

import com.cys.hfparking.entity.Car;
import com.cys.hfparking.entity.Notice;
import com.cys.hfparking.service.NoticeService;
import com.cys.hfparking.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/notice")
@Api(value = "通知公告管理功能",tags = "通知公告管理")
@CrossOrigin
public class NoticeController {
    @Resource
    private NoticeService noticeService;
    @GetMapping("/new")
    @ApiOperation("查询最新公告接⼝")
    @ApiImplicitParam(dataType = "string",name = "token", value = "token",required = true)
    public ResultVO login(@RequestHeader String token){
        return noticeService.selectNewNotice();
    }
    @GetMapping("/all")
    @ApiOperation("全部公告接⼝")
    public ResultVO findAll(@RequestHeader String token,@RequestParam int pageNum){
        return noticeService.selectAllNotice(pageNum);
    }
    @GetMapping("/keyword")
    @ApiOperation("搜索公告信息接⼝")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string",name = "token", value = "token",required = true),
            @ApiImplicitParam(dataType = "string",name = "author", value = "搜索发布公告作者的姓名"),
            @ApiImplicitParam(dataType = "string",name = "content", value = "搜索公告内容"),
            @ApiImplicitParam(dataType = "int",name = "pageNum", value = "当前页码",required = true)
    })
    public ResultVO selectOwnerByKeyword(@RequestHeader String token,
                                         @RequestParam(value = "author",required = false) String author,
                                         @RequestParam(value = "content",required = false) String content,
                                         @RequestParam(value = "pageNum") int pageNum){
        return noticeService.selectNoticeByKeyword(author,content,pageNum);
    }

    @PostMapping("/add")
    @ApiOperation("通知公告添加接口")
    public ResultVO addCar(@RequestBody Notice notice, @RequestHeader String token){
        return noticeService.addNotice(notice);
    }

    @PostMapping("/del")
    @ApiOperation("通知公告下架接口")
    public ResultVO updateCar(@RequestParam Integer nid, @RequestHeader String token){
        return noticeService.delNoticeByNid(nid);
    }

}
