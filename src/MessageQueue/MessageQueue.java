package MessageQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Created by yeran on 2016/10/29.
 *
 * ��Ϣ����:�ڴ�ά��������תΪ��������ʹ��BeansTalk����KafKaʵ�֣�
 */
public class MessageQueue {
    /**
     * �������ȼ���0Ϊ��ߣ�LEVELֵԽ�������ȼ�Խ��
     */
    public static final Integer MESSAGE_LEVEL = 0;

    /**
     * ��ͨ��Ϣ����
     */
    private static List<Queue> normalQueue;

    static {
        normalQueue = new ArrayList<>();
    }
}
