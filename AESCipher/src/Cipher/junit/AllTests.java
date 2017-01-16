package Cipher.junit;

import java.util.BitSet;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import Cipher.AESCipher.*;

@RunWith(Suite.class)
@SuiteClasses({})
public class AllTests {
	private SBox sBox;
	private KeyExpander keyExpander;
	
	public AllTests(){}
	
	@BeforeClass
	public void setup(){
		this.sBox = new SBox();
		this.keyExpander = new KeyExpander(sBox);
	}
	@Test
	public void testKeyExpansion(){
		byte[] cipherkey = new byte[]{
										(byte) 0xdf,(byte) 0x12, (byte) 0xac,(byte) 0x21,
										(byte) 0xdf,(byte) 0x12, (byte) 0xac,(byte) 0x21,
										(byte) 0xdf,(byte) 0x12, (byte) 0xac,(byte) 0x21,
										(byte) 0xdf,(byte) 0x12, (byte) 0xac,(byte) 0x21
									};
		int Nk = 4;
		BitSet[] w = new BitSet[44];
		keyExpander.KeyExpansion(cipherkey, w, Nk);
		for (int i = 0; i < w.length; i++){
			System.out.println(w[i].toString());
		}
	}

}
