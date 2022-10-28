package cn.tedu.search.repository;

import cn.tedu.search.entity.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Meettry
 * @date 2022/10/28 11:29
 */
// @Repository是Spring家族对框架持久层的命名规范
@Repository
public interface ItemRepository extends ElasticsearchRepository<Item,Long> {
    // ItemRepository接口要继承SpringData框架提供的负借口ElasticsearchRepository
    // 一旦继承,当前接口就可以编写ES操作数据的代码了
    // 继承了父接口后,SpringData会根据我们泛型中编写的Item找到对应的索引
    // 会对这个索引自动生成基本的增删改查方法,我们无需再编写
    // ElasticsearchRepository<[要操作的\关联的实体类名称],[实体类主键的类型]>

}
