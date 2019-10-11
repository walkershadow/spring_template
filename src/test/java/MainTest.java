import com.service.biz.DemoBizService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author 陈旭
 * @version $Id: SpringTest, v0.1
 * @company 杭州信牛网络科技有限公司
 * @date 2018年04月12日 17:14 陈旭 Exp $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring.xml"})
public class MainTest {

    @Autowired
    private DemoBizService demoService;

    @Test
    public void test() {
    }


}