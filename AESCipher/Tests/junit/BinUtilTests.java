package junit;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import Cipher.AESCipher.KeyExpander;
import Cipher.AESCipher.SBox;
import util.BinUtil;

public class BinUtilTests {
	
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
	public void modShiftTest(){
		assertEquals( 0xae, BinUtil.integerValue(BinUtil.modShift((byte) 0x57, (byte) 0x02)));
		assertEquals( 0x47, BinUtil.integerValue(BinUtil.modShift((byte) 0x57, (byte) 0x04)));
		assertEquals( 0x8e, BinUtil.integerValue(BinUtil.modShift((byte) 0x57, (byte) 0x08)));
		assertEquals( 0x7, BinUtil.integerValue(BinUtil.modShift((byte) 0x57, (byte) 0x10)));
		assertEquals( 0xfe, BinUtil.integerValue(BinUtil.modShift((byte) 0x57, (byte) 0x13)));
		assertEquals( 0xc1, BinUtil.integerValue(BinUtil.modShift((byte) 0x57, (byte) 0x83)));
	}

}
