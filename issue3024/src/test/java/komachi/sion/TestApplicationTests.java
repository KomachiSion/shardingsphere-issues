package komachi.sion;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.UUID;

import komachi.sion.sharding.DataRecord;
import komachi.sion.sharding.SpringUtil;
import komachi.sion.sharding.mapper.DataRecordMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplicationTests {

    @Test
    public void contextLoads() {
    }


    @Test
    public void testBlobData(){


        DataRecordMapper mapper = SpringUtil.getBean(DataRecordMapper.class);
        

        DataRecord record = new DataRecord();
        record.setId(1L);



        byte[] oriArray = new byte[]{
                1,0,99,111,109,46,108,115,46,114,112,99,46,115,101,114,118,101,114,46,82,112,99,82,101,113,117,101,115,-12,49,48,46,48,46,52,46,49,-75,75,74,65,104,115,100,97,107,100,104,97,115,107,-28,115,101,116,68,97,116,-31,1,1,91,76,106,97,118,97,46,108,97,110,103,46,79,98,106,101,99,116,-69,6,9,-64,-39,-86,20,3,115,100,104,98,115,97,104,100,98,115,-31,1,2,99,111,109,46,108,115,46,114,112,99,46,115,101,114,118,101,114,46,82,112,99,82,101,115,112,111,110,115,-27,0,0,0,3,115,107,104,100,98,115,107,100,117,104,97,108,100,115,106,102,100,107,106,97,102,110,97,100,102,-22,10,66,61,-8,-5,108,25,0,0,3,2,0,3,0,0
        };
//        byte[] oriArray = new byte[] {99,111,109};
        for(byte b:oriArray ){
            System.out.print(b+",");
        }
        System.out.println();
        System.out.println(new String(oriArray));

        record.setByteData(oriArray);
        mapper.insert(record);

        record = mapper.selectByPrimaryKey(record.getId());
//        mapper.delete(1L);

        byte[] dbReadArray     = record.getByteData();
        System.out.println("\n");
        System.out.println("equals result : " + Arrays.equals(oriArray,dbReadArray));
        System.out.println("\n");
        for(byte b:dbReadArray ){
            System.out.print(b+",");
        }
        System.out.println();
        System.out.println(new String(dbReadArray));
    }

}
