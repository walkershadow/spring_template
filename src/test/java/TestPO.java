/**
 * @company 杭州信牛网络科技有限公司
 * @copyright Copyright (c) 2015 - 2018
 */

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author 陈旭
 * @version $Id: BasePO, v0.1
 * @company 杭州信牛网络科技有限公司
 * @date 2018年04月18日 10:03 陈旭 Exp $
 */

public class TestPO extends ArrayList {

    /**
     * 主键id
     */
    @Getter
    @Setter
    private Integer id;


    public static void main(String[] args) {
        TestPO testPO=new TestPO();
        testPO.setId(1);
        testPO.add(1);
        System.out.println(JSONObject.toJSONString(testPO));
    }
}
