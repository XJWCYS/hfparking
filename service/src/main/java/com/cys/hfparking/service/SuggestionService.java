package com.cys.hfparking.service;

import com.cys.hfparking.vo.ResultVO;

public interface SuggestionService {
    //根据分页查询建议
    ResultVO findAllSuggestion(int pageNum);
    //根据分页查询未回复建议
    ResultVO findSuggestionNoReply(int pageNum);
    //根据分页查询建议
    ResultVO findAllSuggestionByUid(int uid);
    //根据sid删除建议
    ResultVO delSuggestionBySid(int sid);
    //发布建议
    ResultVO addSuggestion(String suggestionContent , int uid);
    //添加回复
    ResultVO addReply(String suggestionContent , int sid ,int uid);
    //根据id分页查询建议
    ResultVO findSuggestionReply(int uid,int pageNum);
}
