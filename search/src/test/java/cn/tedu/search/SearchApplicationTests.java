package cn.tedu.search;

import cn.tedu.search.entity.Item;
import cn.tedu.search.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
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

    // 单查
    @Test
    void getOne(){
        // SpringDataElasticsearch提供了按id查询数据的方法
        // 返回值是一个Optional类型的对象,理解为只能保存一个元素的集合
        // 需要内容时直接调用get方法即可获取其中对象
        Optional<Item> optional = itemRepository.findById(1L);
        Item item = optional.get();
        System.out.println("item="+item);
        log.debug("abcabc");
    }

    // 批量增
    @Test
    void addList(){
        // 实例化一个List对象,用户添加要保存到ES的Item对象
        List<Item> list = new ArrayList<>();
        list.add(new Item(2L,"罗技激光有线办公鼠标","鼠标","罗技",9.9,"/2.jpg"));
        list.add(new Item(3L,"雷蛇机械无线游戏键盘","键盘","雷蛇",262.0,"/3.jpg"));
        list.add(new Item(4L,"微软有线静音办公鼠标","鼠标","微软",190.0,"/4.jpg"));
        list.add(new Item(5L,"罗技机械有线背光键盘","键盘","罗技",236.0,"/5.jpg"));
        itemRepository.saveAll(list);
        log.debug("插入批量数据成功,参数:{}",list);
    }

    // 全查
    @Test
    void getAll(){

        Iterable<Item> items = itemRepository.findAll();
        for (Item item : items) {
            System.out.println(item);

        }
    }

    // 单条件自定义查询
    @Test
    void queryOne(){
        // 查询ES中items索引数据,title字段包含"游戏"分词数据
        Iterable<Item> items = itemRepository.queryItemsByTitleMatches("有线游戏");
        for (Item item : items) {
            log.debug("item:{}",item);

        }
    }

    // 多条件自定义查询
    @Test
    void queryTwo(){
        // 查询ES中,items索引里,title字段包含"游戏",并且品牌是"罗技"的数据
        Iterable<Item> items = itemRepository.queryItemsByTitleMatchesAndBrandMatches("有线游戏","罗技");
        for (Item item : items) {
            log.debug("item:{}",item);

        }
    }

    // 自定义排序查询
    @Test
    void queryOrder(){
        // 查询ES中,items索引里,title字段包含"游戏",并且品牌是"罗技"的数据
        Iterable<Item> items = itemRepository.queryItemsByTitleMatchesOrBrandMatchesOrderByPriceDesc("游戏","罗技");
        for (Item item : items) {
            log.debug("item:{}",item);
        }
    }

    // 自定义分页查询
    @Test
    void queryPage(){
        int page = 1;       // 要查询的页码
        int pageSize=2;     // 每页条数设置
        // PageRequest.of(int page,int pageSize) 其中 page参数表示要查询的页码,0表示第一页,以此类推
        Page<Item> pages = itemRepository.queryItemsByTitleMatchesOrBrandMatchesOrderByPriceDesc("游戏", "罗技", PageRequest.of(page-1, pageSize));
        log.debug(""+pages);
        for (Item item : pages) {
            log.debug("item:{}",item);
        }
        // pages对象包含的分页信息输出
        log.debug("总页数:{}",pages.getTotalPages());
        log.debug("总条数:{}",pages.getTotalElements());
        log.debug("当前页:{}",pages.getNumber()+1); // pages.getNumber()返回0时,表示第一页
        log.debug("每页条数:{}",pages.getSize());
        log.debug("是否是首页:{}",pages.isFirst());
        log.debug("是否是末页:{}",pages.isLast());
    }
}
