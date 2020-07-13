package oom;

import java.util.ArrayList;
import java.util.UUID;

/**
 * 类描述：
 * -Xmx20M -Xms20M
 * <p>
 *
 * @author 谢仲东
 * @since 2020/7/10 08:50
 */
public class HeapOomTest {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        while (true) {
            list.add(UUID.randomUUID().toString());
        }
    }
}
