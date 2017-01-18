package Cipher.AESCipher;

import java.math.BigInteger;
import java.util.Arrays;

import util.BinUtil;

public class main {
	
	public static void main(String[] args){
		byte[][] a = new byte[][]{
			{(byte) 0xd4,(byte)  0xe0,(byte)  0xb8,(byte)  0x1e},
			{(byte) 0xbf,(byte)  0xb4,(byte)  0x41,(byte)  0x27},
			{(byte) 0x5d,(byte)  0x52,(byte)  0x11,(byte)  0x98},
			{(byte) 0x30,(byte)  0xae,(byte)  0xf1,(byte)  0xe5}};
		for(int i = 0; i<a.length; i++){
			for(int j = 0; j<a[i].length; j++){
				System.out.print(a[i][j]+" ");
			}
			System.out.println("");
		}
		System.out.println("");
		byte[][] b = BinUtil.transformMatrix(a);
		for(int i = 0; i<b.length; i++){
			for(int j = 0; j<b[i].length; j++){
				System.out.print(b[i][j]+" ");
			}
			System.out.println("");
		}
	}
	

}
