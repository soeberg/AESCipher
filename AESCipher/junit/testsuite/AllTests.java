package testsuite;

import java.util.BitSet;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import Cipher.AESCipher.*;

//@RunWith(Suite.class)
//@SuiteClasses({})
public class AllTests {
	private SBox sBox = new SBox();;
	private KeyExpander keyExpander =new KeyExpander(sBox);;
	
	public AllTests(){}
	
	@BeforeClass
	public static void setup(){
		System.out.println("Initializing test suite");
	}
	@Test
	public void testKeyExpansion(){
		byte[] cipherkey = new byte[]{
										(byte) 0xdf,(byte) 0x12, (byte) 0x11,(byte) 0x00,
										(byte) 0xef,(byte) 0x67, (byte) 0x34,(byte) 0x99,
										(byte) 0xaf,(byte) 0x13, (byte) 0xee,(byte) 0x53,
										(byte) 0x45,(byte) 0x12, (byte) 0xac,(byte) 0x72
									};
		int Nk = 4;
		BitSet[] w = new BitSet[4];
		keyExpander.KeyExpansion(cipherkey, w, Nk);
		for (int i = 0; i < w.length; i++){
			System.out.println(w[i].toString());
		}
	}

}
