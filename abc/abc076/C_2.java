package abc076;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class C_2 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

//		String Sx = "?fb??fb?f?b?";
//		String T = "fb";
		String Sx = sc.nextLine();
		String T = sc.nextLine();

		// マッチングした文字列の中で辞書順最小を取得するためのリスト
		List<String> matchStrs = new ArrayList<>();

		String SxMatch;
		String SxRep;
		Pattern p;
		Matcher m;

		for (int i = Sx.length() - T.length(); i >= 0 ; i--) {
			// Tの長さ分のSxの文字列を生成
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
//				System.out.println(S);
				matchStrs.add(S);
			}
		}

		if (!matchStrs.isEmpty()) {
			// マッチング文字列が見つかった場合は辞書順にソートして最小の文字列を出力
			String[] strs = matchStrs.toArray(new String[matchStrs.size()]);
			Arrays.sort(strs);
//			System.out.println("----------------");
			System.out.println(strs[0]);
		} else {
			// 見つからなかった場合
			System.out.println("UNRESTORABLE");
		}
	}
}