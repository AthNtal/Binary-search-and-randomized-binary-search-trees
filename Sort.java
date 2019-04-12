
public class Sort {
	
	public void mergesort(BookInfo[]a, int p,int r){
		if(r<=p)return;
		int m=(r+p)/2;
		mergesort(a,p,m);
		mergesort(a, m+1, r);
		merge(a, p, m, r);
	}
	
	void merge(BookInfo[]a,int p,int m,int r){
		BookInfo[] aux=new BookInfo[a.length];
		int i,j;
		for(i=m+1;i>p;i--){
			aux[i-1]=a[i-1];
		}
		for(j=m;j<r;j++){
			aux[j+1]=a[r+m-j];
		}
		for(int k=p;k<=r;k++){
			if(less(aux[j].getIsbn(),aux[i].getIsbn())){
				a[k]=aux[j--];
			}else{
				a[k]=aux[i++];
			}	
		}
	}
	
	boolean less(int i, int j) {
		return i<j;
	}
}
