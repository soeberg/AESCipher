package junit;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Locale;

import org.junit.BeforeClass;
import org.junit.Test;

import Cipher.AESCipher.AESCipher;
import Cipher.AESCipher.KeyExpander;
import Cipher.AESCipher.SBox;
import util.BinUtil;

public class AESCipherTests {
	
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
	public void fillStateTest(){
		int Nr = 0;
		AESCipher ac = new AESCipher(cipherkey, Nr);
		Class[] acarg = new Class[1];
		acarg[0] = byte[].class;
		byte[][] result = new byte[][]{
										{(byte) 0x32,(byte) 0x88,(byte) 0x31,(byte) 0xe0},
										{(byte) 0x43,(byte) 0x5a,(byte) 0x31,(byte) 0x37},
										{(byte) 0xf6,(byte) 0x30,(byte) 0x98,(byte) 0x07},
										{(byte) 0xa8,(byte) 0x8d,(byte) 0xa2,(byte) 0x34}};
		Method method;
		try {
			method = ac.getClass().getDeclaredMethod("fillState", acarg);
			method.setAccessible(true);
			byte[][] state = (byte[][]) method.invoke(ac, input);
			for (int i = 0; i < state.length; i++){
				for(int j = 0; j < state[i].length; j++){
					assertEquals("Current index is: ("+i+","+j+").",result[i][j], state[i][j]);
				}
			}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}
	
	
	/*
	 * Testing af round one.
	 */
	@Test
	public void testRoundOneSubBytes(){
		byte[][] state = new byte[][]{
										{(byte) 0x19,(byte)  0xa0,(byte)  0x9a,(byte)  0xe9},
										{(byte) 0x3d,(byte)  0xf4,(byte)  0xc6,(byte)  0xf8},
										{(byte) 0xe3,(byte)  0xe2,(byte)  0x8d,(byte)  0x48},
										{(byte) 0xbe,(byte)  0x2b,(byte)  0x2a,(byte)  0x08}};
		
		byte[][] result = new byte[][]{
										{(byte) 0xd4,(byte)  0xe0,(byte)  0xb8,(byte)  0x1e},
										{(byte) 0x27,(byte)  0xbf,(byte)  0xb4,(byte)  0x41},
										{(byte) 0x11,(byte)  0x98,(byte)  0x5d,(byte)  0x52},
										{(byte) 0xae,(byte)  0xf1,(byte)  0xe5,(byte)  0x30}};
		int Nr = 0;
		AESCipher ac = new AESCipher(cipherkey, Nr);
		Class[] acarg = new Class[1];
		acarg[0] = byte[][].class;
		Method method;
		try {
			method = ac.getClass().getDeclaredMethod("subBytes", acarg);
			method.setAccessible(true);
			
			
			Object o = method.invoke(ac, state);
			
			for(int i = 0; i<state.length; i++){
				for(int j = 0; j<state[i].length; j++){
					assertEquals("Current index is: ("+i+","+j+").",result[i][j], state[i][j]);
				}
			}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRoundOneShiftRows(){
		int Nr = 0;
		AESCipher ac = new AESCipher(cipherkey, Nr);
		
		byte[][] state = new byte[][]{
									{(byte) 0xd4,(byte)  0xe0,(byte)  0xb8,(byte)  0x1e},
									{(byte) 0x27,(byte)  0xbf,(byte)  0xb4,(byte)  0x41},
									{(byte) 0x11,(byte)  0x98,(byte)  0x5d,(byte)  0x52},
									{(byte) 0xae,(byte)  0xf1,(byte)  0xe5,(byte)  0x30}};
		
		byte[][] result = new byte[][]{
									{(byte) 0xd4,(byte)  0xe0,(byte)  0xb8,(byte)  0x1e},
									{(byte) 0xbf,(byte)  0xb4,(byte)  0x41,(byte)  0x27},
									{(byte) 0x5d,(byte)  0x52,(byte)  0x11,(byte)  0x98},
									{(byte) 0x30,(byte)  0xae,(byte)  0xf1,(byte)  0xe5}};
		ac.shiftRows(state);
		for (int i = 0; i < state.length; i++){
			for(int j = 0; j < state[i].length; j++){
				assertEquals("Current index is: ("+i+","+j+").",BinUtil.integerValue(result[i][j]),BinUtil.integerValue(state[i][j]));
			}
		}
	}
	
	@Test
	public void testRoundOneMixColumn(){
		int Nr = 0;
		AESCipher ac = new AESCipher(cipherkey,Nr);

		byte[][] inputMixColumn = new byte[][]{
										{(byte) 0xd4,(byte)  0xe0,(byte)  0xb8,(byte)  0x1e},
										{(byte) 0xbf,(byte)  0xb4,(byte)  0x41,(byte)  0x27},
										{(byte) 0x5d,(byte)  0x52,(byte)  0x11,(byte)  0x98},
										{(byte) 0x30,(byte)  0xae,(byte)  0xf1,(byte)  0xe5}};
										
		ac.mixColumns(inputMixColumn);
		byte[][] mixColumnResult = new byte[][]{
												{(byte) 0x04,(byte)  0xe0,(byte)  0x48,(byte)  0x28},
												{(byte) 0x66,(byte)  0xcb,(byte)  0xf8,(byte)  0x06},
												{(byte) 0x81,(byte)  0x19,(byte)  0xd3,(byte)  0x26},
												{(byte) 0xe5,(byte)  0x9a,(byte)  0x7a,(byte)  0x4c}};
		
		for(int i = 0; i < inputMixColumn.length; i++){
			for(int j = 0; j < inputMixColumn[i].length; j++){
				assertEquals("Current index is: ("+i+","+j+").", mixColumnResult[i][j],inputMixColumn[i][j]);
			}
		}
	}
	
	@Test
	public void testRoundOneAddRoundKey(){
		int Nr = 10;
		int Nb = 4;
		AESCipher ac = new AESCipher(cipherkey,Nr);
		byte[][] state = new byte[][]{
												{(byte) 0x04,(byte)  0xe0,(byte)  0x48,(byte)  0x28},
												{(byte) 0x66,(byte)  0xcb,(byte)  0xf8,(byte)  0x06},
												{(byte) 0x81,(byte)  0x19,(byte)  0xd3,(byte)  0x26},
												{(byte) 0xe5,(byte)  0x9a,(byte)  0x7a,(byte)  0x4c}};
		byte[][] result = new byte[][]{
										{(byte) 0xa4,(byte)  0x68,(byte)  0x6b,(byte)  0x02},
										{(byte) 0x9c,(byte)  0x9f,(byte)  0x5b,(byte)  0x6a},
										{(byte) 0x7f,(byte)  0x35,(byte)  0xea,(byte)  0x50},
										{(byte) 0xf2,(byte)  0x2b,(byte)  0x43,(byte)  0x49}};
										
		int[] w = new int[Nb*(Nr+1)];
		ke.KeyExpansion(cipherkey, w, 4);
		Class[] acarg = new Class[2];
		acarg[0] = byte[][].class;
		acarg[1] = int[].class;
		Method method;
		byte[][] keyresult = new byte[][]{
			{(byte) 0xa0,(byte)  0x88,(byte)  0x23,(byte)  0x2a},
			{(byte) 0xfa,(byte)  0x54,(byte)  0xa3,(byte)  0x6c},
			{(byte) 0xfe,(byte)  0x2c,(byte)  0x39,(byte)  0x76},
			{(byte) 0x17,(byte)  0xb1,(byte)  0x39,(byte)  0x05}
		};
		int[] keys = Arrays.copyOfRange(w, 4, 8);
		
		state = ac.AddRoundKey(state, Arrays.copyOfRange(w, 4, 8));
		
		for(int i = 0; i < state.length; i++){
			for(int j = 0; j < state[i].length; j++){
				assertEquals("Current index is: ("+i+","+j+").", result[i][j],state[i][j]);
			}
		}
	}
	
	@Test
	public void testRoundTenSubBytes(){
		byte[][] state = new byte[][]{
										{(byte) 0xeb,(byte)  0x59,(byte)  0x8b,(byte)  0x1b},
										{(byte) 0x40,(byte)  0x2e,(byte)  0xa1,(byte)  0xc3},
										{(byte) 0xf2,(byte)  0x38,(byte)  0x13,(byte)  0x42},
										{(byte) 0x1e,(byte)  0x84,(byte)  0xe7,(byte)  0xd2}};
		
		byte[][] result = new byte[][]{
										{(byte) 0xe9,(byte)  0xcb,(byte)  0x3d,(byte)  0xaf},
										{(byte) 0x09,(byte)  0x31,(byte)  0x32,(byte)  0x2e},
										{(byte) 0x89,(byte)  0x07,(byte)  0x7d,(byte)  0x2c},
										{(byte) 0x72,(byte)  0x5f,(byte)  0x94,(byte)  0xb5}};
		int Nr = 0;
		AESCipher ac = new AESCipher(cipherkey, Nr);
		Class[] acarg = new Class[1];
		acarg[0] = byte[][].class;
		Method method;
		try {
			method = ac.getClass().getDeclaredMethod("subBytes", acarg);
			method.setAccessible(true);
			byte[][][] acparam = new byte[][][]{state};
			
			Object o = method.invoke(ac, state);
			
			for(int i = 0; i<state.length; i++){
				for(int j = 0; j<state[i].length; j++){
					assertEquals("Current index is: ("+i+","+j+").",result[i][j], state[i][j]);
				}
			}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testThroughRoundOne(){
		int rounds = 1;
		byte[][] result = new byte[][]{
			{(byte) 0xa4,(byte)  0x68,(byte)  0x6b,(byte)  0x02},
			{(byte) 0x9c,(byte)  0x9f,(byte)  0x5b,(byte)  0x6a},
			{(byte) 0x7f,(byte)  0x35,(byte)  0xea,(byte)  0x50},
			{(byte) 0xf2,(byte)  0x2b,(byte)  0x43,(byte)  0x49}};
		
		AESCipher ac = new AESCipher(cipherkey, rounds);
		byte[][] out = ac.cipher(input);
		
		for(int i = 0; i < out.length; i++){
			for (int j = 0; j < out[i].length; j++){
				assertEquals(result[i][j], out[i][j]);
			}
			System.out.println("");
		}
	}
	
	@Test
	public void testThroughRoundTwo(){
		int rounds = 2;
		byte[][] result = new byte[][]{
			{(byte) 0xaa,(byte)  0x61,(byte)  0x82,(byte)  0x68},
			{(byte) 0x8f,(byte)  0xdd,(byte)  0xd2,(byte)  0x32},
			{(byte) 0x5f,(byte)  0xe3,(byte)  0x4a,(byte)  0x46},
			{(byte) 0x03,(byte)  0xef,(byte)  0xd2,(byte)  0x9a}};
		
		AESCipher ac = new AESCipher(cipherkey, rounds);
		byte[][] out = ac.cipher(input);
		
		for(int i = 0; i < out.length; i++){
			for (int j = 0; j < out[i].length; j++){
				assertEquals(result[i][j], out[i][j]);
			}
		}
	}
	
	@Test
	public void testThroughRoundNine(){
		int rounds = 9;
		byte[][] result = new byte[][]{
			{(byte) 0xeb,(byte)  0x59,(byte)  0x8b,(byte)  0x1b},
			{(byte) 0x40,(byte)  0x2e,(byte)  0xa1,(byte)  0xc3},
			{(byte) 0xf2,(byte)  0x38,(byte)  0x13,(byte)  0x42},
			{(byte) 0x1e,(byte)  0x84,(byte)  0xe7,(byte)  0xd2}};
		
		AESCipher ac = new AESCipher(cipherkey, rounds);
		byte[][] out = ac.cipher(input);
		
		for(int i = 0; i < out.length; i++){
			for (int j = 0; j < out[i].length; j++){
				assertEquals(result[i][j], out[i][j]);
			}
		}
	}
	
	@Test
	public void testCipher(){
		int rounds = 10;
		byte[][] result = new byte[][]{
			{(byte) 0x39,(byte)  0x02,(byte)  0xdc,(byte)  0x19},
			{(byte) 0x25,(byte)  0xdc,(byte)  0x11,(byte)  0x6a},
			{(byte) 0x84,(byte)  0x09,(byte)  0x85,(byte)  0x0b},
			{(byte) 0x1d,(byte)  0xfb,(byte)  0x97,(byte)  0x32}};
		
		AESCipher ac = new AESCipher(cipherkey, rounds);
		byte[][] out = ac.cipher(input);
		
		
		
		for(int i = 0; i < out.length; i++){
			for (int j = 0; j < out[i].length; j++){
				System.out.print(Integer.toHexString(BinUtil.integerValue(out[i][j]))+" ");
				assertEquals(result[i][j], out[i][j]);
			}
			System.out.println("");
		}
	}
}
	