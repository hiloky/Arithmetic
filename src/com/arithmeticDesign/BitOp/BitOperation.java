package com.arithmeticDesign.BitOp;

/**
 * 位运算
 */
public class BitOperation {

    public static void main(String[] args){
        BitOperation bitOperation = new BitOperation();
        /*int x = bitOperation.add(2322222,120243);*/
        //int x = bitOperation.mul(222,-6);

        //int x = bitOperation.div((1 << 31),2 << 20);
        int[] arr = new int[]{1,1,1,1,2,2,2,2,3,3,3,3,5,5,5,5,7};
        int x = bitOperation.getOnceNum( arr,4);
        //bitOperation.printOddNum(new int[]{1,1,3,4,4,7,7});
        System.out.println(x);
    }
    /**
     * 不申请额外变量交换两个整数变量的值
     * ^ 位运算符，二进制位都相同则为0，否则为1
     * @param a
     * @param b
     */
    public void exchangeV(int a,int b){
        a = a^b;
        b = a^b;
        a = a^b;
    }

    /**
     * 不用比较 找出两个数中较大的数
     * @param a
     * @param b
     * @return
     */
    public int getMax1(int a,int b){
        //因为 a-b 可能会溢出，用long
        long c = a - b;
        //判断c的符号 k = 0 则 c >0,即 a>b
        int k = (int)((c >> 63) & 1);

        //将较小的数 *0 ，大的数 *1 即可
        return (a * (k ^ 1) + b * k);
    }

    /**
     * 上面对象转换需要装箱拆箱，影响效率
     * 【思路】 判断 a，b 是否符号相同，相同的话用 int 表示，不同的话只需判断符号正负判断大小
     * @param a
     * @param b
     * @return
     */
    public int getMax2(int a,int b){
        int ai = a >> 31;
        int aif = a & 1; // 1则为负
        int bi = b >> 31;
        int bif = b & 1;

        return 0;
    }

    /**
     * 位运算--加法
     * 进位和无进位的结果和 a + b = a^b + a&b<<1  = a^b^(a&b<<1) + (a^b)&(a&b<<1)<<1 ......,直到 b 的值为0，停止相加
     * 【理解】 把两数相加 看成无进位和进位情况，先得到无进位 a^b, 进位的值 为 a&b<<1,这两个数的和就是 a+b
     * @param a
     * @param b
     * @return
     */
    public int add(int a,int b){
        int c = 0;
        while (b != 0){
            c = a;
            a = a ^ b;
            b = (c & b) << 1;
        }

        return a;
    }

    /**
     * 位运算--减法
     * 和加法类似，a-b = (-b) + a
     * @param a
     * @param b
     * @return
     */
    public int sub(int a,int b){
        return add(add(~b,1),a);
    }

    /**
     * 位运算--乘法
     * 【思路】 将 a*b 的b值可以写成 2的次方的累加和，这样a *b 可以解释为a的进位累加
     * a*b = a*(2^0 * b0 + 2^1 * b1 + 2^2 * b2 +......+2^31 * b31)
     * @param a
     * @param b
     * @return
     */
    public int mul(int a,int b){
        int i = 0;
        int bIndex = 0;
        int res = 0;
        while (b != 0){
            bIndex = b & 1;
            if(bIndex != 0){
                res = add(a << i ,res);
            }
            i++;
            b >>>= 1;
        }

        return res;
    }

    /**
     * 位运算--除法
     * 【思路】 乘法的逆运算，res = a/b => a = b * res => a = b * (2^0 * res0 + 2^1 * res1 +......+ 2^31 * res31)
     * 当 b右移 k位，正好大于等于 a，则表示 res 在 res(k)* 2^k 位置的res(k) = 1，即 2^k 记录,而大于k位置的都为0，因为乘法是根据res的进位来进位值相加的，所以res下标大于k位置的部分都为0
     *  剩下的为 a - b*2^k = b*(2^0 * res0 + ...+2^(k-1) * res(k-1))，一直迭代下去，直到 右边的结果大于等于左边的结果
     *  当存在负数时，转成正数计算,最后判断符号即可，但由于int 类型的值为 -2147483648 ~ 21474836467，当a或者b为最小负数时，转成正数会溢出
     *  1， a = b = Integer.MIN_VALUE,返回1；
     *  2. a > Integer.MIN_VALUE,b > Integer.MIN_VALUE,正常计算
     *  3. a > Integer.MIN_VALUE,b = Integer.MIN_VALUE,返回0；
     *  4. a = Integer.MIN_VALUE,b > Integer.MIN_VALUE 时：
     *     将a + 1，再取绝对值(在divOp方法中)，再计算 res =(a+1)/b +  (a - (a+1)/b * b)/b
     * @param a
     * @param b
     * @return
     */
    public int div(int a,int b){
        if(b == 0){
            throw new RuntimeException("div is zero");
        }
        if(b == Integer.MIN_VALUE && a == Integer.MIN_VALUE){
            return 1;
        }
        else if(b == Integer.MIN_VALUE){
            return 0;
        }
        else if(a == Integer.MIN_VALUE){
            int res = divOp(add(a,1),b);
            return add(res , divOp(add(a ,add(~mul(res,b),1)),b));
        }
        else{
            return divOp(a,b);
        }
    }

