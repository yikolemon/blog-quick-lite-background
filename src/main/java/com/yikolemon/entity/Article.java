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
    @SerializedName(value = "tags")
    private List<String> tags;

    //文章分类
    @SerializedName(value = "category")
    private String category;



    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Article)){
            return false;
        }
        Article article=(Article) obj;
        return getEqual(this.id,article.id)
                &&getEqual(this.title,article.title)
                &&getEqual(this.content,article.content)
                &&getEqual(this.createTime,article.createTime)
                &&getEqual(this.tags,article.tags)
                &&getEqual(this.category,article.category);
    }

    private <T> boolean getEqual(T a,T b){
        if ((a!=null&&b!=null)||(a==null&&b==null)){
            return true;
        }else if (a==null||b==null){
            return false;
        }
        return a.equals(b);
    }

}


