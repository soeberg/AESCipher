package junit;

import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


import Cipher.AESCipher.KeyExpander;
import Cipher.AESCipher.SBox;
import util.BinUtil;

//@RunWith(Suite.class)
//@SuiteClasses({})
public class AllTests {
	SBox sbox = new SBox();
	KeyExpander ke = new KeyExpander(sbox);
	
	
	@Test
	public void testKeyExpander(){
		byte[] cipherkey = new byte[]{	(byte) 0x1e,(byte) 0x14,(byte) 0xc4,(byte) 0x54,(byte) 0x00,(byte) 0x92,(byte) 0x47,(byte) 0x99,
										(byte) 0xfb,(byte) 0xc7,(byte) 0xb1,(byte) 0x66,(byte) 0xea,(byte) 0x29,(byte) 0x31,(byte) 0x20,
										(byte) 0x37,(byte) 0x57,(byte) 0xfd,(byte) 0xad,(byte) 0xf4,(byte) 0xde,(byte) 0xa6,(byte) 0xea,
										(byte) 0x89,(byte) 0xbf,(byte) 0x9f,(byte) 0xff,(byte) 0x9f,(byte) 0xaf,(byte) 0xf5,(byte) 0x42
									};
		int[] w = new int[4];
		int Nk = 4;
		ke.KeyExpansion(cipherkey, w, Nk);
		
		for(int i = 0; i < w.length; i++){
			byte[] bytes = BinUtil.integerToByteArray(w[i]);
			System.out.print("{ ");
			for(int j = 0; j < bytes.length; j++){
				System.out.print(Integer.toHexString(BinUtil.integerValue(bytes[j]))+", ");
			}
			System.out.println("}");
		}
	}
}
