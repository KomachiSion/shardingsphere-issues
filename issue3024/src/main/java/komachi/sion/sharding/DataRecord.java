package komachi.sion.sharding;

/**
 * @author : wangzhe
 * @date : Created in 2019-09-11 21:25
 * @description :
 */
public class DataRecord {

    private Long id;

    private byte[] byteData;

    private int type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getByteData() {
        return byteData;
    }

    public void setByteData(byte[] byteData) {
        this.byteData = byteData;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
