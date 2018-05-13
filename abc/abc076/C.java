package abc076;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class C {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		String Sx = "abc??";
		String T = "bc";
//		String Sx = sc.nextLine();
//		String T = sc.nextLine();

		String SxMatch;
		String SxRep;
		Pattern p;
		Matcher m;

		for (int i = Sx.length() - T.length(); i >= 0 ; i--) {
			// Tの長さ文のSxの文字列を生成
			SxMatch = Sx.substring(i, i + T.length());

			// 文字列?を正規表現[a-z]?に置換する
			SxRep = SxMatch.replaceAll("\\?", "[a-z]");

			// 文字列の頭から順番に正規表現でパターンマッチングする
			p = Pattern.compile(SxRep);
			m = p.matcher(T);

			if (m.find()) {
				// 見つかった場合
				// マッチングした部分文字列についてはTで置換、残りの?についてはaで置換
				String forwardT = Sx.substring(0, i);
				String BehindT = Sx.substring(i+T.length(), Sx.length());
				String S = (forwardT + T + BehindT).replaceAll("\\?", "a");
				System.out.println(S);
				return;
			}
		}
		// 見つからなかった場合
		System.out.println("UNRESTORABLE");
	}

}