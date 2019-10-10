import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import komachi.sion.ShardingBugDemoApplication;
import komachi.sion.entity.ShardingTest;
import komachi.sion.service.ShardingTestService;


@SpringBootTest(classes = {ShardingBugDemoApplication.class})
@RunWith(SpringRunner.class)
public class TempTest {

    @Autowired
    private ShardingTestService shardingTestService;


    @Test
    public void test(){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("产品","相机");
        jsonObject.put("规格","台");
        ShardingTest shardingTest =new ShardingTest();
        shardingTest.setSort(1);
        shardingTest.setAliveFlag(1);
        shardingTest.setTitle("aaaaa");
        shardingTest.setContent(jsonObject.toJSONString());
        shardingTestService.save(shardingTest);
        List<ShardingTest> shardingTestList = shardingTestService.list();
        System.out.println(JSON.toJSONString(shardingTestList));
    }

}
