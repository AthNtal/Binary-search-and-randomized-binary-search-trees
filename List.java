
public class List {
	public BookInfo firstBook,lastBook;
	int size=0;
	
	public List(){
		firstBook=lastBook=null;
		size=0;
	}
	
	public void insert(BookInfo book){
		BookInfo b= new BookInfo(book.getIsbn(), book.getCopies());
		if(isEmpty()){
			firstBook=lastBook=b;
		}else{
			lastBook.next=b;
			lastBook=b;
		}
		size++;
	}
	
	public BookInfo remove(){
		BookInfo removed=new BookInfo(firstBook.getIsbn(),firstBook.getCopies());
		if ( firstBook == lastBook )
			firstBook = lastBook = null;
		else
			firstBook = firstBook.next;
		
		size--;
		return removed;
	}
	
	boolean isEmpty(){
		return size==0;
	}

	int getSize(){
		return size;
	}
	
	public void display(){
		if(isEmpty())return;
		BookInfo current=firstBook;
		while(current!=null){
			System.out.println("Book: "+current.getIsbn()+", copies: "+current.getCopies());
			current=current.next;
		}
	}	
		
	public void merging(List list){
		BookInfo[] array=new BookInfo[list.getSize()];
		for(int i=0;i<array.length;i++){
			array[i]=list.remove();
		}
		
		Sort s=new Sort();
		s.mergesort(array, 0, array.length-1);
		for(int i=0;i<array.length;i++){
			list.insert(array[i]);
		}
	}
	
	/* this method checks if there are two or more books with the same isbn
	 * example: if there are two books with the same isbn and they have 100 and
	 * 			50 copies, then the first book will have 150 copies and the 
	 * 			second one will be deleted.
	 */
	public void checking(List list){
		if(isEmpty())return;
		BookInfo current=firstBook;
		while(current!=lastBook){
			if(current.getIsbn()==(current.next).getIsbn()){
				System.out.println("There are two books with the same  ISBN. The number of copies"
						+ " of the second book will be added to those of the first book.");
				current.setCopies(current.getCopies()+(current.next).getCopies());
				(current.next).setIsbn(0);			
			}
			current=current.next;
		}
		merging(list);
		current=firstBook;
		while(current!=null){
			if (current.getIsbn()==0){
				list.remove();
			}
			current=current.next;
		}
	}
	
	public BookInfo find2(int element){
		BookInfo b=firstBook;
		while(b!=null){
			if(element==b.getIsbn()){
				break;
			}
			b=b.next;
		}
		return b;
	}
	
	public boolean found2(int isbn){
		BookInfo node  =find2(isbn);
		if(node==null){
			return false;
		}else{
			return true;
		}
	}
}
