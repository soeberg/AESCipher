import java.util.BitSet;

public class KeyExpander {
	
	public static byte[] KeyExpansion(byte[] key, BitSet[] w, int Nk){
		BitSet temp;
		
		
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
		return null;
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
		return BitSet.valueOf(result);
	}


	
	

}
