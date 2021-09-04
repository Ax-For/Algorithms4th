package org.study;


import org.junit.jupiter.api.Test;


public class TestDemo {
    public static int gcd(int p, int q)
    {
        if(q == 0)
        {
            return p;
        }
        else
        {
            int r = p % q;
            return gcd(q, r);
        }
    }

    // 增加单元测试
    @Test
    public void testGcd()
    {
        int gcd = gcd(20, 5);
        System.out.println(gcd);
    }
}
