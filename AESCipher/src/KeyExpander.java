import java.util.BitSet;

public class KeyExpander {
	
	public static byte[] KeyExpansion(byte[] key, BitSet[] w, int Nk){
		BitSet temp;
		byte[] t2 = w[0].toByteArray();
		
		int i = 0;
		
		//first Nk keys
		while (i < Nk){
			w[i] = BitSet.valueOf(new byte[]{key[i*4], key[i*4+1], key[i*4+1], key[i*4+2]});
			i++;
		}
		
		i = Nk;
		
		while (i < w.length){
			temp = w[i-1];
			if (i % Nk == 0){
				temp = SubWord(RotWord(temp));
			}
		}
		
		return null;
	}
	
	private static BitSet SubWord(BitSet word){
		byte[] bytes = word.toByteArray();
		byte[] subbytes = new byte[word.length()];
		for (int i = 0; i < bytes.length; i++){
			subbytes[i] = SBox.getbyte(bytes[i]);
		}
		return BitSet.valueOf(subbytes);
	}
	
	private static BitSet RotWord(BitSet word){
		byte[] bytes = word.toByteArray();
		byte[] result = new byte[bytes.length];
		for (int i = 1; i < bytes.length; i++) {
			result[i-1] = bytes[i];
		}
		result[bytes.length-1] = bytes[0];
		return BitSet.valueOf(result);
	}

	private static BitSet InvRotWord(BitSet word) {
		byte[] bytes = word.toByteArray();
		byte[] result = new byte[bytes.length];
		for (int i = 1; i < bytes.length; i++) {
			result[i] = bytes[i-1];
		}
		result[0] = bytes[bytes.length-1];
		return BitSet.valueOf(result);
	}


	
	public BitSet XORRCon(BitSet b1,int i){
		return null;
	}
	

}
