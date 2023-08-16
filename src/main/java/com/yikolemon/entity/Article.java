package com.yikolemon.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * 文章实体类
 **/

@Data
@Document(collection = "article") //指定要对应的文档名（表名）
@Accessors(chain = true)
public class Article {
    @Id
    @SerializedName(value = "id",alternate = {"Id"})
    private String id;//文章主键

    @SerializedName(value = "title",alternate = {"Title"})
    private String title; //文章名

    @SerializedName(value = "content",alternate ={"Content"})
    private String content; //文章内容

    @SerializedName(value = "createTime",alternate = {"PostDate"})
    private Date createTime;

    //文章标签
    private List<String> tags;

    //文章分类
    @SerializedName(value = "category")
    private String category;

}


