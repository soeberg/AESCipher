package Cipher.AESCipher;

import java.math.BigInteger;
import java.util.Arrays;

import util.BinUtil;

public class main {
	
	public static void main(String[] args){
		System.out.println(Integer.toHexString(BinUtil.integerValue(BinUtil.modShift((byte) 0x57, (byte) 0x02, 0x1b))));
		System.out.println(Integer.toHexString(BinUtil.integerValue(BinUtil.modShift((byte) 0x57, (byte) 0x04, 0x1b))));
		System.out.println(Integer.toHexString(BinUtil.integerValue(BinUtil.modShift((byte) 0x57, (byte) 0x08, 0x1b))));
		System.out.println(Integer.toHexString(BinUtil.integerValue(BinUtil.modShift((byte) 0x57, (byte) 0x10, 0x1b))));
		System.out.println(Integer.toHexString(BinUtil.integerValue(BinUtil.modShift((byte) 0x57, (byte) 0x13, 0x1b))));
		System.out.println(Integer.toHexString(BinUtil.integerValue(BinUtil.modShift((byte) 0x57, (byte) 0x83, 0x1b))));
	}
	

}
