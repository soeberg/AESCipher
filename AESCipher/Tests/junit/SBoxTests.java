package junit;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import Cipher.AESCipher.KeyExpander;
import Cipher.AESCipher.SBox;
import util.BinUtil;

public class SBoxTests {
	
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
	public void getRconiFourTest(){
		int i = 4;
		assertEquals(1,BinUtil.integerValue(BinUtil.integerToByteArray(sbox.getRcon(i/4))[0]));
	}
	
	@Test
	public void getRconiEightTest(){
		int i = 8;
		assertEquals(2,BinUtil.integerValue(BinUtil.integerToByteArray(sbox.getRcon(i/4))[0]));
	}
	
	@Test
	public void getRconiSixteenTest(){
		int i = 16;
		assertEquals(8,BinUtil.integerValue(BinUtil.integerToByteArray(sbox.getRcon(i/4))[0]));
	}
	
	@Test
	public void getRconiTwentyTest(){
		int i = 20;
		assertEquals(0x10,BinUtil.integerValue(BinUtil.integerToByteArray(sbox.getRcon(i/4))[0]));
	}
	
	@Test
	public void getRconiTwentyfourTest(){
		int i = 24;
		assertEquals(0x20,BinUtil.integerValue(BinUtil.integerToByteArray(sbox.getRcon(i/4))[0]));
	}
	
	@Test
	public void getRconiTwentyEightTest(){
		int i = 28;
		assertEquals(0x40,BinUtil.integerValue(BinUtil.integerToByteArray(sbox.getRcon(i/4))[0]));
	}
	
	@Test
	public void getRconiThirtytwoTest(){
		int i = 32;
		assertEquals(0x80,BinUtil.integerValue(BinUtil.integerToByteArray(sbox.getRcon(i/4))[0]));
	}
	
	@Test
	public void getRconiThirtySixTest(){
		int i = 36;
		assertEquals(0x1b,BinUtil.integerValue(BinUtil.integerToByteArray(sbox.getRcon(i/4))[0]));
	}
	
	@Test
	public void getRconiFourtyTest(){
		int i = 40;
		assertEquals(0x36,BinUtil.integerValue(BinUtil.integerToByteArray(sbox.getRcon(i/4))[0]));
	}


}
