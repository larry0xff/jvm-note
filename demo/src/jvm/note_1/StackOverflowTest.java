package jvm.note_1;

/**
 * 类描述：模拟虚拟机栈内存溢出
 * <p>
 * 配置：-Xss1m
 * <p>
 *     Exception in thread "main" java.lang.StackOverflowError
 *          at jvm.note_1.StackOverflowTest.incr(StackOverflowTest.java:16)
 * 	        at jvm.note_1.StackOverflowTest.incr(StackOverflowTest.java:16)
 * 	        at jvm.note_1.StackOverflowTest.incr(StackOverflowTest.java:16)
 * @author 谢仲东
 * @since 2020/7/12 16:29
 */
public class StackOverflowTest {

    private int num = 0;

    private void incr() {
        num ++;
        incr();
    }
    public static void main(String[] args) {
        new StackOverflowTest().incr();
    }
}