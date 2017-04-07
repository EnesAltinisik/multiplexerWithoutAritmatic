import java.util.LinkedList;
import java.util.Iterator;

public class multiplerWithoutAritmetics {
	LinkedList<Character> ilkSayiChar;
	LinkedList<Character> ikinciSayiChar;
	boolean elde = false;

	LinkedList<Boolean> adimIcinCarpmaSonucu = new LinkedList<>();
	LinkedList<Boolean> sonuc = new LinkedList<>();
	LinkedList<Boolean> sonucTemp = new LinkedList<>();
	LinkedList<Boolean> neKadarSagaKayacaginiTutar = new LinkedList<>();

	public int Mult(int sayi1, int sayi2) {
		int rakam1;
		this.initialization(sayi1, sayi2);
		for (Iterator<Character> iteratorIlkSayi = ilkSayiChar.iterator(); iteratorIlkSayi.hasNext();) {
			char c1 = (char) iteratorIlkSayi.next();
			rakam1 = Integer.parseInt(String.valueOf(c1));
			adimIcinCarpmaSonucu.removeAll(adimIcinCarpmaSonucu);
			sonucTemp.removeAll(sonucTemp);

			this.adimIcinCarpmaSonucuOlustur(rakam1);

			this.adimSayisiOlustur();

			if (sonuc.size() == 0)
				sonuc.addAll(adimIcinCarpmaSonucu);
			else {
				this.oAnaKadarkiSonuc();
			}
		}

		return this.charListToInt();
	}

	public void initialization(int ilkSayi, int ikinciSayi) {
		String ilk = Integer.toBinaryString(ilkSayi);
		String iki = Integer.toBinaryString(ikinciSayi);

		char[] charsİlk = ilk.toCharArray();
		ilkSayiChar = reverse(charsİlk);
		char[] charsİki = iki.toCharArray();
		ikinciSayiChar = reverse(charsİki);
	}

	public int charListToInt() {
		StringBuilder sonucBinaryBuilder = new StringBuilder();
		for (Iterator<Boolean> iterator = sonuc.iterator(); iterator.hasNext();) {
			char c = (boolean) iterator.next() ? '1' : '0';
			sonucBinaryBuilder.append(c);
		}
		String sonucBinary = new StringBuilder(sonucBinaryBuilder.toString()).reverse().toString();
		return Integer.parseInt(sonucBinary, 2);
	}

	public void oAnaKadarkiSonuc() {
		this.carpmaSonucunuKaydir();
		Iterator<Boolean> iteratorSonuc = sonuc.iterator();
		for (Iterator<Boolean> iteratorTemp = adimIcinCarpmaSonucu.iterator(); iteratorTemp.hasNext();) {
			boolean bit1 = (boolean) iteratorTemp.next();
			this.bitIcinIslem(bit1, iteratorSonuc);
		}
		sonucTemp.addLast(elde);
		sonuc.removeAll(sonuc);
		sonuc.addAll(sonucTemp);
		elde = false;

	}

	public void bitIcinIslem(boolean bit1, Iterator<Boolean> iteratorSonuc) {
		if (iteratorSonuc.hasNext()) {
			boolean bit2 = (boolean) iteratorSonuc.next();
			this.ikiBitVarken(bit1, bit2);
		} else {
			this.tekBitVarken(bit1);
		}

	}

	public void tekBitVarken(boolean bit1) {
		if (bit1 && elde) {
			elde = false;
			sonucTemp.addLast(false);
			sonucTemp.addLast(true);
		} else {
			elde = false;
			sonucTemp.addLast(bit1 || elde);
		}
	}

	public void ikiBitVarken(boolean bit1, boolean bit2) {

		if (elde) {
			if (bit1 && bit2) {
				elde = true;
				sonucTemp.addLast(true);
			} else if (bit1 || bit2) {
				elde = true;
				sonucTemp.addLast(false);
			} else {
				elde = false;
				sonucTemp.addLast(true);
			}
		} else {
			if (bit1 && bit2) {
				elde = true;
				sonucTemp.addLast(false);
			} else {
				elde = false;
				sonucTemp.addLast(bit1 || bit2);
			}

		}

	}

	public void carpmaSonucunuKaydir() {

		for (; adimIcinCarpmaSonucu.size() < neKadarSagaKayacaginiTutar.size();) {
			adimIcinCarpmaSonucu.addFirst(false);
		}
	}

	public void adimSayisiOlustur() {
		if (neKadarSagaKayacaginiTutar.size() == 0)
			neKadarSagaKayacaginiTutar.addAll(adimIcinCarpmaSonucu);
		else {
			neKadarSagaKayacaginiTutar.addLast(true);
		}
	}

	public void adimIcinCarpmaSonucuOlustur(int rakam1) {
		int rakam2;
		for (Iterator<Character> iteratorIkinciSayi = ikinciSayiChar.iterator(); iteratorIkinciSayi.hasNext();) {
			char c2 = (char) iteratorIkinciSayi.next();
			rakam2 = Integer.parseInt(String.valueOf(c2));
			if (rakam1 == 1 && rakam2 == 1) {
				adimIcinCarpmaSonucu.addLast(true);
			} else {
				adimIcinCarpmaSonucu.addLast(false);
			}
		}
	}

	public static LinkedList<Character> reverse(char[] cs) {
		LinkedList<Character> ret = new LinkedList<>();
		for (char c : cs) {
			ret.addFirst(c);
		}
		return ret;
	}
}
