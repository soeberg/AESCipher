package junit;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

import org.junit.BeforeClass;
import org.junit.Test;

import Cipher.AESCipher.AESCipher;
import Cipher.AESCipher.KeyExpander;
import Cipher.AESCipher.SBox;
import util.BinUtil;

public class AESDecipherTests {

	static SBox sbox;
	static KeyExpander ke;
	static byte[] input;
	static byte[] cipherkey;
	static int[] w;

	@BeforeClass
	public static void setupVariables(){
		sbox = new SBox();
		ke = new KeyExpander(sbox);
		input = new byte[]{	(byte) 0x32,(byte) 0x43,(byte) 0xf6,(byte) 0xa8,
				(byte) 0x88,(byte) 0x5a,(byte) 0x30,(byte) 0x8d,
				(byte) 0x31,(byte) 0x31,(byte) 0x98,(byte) 0xa2,
				(byte) 0xe0,(byte) 0x37,(byte) 0x07,(byte) 0x34
			};
		cipherkey = new byte[]{
				(byte) 0x2b,(byte) 0x7e,(byte) 0x15,(byte) 0x16,
				(byte) 0x28,(byte) 0xae,(byte) 0xd2,(byte) 0xa6,
				(byte) 0xab,(byte) 0xf7,(byte) 0x15,(byte) 0x88,
				(byte) 0x09,(byte) 0xcf,(byte) 0x4f,(byte) 0x3c
				};
		w = new int[4];
	}

	@Test
	public void testInvShiftRow(){
		int Nr = 0;
		AESCipher ac = new AESCipher(cipherkey,Nr);

		byte[][] invInputShiftRow = new byte[][]{
										{(byte) 0xd4,(byte)  0xe0,(byte)  0xb8,(byte)  0x1e},
										{(byte) 0x27,(byte)  0xbf,(byte)  0xb4,(byte)  0x41},
										{(byte) 0x11,(byte)  0x98,(byte)  0x5d,(byte)  0x52},
										{(byte) 0xae,(byte)  0xf1,(byte)  0xe5,(byte)  0x30}};
										
		ac.invShiftRows(invInputShiftRow);
		byte[][] invShiftRowResult = new byte[][]{
										{(byte) 0xd4,(byte)  0xe0,(byte)  0xb8,(byte)  0x1e},
										{(byte) 0x41,(byte)  0x27,(byte)  0xbf,(byte)  0xb4},
										{(byte) 0x5d,(byte)  0x52,(byte)  0x11,(byte)  0x98},
										{(byte) 0xf1,(byte)  0xe5,(byte)  0x30,(byte)  0xae}};
		
		for(int i = 0; i < invInputShiftRow.length; i++){
			for(int j = 0; j < invInputShiftRow[i].length; j++){
				System.out.println(invInputShiftRow[i][j]);
				assertEquals("Current index is: ("+i+","+j+").", invShiftRowResult[i][j],invInputShiftRow[i][j]);
			}
		}
	}
}
