package cn.tedu.search.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author Meettry
 * @date 2022/10/28 10:34
 */
@Data
@Accessors(chain = true) // 支持链式Set赋值
@AllArgsConstructor // 自动生成全参构造
@NoArgsConstructor // 自动生成无参构造
// @Document注解标记当前类是对应ES框架的一个实体类
// 属性indexName指定ES中的索引名称,运行时,如果索引不存在,SpringData会自动创建此索引
@Document(indexName = "items")
public class Item implements Serializable {
    // SpringData通过@Id注解标记当前实体类的主键
    @Id
    private Long id;

    // SpringData标记title字段需要支持分词,并自定义分词器
    @Field(type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer ="ik_max_word" )
    private String title;

    // Keyword类型是不分词的字符串类型
    @Field(type = FieldType.Keyword)
    private String category;

    @Field(type = FieldType.Keyword)
    private String brand;

    @Field(type = FieldType.Double)
    private Double price;

    // imgPath是图片路径,不会是搜索条件,因此最好不创建索引,尽管不创建索引,但是ES依旧会保存数据
    // 设置index=false ->不创建索引,节省空间
    @Field(type = FieldType.Keyword,index = false)
    private String imgPath;


}
