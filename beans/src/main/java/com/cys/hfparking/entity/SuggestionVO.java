package com.cys.hfparking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuggestionVO {

    private Integer sid;

    private String suggestContent;

    private Date suggestTime;

    private String status;

    private Integer parentId;

    private String suggestionLevel;

    private Integer uid;

    private Integer isAnonymous;

    private List<Suggestion> suggestions;

    private List<User> users;
}
