package Cipher.AESCipher;
import java.util.BitSet;

public class KeyExpander {
	
	private SBox sbox;
	
	public KeyExpander(SBox sbox){
		this.sbox = sbox;
	}
	
	public void KeyExpansion(byte[] key, byte[][] w, int Nk){
		byte[] temp = new byte[4];
		
		int i = 0;
		
		//first Nk keys
		while (i < Nk){
			w[i] = new byte[]{key[i*4], key[i*4+1], key[i*4+1], key[i*4+2]};
			i++;
		}
		
		i = Nk;
		
		while (i < w.length){
			temp = w[i-1];
			if (i % Nk == 0){
				temp = XORRcon(SubWord(RotWord(temp)),i/Nk);
			} else{
				if(Nk > 6 && i % Nk == 4){
					temp = SubWord(temp);
				}
			}
			w[i] = w[i-Nk].clone();
			// w[i]. xor with temp 
			i++;
		}
	}
	
	private byte[] SubWord(byte[] bytes){
		byte[] subbytes = new byte[bytes.length];
		for (int i = 0; i < bytes.length; i++){
			subbytes[i] = this.sbox.getbyte(bytes[i]);
		}
		return subbytes;
	}

	private BitSet InvSubWord(BitSet word){
		byte[] bytes = word.toByteArray();
		byte[] subbytes = new byte[word.length()];
		for (int i = 0; i < bytes.length; i++){
			subbytes[i] = this.sbox.getByteInv(bytes[i]);
		}
		return BitSet.valueOf(subbytes);
	}
	
	private static byte[] RotWord(byte[] bytes){
		byte[] result = new byte[bytes.length];
		System.arraycopy(bytes, 1, result, 0, bytes.length - 1);
		result[bytes.length-1] = bytes[0];
		return result;
	}

	private static byte[] InvRotWord(byte[] bytes) {
		byte[] result = new byte[bytes.length];
		System.arraycopy(bytes, 0, result, 1, bytes.length - 1);
		result[0] = bytes[bytes.length-1];
		return result;
	}


	
	public byte[] XORRcon(byte[] b1,int i){
		//i++; skal måske tilføjes
		byte[] temp = b1.clone();
		byte[] Rcon = this.sbox.getRcon(i);
		return temp;
	}
	

}
