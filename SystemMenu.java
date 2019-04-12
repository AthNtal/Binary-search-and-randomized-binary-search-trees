import java.util.*;
public class SystemMenu {

	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);//for integers
		Scanner in2=new Scanner(System.in);//for strings
		boolean flag=false;
		int answer;
		
		ST st=new ST();
		
		st.insertWarehouse(3, "Thessaloniki");
		st.insertBookAtWarehouse(3, 134, 2);
		st.insertBookAtWarehouse(3, 256, 2);
		
		st.insertWarehouse(13, "Irakleio");
		st.insertBookAtWarehouse(13, 134, 1);
		st.insertBookAtWarehouse(13, 349, 3);
		
		st.insertWarehouse(33, "Ithaki");
		st.insertBookAtWarehouse(33, 17, 1);
		st.insertBookAtWarehouse(33, 31, 1);
		st.insertBookAtWarehouse(33, 349, 1);
		
		while(flag==false){
			System.out.println("Menu: \n1.Insert a warehouse.			2.Delete a warehouse.");
			System.out.println("3.Insert a book to a warehouse.		4.Delete a book from a warehouse.");
			System.out.println("5.Search the books of a warehouse.	6.Search a book to a warehouse.");
			System.out.println("7.Search a book.			8.Print all the warehouses.	");
			System.out.println("9.Exit.");
			answer=in.nextInt();
		
			if(answer==1){
				System.out.println("Please enter the ID  of the warehouse:");
				int nodeid=in.nextInt();
				System.out.println("Now enter where it is located:");
				String name=in2.nextLine();
				st.insertWarehouse(nodeid, name);
			
			}else if(answer==2){
				System.out.println("Please enter the ID of the warehouse:");
				int nodeid=in.nextInt();
				st.removeWarehouse(nodeid);
			
			}else if(answer==3){
				System.out.println("Please enter the ID of the warehouse:");
				int nodeid=in.nextInt();
				System.out.println("Which book?(enter its isbn):");
				int isbn=in.nextInt();
				System.out.println("How many copies?");
				int copies=in.nextInt();
				st.insertBookAtWarehouse(nodeid, isbn, copies);
			
			}else if(answer==4){
				System.out.println("Please enter the ID  of the warehouse:");
				int nodeid=in.nextInt();
				System.out.println("Now enter the isbn of the book you want to delete 1 copy:");
				int isbn=in.nextInt();
				st.removeBook(nodeid, isbn);
			
			}else if(answer==5){
				System.out.println("Please enter the ID  of the warehouse:");
				int nodeid=in.nextInt();
				st.searchByWarehouse(nodeid);
			
			}else if(answer==6){
				System.out.println("Please enter the ID  of the warehouse:");
				int nodeid=in.nextInt();
				System.out.println("Now enter the isbn of the book you want:");
				int isbn=in.nextInt();
				st.searchBookInWarehouse(nodeid, isbn);
			
			}else if(answer==7){
				System.out.println("Enter the isbn of the book: ");
				int isbn=in.nextInt();
				System.out.println("This book is available at:");
				st.searchBook(isbn);
			
			}else if(answer==8){
				st.printΤree(System.out);
		
			}else if(answer==9){
				flag=true;
				
			}else{
				System.out.println("Unavailable choice.Please try again.");
			}
		}//end of while
		System.out.println("That is the end of the program.");
	}//end of main

}