    /**
     * 除法运算
     */
    public int divOp(int a,int b){
        int res = 0;
        int signA = (a >> 31) & 1;
        int signB = (b >> 31) & 1;
        if(signA == 1){
            a = add(~a,1);
        }
        if(signB == 1){
            a = add(~b,1);
        }
        while (a > 0){
            int i = -1;
            int c = b;
            if(a < c){
                a = 0;
                continue;
            }
            //当c 往左移最高位超过31位，即符号位为1时，停止，此时int类型的最大除数不会超过c移动的最大位
            while (a >= c && (c>>31 & 1) != 1){
                i++;
                c <<= 1;
            }

            res += (1<<i);
            a = a - b* (1<<i);
        }

        return (signA ^ signB) == 1? add(~res,1):res;
    }

    /**
     * 获取整数的二进制中的1的个数
     * @param n
     */
    public int getOneNum1(int n){
        int count = 0;
        while (n > 0){
            count += n & 1;
            n >>>= 1;
        }

        return count;
    }
    /**
     * 获取整数的二进制中的1的个数
     * @param n
     */
    public int getOneNum2(int n){
        int count = 0;
        while (n > 0){
            n &= (n - 1);//跳过循环0部分的数
                         //或者 n -= n & (~n + 1) ,同理，剪掉存在1部分的数
            count++;
        }

        return count;
    }

    /**
     * 平行算法
     * @param n
     * @return
     */
    public int getOneNum3(int n){
        n = (n & 0x55555555) + ((n >>> 1) & 0x55555555);
        n = (n & 0x33333333) + ((n >>> 2) & 0x33333333);
        n = (n & 0x0f0f0f0f) + ((n >>> 4) & 0x0f0f0f0f);
        n = (n & 0x00ff00ff) + ((n >>> 8) & 0x00ff00ff);
        n = (n & 0x0000ffff) + ((n >>> 16) & 0x0000ffff);

        return n;
    }

    /**
     * 在其他数都出现偶数次的数组中找到出现奇数次的数
     * @param arr
     */
    public void printOddNum(int[] arr){
        int y = 0;
        for(int k : arr){
            y ^= k;
        }
        System.out.println(y);
    }

    /**
     * 有两个数出现奇数次，其他都出现偶数次，打印这两个数
     * 【思路】 循环一遍异或，最后的结果是 a^b != 0; 找到任意的二进制下标为1的位置，跟下标为 1 的数去 ^ ,结果可以得到 a，b = (a^b)^a
     * @param arr
     */
    public void printTwoOdd(int[] arr){
        int c = 0;
        for(int k : arr){
            c ^= k; //c的结果为 a^b
        }

        //获取c的最右边的 1 的结果值
        int sign = c & (~c + 1);
        int a = c; //初始化a
        for (int k : arr){
            if((k & sign) == sign){
                a ^= k;
            }
        }

        int b = c ^ a;
        System.out.print("a="+a+",b="+b);
    }

    /**
     * 在其他数都出现k次的数组中找到只出现一次的数（一个数出现一次，其他数出现k（k>1）次）
     * 时间复杂度O(n),空间复杂度O(1)
     * 【思路】 可以看成k个 k进制的数无进位相加，最后结果一定是出现一次的数
     * @param arr
     * @return
     */
    public int getOnceNum(int[] arr,int k){
        int[] eO = new int[32]; //设置一个k进制的32位的数，且每个位上初始化都为0
        int c = 0;
        //循环遍历数组
        for(int a : arr){
            //每个数都可看成32位的int[32]的数组
            for(int i=0;i<32;i++){
                //计算是否满足k
                c = a >> i & 1;
                eO[i] = (eO[i] + c)%k;
                //System.out.print(eO[i] + " ");
            }
            //System.out.println();
        }

        //将数组arr转为十进制
        int res = 0;
        for(int i=0;i<eO.length;i++){
            res += eO[i] * (1 << i);
        }
        return  res;
    }

}
