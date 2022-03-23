package com.cys.hfparking.controller;

import com.cys.hfparking.service.SuggestionService;
import com.cys.hfparking.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/suggestion")
@Api(value = "建议接口",tags = "建议管理")
@CrossOrigin
public class SuggestionController {
    @Resource
    private SuggestionService suggestionService;
    @GetMapping("/all")
    @ApiOperation("建议查询接⼝")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string",name = "token", value = "token",required = true),
            @ApiImplicitParam(dataType = "int",name = "pageNum", value = "当前页码",required = true)
    })
    public ResultVO findAllSuggestion(@RequestHeader String token,@RequestParam int pageNum){
        return suggestionService.findAllSuggestion(pageNum);
    }
    @GetMapping("/uid")
    @ApiOperation("建议查询接⼝")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string",name = "token", value = "token",required = true),
            @ApiImplicitParam(dataType = "int",name = "uid", value = "用户id",required = true)
    })
    public ResultVO findAllSuggestionByUid(@RequestHeader String token,@RequestParam int uid){
        return suggestionService.findAllSuggestionByUid(uid);
    }
    @DeleteMapping("/delSuggestion")
    @ApiOperation("建议查询接⼝")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string",name = "token", value = "token",required = true),
            @ApiImplicitParam(dataType = "int",name = "sid", value = "建议id",required = true)
    })
    public ResultVO delSuggestionBySid(@RequestHeader String token,@RequestParam int sid){
        return suggestionService.delSuggestionBySid(sid);
    }
    @PostMapping("/addSuggestion")
    @ApiOperation("建议添加接⼝")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string",name = "token", value = "token",required = true),
            @ApiImplicitParam(dataType = "string",name = "suggestionContent", value = "建议内容",required = true),
            @ApiImplicitParam(dataType = "int",name = "uid", value = "用户id",required = true)
    })
    public ResultVO addSuggestion(@RequestHeader String token,@RequestParam String suggestionContent,@RequestParam int uid){
        return suggestionService.addSuggestion(suggestionContent, uid);
    }
    @PostMapping("/addReply")
    @ApiOperation("建议添加接⼝")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string",name = "token", value = "token",required = true),
            @ApiImplicitParam(dataType = "string",name = "suggestionContent", value = "建议内容",required = true),
            @ApiImplicitParam(dataType = "int",name = "sid", value = "建议id",required = true),
            @ApiImplicitParam(dataType = "int",name = "uid", value = "用户id",required = true)
    })
    public ResultVO addReply(@RequestHeader String token,@RequestParam String suggestionContent,
                                  @RequestParam int sid,@RequestParam int uid){
        return suggestionService.addReply(suggestionContent, sid,uid);
    }
    @GetMapping("/allNoReply")
    @ApiOperation("未回复建议查询接⼝")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string",name = "token", value = "token",required = true),
            @ApiImplicitParam(dataType = "int",name = "pageNum", value = "当前页码",required = true)
    })
    public ResultVO findSuggestionNoReply(@RequestHeader String token,@RequestParam int pageNum){
        return suggestionService.findSuggestionNoReply(pageNum);
    }
    @GetMapping("/myReply")
    @ApiOperation("用户本人回复建议查询接⼝")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string",name = "token", value = "token",required = true),
            @ApiImplicitParam(dataType = "int",name = "uid", value = "用户id",required = true),
            @ApiImplicitParam(dataType = "int",name = "pageNum", value = "当前页码",required = true)
    })
    public ResultVO findSuggestionReply(@RequestHeader String token,@RequestParam int uid,@RequestParam int pageNum){
        return suggestionService.findSuggestionReply(uid, pageNum);
    }
}
