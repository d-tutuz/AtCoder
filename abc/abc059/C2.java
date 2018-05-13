package abc059;

import java.util.Scanner;


public class C2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[]a =new int[n];
		long[]imos = new long[n];
		int[]acopy = new int[n];
		for(int i=0;i<n;i++){
			a[i]=sc.nextInt();
			acopy[i]=a[i];
		}

		//第一項目が正の場合と負の場合
		//a[0]が0の場合を考える
		long cnt=0;

		if(a[0]==0){
			a[0]++;
			cnt++;
		}
		imos[0]=a[0];
		for(int i=1;i<n;i++){
			imos[i]=imos[i-1]+a[i];

			//積が0なら符号が異なるように+-1
			if(imos[i]*imos[i-1]==0){
				if(imos[i-1]>0){
					a[i]--;
					cnt++;
				}else{
					a[i]++;
					cnt++;
				}
				imos[i]=imos[i-1]+a[i];
			}

			//積が負なら条件を満たしているのでok
			else if(imos[i]*imos[i-1]<0){
			}

			//積が正なら負になるまで変える
			else{
				if(imos[i-1]>0){
					a[i]-=(imos[i]+1);
					cnt+=imos[i]+1;
				}else{
					a[i]+=-imos[i]+1;
					cnt+=-imos[i]+1;
				}
				imos[i]=imos[i-1]+a[i];
			}

		}


		long cnt2=0;
		if(acopy[0]==0){
			acopy[0]--;
			cnt2++;
		}else if(acopy[0]>0){
			cnt2 +=acopy[0]+1;
			acopy[0]=-1;
		}else{
			cnt2+=-acopy[0]+1;
			acopy[0]=1;
		}
		imos[0]=acopy[0];
		for(int i=1;i<n;i++){
			imos[i]=imos[i-1]+acopy[i];

			//積が0なら符号が異なるように+-1
			if(imos[i]*imos[i-1]==0){
				if(imos[i-1]>0){
					acopy[i]--;
					cnt2++;
				}else{
					acopy[i]++;
					cnt2++;
				}
				imos[i]=imos[i-1]+acopy[i];
			}

			//積が負なら条件を満たしているのでok
			else if(imos[i]*imos[i-1]<0){
			}

			//積が正なら負になるまで変える
			else{
				if(imos[i-1]>0){
					acopy[i]-=(imos[i]+1);
					cnt2+=imos[i]+1;
				}else{
					acopy[i]+=-imos[i]+1;
					cnt2+=-imos[i]+1;
				}
				imos[i]=imos[i-1]+acopy[i];
			}

		}


		System.out.println(Math.min(cnt, cnt2));

	}

}
