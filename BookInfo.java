
public class BookInfo {
	//isbn length=[0-9999]
	int isbn;
	int copies;
	BookInfo next;
	public BookInfo(int isbn,int copies){
		this.isbn=isbn;
		this.copies=copies;
	}
	
	int getIsbn(){
		return isbn;
	}
	public void setIsbn(int isbn){
		this.isbn=isbn;
	}
	int getCopies(){
		return copies;
	}
	public void setCopies(int copies){
		this.copies=copies;
	}
}
