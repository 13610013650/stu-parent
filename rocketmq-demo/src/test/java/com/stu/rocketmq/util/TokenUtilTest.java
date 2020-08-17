package com.stu.rocketmq.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class TokenUtilTest {

    @Test
    public void main() {
    }

    @Test
    public void testCreateToken() {
        String s = TokenUtil.testCreateToken();
        System.out.println(s);
    }
}