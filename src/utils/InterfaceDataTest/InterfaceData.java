package utils.InterfaceDataTest;

/**
 * Created by Administrator on 2016/10/8.
 */
public class InterfaceData {

    public static final Boolean SELFTEST_DEVICE = false;

    public static final Boolean SELFTEST_ADVERTISE = false;

    public static final String No_1_2_devicesList = "[" +
            "{\"uToken\":\"18710112131\"," +
            "\"storeAccount\":\"987654321\"," +
            "\"storeName\":\"miao1\"," +
            "\"deviceNum\":10," +
            "\"groupNum\":1}," +
            "{\"uToken\":\"18710112132\"," +
            "\"storeAccount\":\"987654322\"," +
            "\"storeName\":\"miao2\"," +
            "\"deviceNum\":12," +
            "\"groupNum\":2}," +
            "{\"uToken\":\"18710112133\"," +
            "\"storeAccount,\"987654323\"," +
            "\"storeName\",\"miao3\"," +
            "\"deviceNum\":13," +
            "\"groupNum\":3}" +
            "]";

    public static final String No_1_1_advList =
            "{\"pages\":5,\"advlist\":[{\"advUUID\":\"1111\",\"status\":\"WG\",\"pic\":\"1222\",\"logo\":\"1333\", \"BusinessName\":\"1444\", \"beginTime\":\"1475027888000\", \"endTime\":\"1475927888000\", \"touNum\":\"100\", \"withdrawal\":{\"id\":1,\"type\":\"O\",\"once_money\":100,\"name\":\"Test1\"}}," +
                    "{\"advUUID\":\"1111\",\"status\":\"N\",\"pic\":\"1222\",\"logo\":\"1333\", \"BusinessName\":\"1444\", \"beginTime\":\"1475027888000\", \"endTime\":\"1475927888000\", \"touNum\":\"100\", \"withdrawal\":{\"id\":1,\"type\":\"O\",\"once_money\":100,\"name\":\"Test1\"}}" +
                    ",{\"advUUID\":\"1111\",\"status\":\"PM\",\"pic\":\"1222\",\"logo\":\"1333\", \"BusinessName\":\"1444\", \"beginTime\":\"1475027888000\", \"endTime\":\"1475927888000\", \"touNum\":\"100\", \"withdrawal\":{\"id\":1,\"type\":\"O\",\"once_money\":100,\"name\":\"Test1\"}}]}\n";

//    public static final String No_2_1_getWithdrawls = "{[\"withdrawal\":{\"id\":1,\"type\":\"O\",\"once_money\":100,\"name\":\"Test1\"}]," +
//            "[\"withdrawal\":{\"id\":2,\"type\":\"Q\",\"once_money\":200,\"name\":\"Test2\"}]," +
//            "[\"withdrawal\":{\"id\":1,\"type\":\"O\",\"once_money\":100,\"name\":\"Test3\"}]}";
    public static final String No_2_1_getWithdrawls =
        "[{\"withdrawal\":{\"id\":1,\"type\":\"O\",\"once_money\":100,\"name\":\"Test1\"}},\n" +
        "{\"withdrawal\":{\"id\":2,\"type\":\"O\",\"once_money\":100,\"name\":\"Test2\"}},\n" +
        "{\"withdrawal\":{\"id\":1,\"type\":\"O\",\"once_money\":100,\"name\":\"Test3\"}}]";
}
