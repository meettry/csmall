package cn.tedu.search;

import cn.tedu.search.entity.Item;
import cn.tedu.search.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@Slf4j
@SpringBootTest
class SearchApplicationTests {
    @Autowired
    private ItemRepository itemRepository;
    @Test
    void contextLoads() {
    }

    @Test
    void addOne(){
        // 实例化Item对象
        Item item = new Item().setId(1L)
                .setTitle("罗技激光鼠标")
                .setCategory("鼠标")
                .setBrand("罗技")
                .setPrice(188.0)
                .setImgPath("/1.jpg");
        itemRepository.save(item);
        log.debug("ok");
    }

    //单查
    @Test
    void getOne(){
        // SpringDataElasticsearch提供了按id查询数据的方法
        // 返回值是一个Optional类型的对象,理解为只能保存一个元素的集合
        // 需要内容时直接调用get方法即可获取其中对象
        Optional<Item> optional = itemRepository.findById(1L);
        Item item = optional.get();
        System.out.println("item="+item);


    }
}
