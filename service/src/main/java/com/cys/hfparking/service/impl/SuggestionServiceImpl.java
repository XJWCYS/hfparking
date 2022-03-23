package com.cys.hfparking.service.impl;

import com.cys.hfparking.dao.SuggestionMapper;
import com.cys.hfparking.entity.Parking;
import com.cys.hfparking.entity.Suggestion;
import com.cys.hfparking.entity.SuggestionVO;
import com.cys.hfparking.service.SuggestionService;
import com.cys.hfparking.utils.PageHelper;
import com.cys.hfparking.vo.ResStatus;
import com.cys.hfparking.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class SuggestionServiceImpl implements SuggestionService {
    @Autowired
    private SuggestionMapper suggestionMapper;
    private int limit = 5;
    @Override
    public ResultVO findAllSuggestion(int pageNum) {
        int start = (pageNum-1)*limit;
        List<SuggestionVO> allSuggestion = suggestionMapper.findAllSuggestion(start, limit);
        Example example = new Example(Suggestion.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("suggestionLevel",1);
        int count = suggestionMapper.selectCountByExample(example);
        int pageCount = count%limit==0 ? count/limit:count/limit+1;
        PageHelper<SuggestionVO> suggestionPageHelper = new PageHelper<>(count,pageCount,allSuggestion);
        return new ResultVO(ResStatus.OK,"success",suggestionPageHelper);
    }

    @Override
    public ResultVO findSuggestionNoReply(int pageNum) {
        int start = (pageNum-1)*limit;
        List<SuggestionVO> allSuggestion = suggestionMapper.findSuggestionNoReply(start, limit);
        Example example = new Example(Suggestion.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("suggestionLevel",1);
        criteria.andEqualTo("status",0);
        int count = suggestionMapper.selectCountByExample(example);
        int pageCount = count%limit==0 ? count/limit:count/limit+1;
        PageHelper<SuggestionVO> suggestionPageHelper = new PageHelper<>(count,pageCount,allSuggestion);
        return new ResultVO(ResStatus.OK,"success",suggestionPageHelper);
    }

    @Override
    public ResultVO findAllSuggestionByUid(int uid) {

        List<SuggestionVO> allSuggestion = suggestionMapper.findSuggestionByUid(uid);
        Example example = new Example(Suggestion.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uid",uid);
        criteria.andEqualTo("suggestionLevel",1);
        int count = suggestionMapper.selectCountByExample(example);
        PageHelper<SuggestionVO> suggestionPageHelper = new PageHelper<>(count,0,allSuggestion);
        return new ResultVO(ResStatus.OK,"success",suggestionPageHelper);
    }

    @Override
    public ResultVO delSuggestionBySid(int sid) {
        Example example = new Example(Suggestion.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("parentId",sid);
        int j = suggestionMapper.deleteByExample(example);
        int i = suggestionMapper.deleteByPrimaryKey(sid);
        if(i>0){
            return new ResultVO(ResStatus.OK,"删除成功",null);
        }else {
            return new ResultVO(ResStatus.NO,"删除失败",null);
        }
    }

    @Override
    public ResultVO addSuggestion(String suggestionContent, int uid) {
        Suggestion suggestion = new Suggestion();
        suggestion.setSuggestContent(suggestionContent);
        suggestion.setSuggestTime(new Date());
        suggestion.setStatus("0");
        suggestion.setParentId(0);
        suggestion.setSuggestionLevel("1");
        suggestion.setUid(uid);
        int i = suggestionMapper.insertSelective(suggestion);
        if(i>0){
            return new ResultVO(ResStatus.OK,"发布成功",null);
        }else {
            return new ResultVO(ResStatus.NO,"发布失败",null);
        }
    }

    @Override
    public ResultVO addReply(String suggestionContent, int sid, int uid) {
        Suggestion suggestion = new Suggestion();
        suggestion.setSuggestContent(suggestionContent);
        suggestion.setSuggestTime(new Date());

        suggestion.setParentId(sid);
        suggestion.setSuggestionLevel("2");
        suggestion.setUid(uid);
        Suggestion suggestion1 = suggestionMapper.selectByPrimaryKey(sid);
        if(suggestion1.getStatus().equals("0")){
            suggestion1.setStatus("1");
            suggestionMapper.updateByPrimaryKeySelective(suggestion1);
        }
        int i = suggestionMapper.insertSelective(suggestion);
        if(i>0){
            return new ResultVO(ResStatus.OK,"发布成功",null);
        }else {
            return new ResultVO(ResStatus.NO,"发布失败",null);
        }
    }

    @Override
    public ResultVO findSuggestionReply(int uid, int pageNum) {
        int start = (pageNum-1)*limit;
        List<SuggestionVO> suggestions = suggestionMapper.findSuggestionReplyByUid(uid, start, limit);
        Example example = new Example(Suggestion.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("suggestionLevel",2);
        criteria.andEqualTo("uid",uid);
        int count = suggestionMapper.selectCountByExample(example);
        int pageCount = count%limit==0 ? count/limit:count/limit+1;
        PageHelper<SuggestionVO> suggestionPageHelper = new PageHelper<>(count,pageCount,suggestions);
        return new ResultVO(ResStatus.OK,"success",suggestionPageHelper);
    }
}
