package com.cqx.bit;

/**
 * String的bit表示与运算
 */
public class StringBit {

    /**
     * 原始字符串
     */
    private String s;

    /**
     * 字符串的字节数组
     */
    private byte[] bytes;

    /**
     * 字节数组高位第一个1的位置
     */
    private int left1Position;

    public String getS() {
        return s;
    }

    public byte[] getBytes() {
        return bytes;
    }


    public static StringBit valueOf(String str) {
        return new StringBit(str);
    }

    public StringBit(String str) {
        this.s = str;
        this.bytes = s.getBytes();
        this.left1Position = findLeft1PositionInBytes(bytes);
    }


    /**
     * big endian 大端字节序
     *
     * @return
     */
    public String toBinaryString() {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(byteToBit(b));
        }
        return sb.toString();
    }


    /**
     * 是否包含另外一个
     *
     * @param another
     * @return
     */
    public boolean contains(StringBit another) {
        byte[] bytes = another.bytes;
        if (bytes.length > this.bytes.length
                || another.left1Position > this.left1Position) {
            return false;
        }
        for (int i = 1; i <= bytes.length; i++) {
            byte b0 = bytes[bytes.length - i];
            byte b1 = this.bytes[this.bytes.length - i];
            if ((b0 & b1) != b0) {
                return false;
            }
        }
        return true;
    }

    /**
     * char  2个字节
     *
     * @param c
     * @return
     */
    public static String charToBit(char c) {
        StringBuilder sb = new StringBuilder();
        for (int i = 16; i >= 0; i--) {
            int v = 1 << i;
            sb.append((c & v) == v ? "1" : "0");
        }
        return sb.toString();
    }


//  ===================PRIVATE===============


    private static String byteToBit(byte b) {
        StringBuilder sb = new StringBuilder();
        for (int i = 16; i >= 0; i--) {
            int v = 1 << i;
            sb.append((b & v) == v ? "1" : "0");
        }
        return sb.toString();
    }

    private int findLeft1PositionInBytes(byte[] bytes) {
        for (int i = bytes.length - 1; i >= 0; i--) {
            byte b = bytes[i];
            int positiveBitPositionInByte = findLeft1PositionInByte(b);
            if (positiveBitPositionInByte >= 0) {
                return i * 8 + positiveBitPositionInByte;
            }
        }
        return -1;
    }

    private int findLeft1PositionInByte(byte b) {
        for (int i = 8; i >= 0; i--) {
            if ((b & (1 << i)) == (1 << i)) {
                return i;
            }
        }
        return -1;
    }


//============= test ================

    public static void main(String[] args) {
        StringBit bc = StringBit.valueOf("bc");
        StringBit c = StringBit.valueOf("c");
        StringBit a = StringBit.valueOf("a");
        System.out.println(bc.toBinaryString());
        System.out.println(c.toBinaryString());
        System.out.println(a.toBinaryString());
        System.out.println(bc.contains(c));
        System.out.println(a.contains(c));
        System.out.println(a.contains(a));

    }


}
