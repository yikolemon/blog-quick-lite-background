package com.yikolemon.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author yikolemon
 * @date 2023/8/20 21:43
 * @description
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Page<T> implements Serializable {

    private Integer curPage;

    private Integer totalPage;

    private List<T> data;


}
