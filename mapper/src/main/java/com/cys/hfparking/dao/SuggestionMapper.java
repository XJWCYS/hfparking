package com.cys.hfparking.dao;

import com.cys.hfparking.entity.Suggestion;
import com.cys.hfparking.entity.SuggestionVO;
import com.cys.hfparking.general.GeneralDao;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SuggestionMapper extends GeneralDao<Suggestion> {
    //分页查询所有评论以及其回复
    List<SuggestionVO> findAllSuggestion(int start,int limit);
    //根据parentId查询评论回复
    List<SuggestionVO> findSuggestionReplyByParentId(int sid);
    //根据用户id查询所有评论以及其回复
    List<SuggestionVO> findSuggestionByUid(int uid);
    //分页查询未回复评论以及其回复
    List<SuggestionVO> findSuggestionNoReply(int start,int limit);
    //分页查询登陆人员的回复及原建议
    List<SuggestionVO> findSuggestionReplyByUid(int uid,int start,int limit);
    //根据parentId查询建议
    List<SuggestionVO> findSuggestionByParentId(int parentId);
}