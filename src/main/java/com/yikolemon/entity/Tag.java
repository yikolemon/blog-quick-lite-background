package com.yikolemon.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author yikolemon
 * @date 2023/8/20 20:42
 * @description
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Tag {


    public String name;

    public Integer num;

    public List<Article> articleList;

}
