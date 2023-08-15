package com.yikolemon.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 文章实体类
 **/

@Data
@Document(collection = "article") //指定要对应的文档名（表名）
@Accessors(chain = true)
public class Article {
    @Id
    @SerializedName("Id")
    private String id;//文章主键

    @SerializedName("Title")
    private String title; //文章名

    @SerializedName("Content")
    private String content; //文章内容

    @SerializedName("PostDate")
    private Date createTime;

}


