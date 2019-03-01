package newland.rpc.servicebean;

/**
 * @Auther: allanyang
 * @Date: 2019/2/22 16:42
 * @Description:
 */
public class CalculateImpl implements Calculate{

    @Override
    public int add(int a, int b) {
        return a + b;
    }
}