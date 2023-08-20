import com.yikolemon.SpringBootApplication;
import com.yikolemon.dao.impl.CategoryDaoImpl;
import com.yikolemon.entity.Article;
import com.yikolemon.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author yikolemon
 * @date 2023/8/20 22:24
 * @description
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootApplication.class)
public class CategoryTest {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryDaoImpl categoryDao;

    @Test
    public void test1(){
        List<Article> list = articleService.getList();
        System.out.println(list);
    }

    @Test
    public void test2(){
        categoryDao.getCategoryArticleCount();
    }
}
