import java.io.PrintStream;
public class ST{

	//*********************************
	private class TreeNode{
		int id;
		String city;
		int N;				//number of nodes in the subtree starting at this TreeNode
		List booklist;		// sorted linked list of the books
		
		public TreeNode(int id,String city,List booklist) {
			this.city=city;
			this.id=id;	
			this.booklist=booklist;
		}

		/* key() method. Via this method, the user 
		   is able to have access to the key of the tree.*/
		int getId(){return id;}	
		
		String getCity(){return city;}
		
		TreeNode l,r;		//pointers to left and right subtree 		
	};
	//*********************************

	
	private TreeNode head;	//root
	
	// the constructor
	public ST(){
		head=null;	
	}

	// these methods are used from various other methods in this class
	
	boolean less(int a,int b){
		return a<b;
	}
	boolean equals(int a,int b){
		return a==b;
	}
    
	// this method is used for checking issues
    private boolean found(int nodeid){
    		TreeNode node  =searchR(head, nodeid);
    		if(node==null){
    			return false;
    		}else{
    			return true;
    		}
    }

    // via this method we find the node we want in the tree
	 public TreeNode searchR(TreeNode h,int nodeid){
		 if(h==null)return null;
		 if(equals(nodeid,h.getId()))return h;
		 if(less(nodeid, h.getId())){
			 return searchR(h.l, nodeid);
		 }else{
			 return searchR(h.r, nodeid);
		 } 
	 }
    //-------------------------------------------------------------------------
    
    /************************************************************************
    *				Insert Warehouse
	************************************************************************/
    private TreeNode rotR(TreeNode h){
    		TreeNode xNode=h.l;
    		h.l=xNode.r;
    		xNode.r=h;
    		return xNode;
    }
    
    private TreeNode rotL(TreeNode h){
		TreeNode xNode=h.r;
		h.r=xNode.l;
		xNode.l=h;
		return xNode;
    }
    
    private TreeNode insertT(TreeNode h,int nodeid,String name){
    		if(h==null){
    			return new TreeNode(nodeid, name, null);
    		}
    		if(less(nodeid, h.getId())){
    			h.l=insertT(h.l, nodeid, name);
    			h=rotR(h);
    		}else{
    			h.r=insertT(h.r, nodeid, name);
    			h=rotL(h);
    		}
    		
    		return h;
    }
		
	private TreeNode insertWarehouse(TreeNode h,int nodeid,String name){
		if(h==null){
			return new TreeNode(nodeid, name,null);
		}
		if(Math.random()*(h.N+1)<1.0){
			return insertT(h, nodeid, name);
		}
		if (less(nodeid, h.getId()))
			h.l=insertWarehouse(h.l, nodeid, name);
		else 
			h.r=insertWarehouse(h.r, nodeid, name);
		
		h.N++;
		return h;	
	}
	
	 public void insertWarehouse(int nodeid,String name){	 
		 if(found(nodeid)){
			 System.out.println("ERROR! There is already a warehouse with this ID");
		 }else{
			head=insertWarehouse(head, nodeid, name);	
			System.out.println("Warehouse added.");
		 }
		 System.out.println();
		 System.out.println();
	}
	//--------------------------------------------------

	/************************************************************************
    *				Insert Book At Warehouse
	************************************************************************/

	 public void insertBookAtWarehouse(int nodeid, int isbn, int copies){
		 if(!found(nodeid)){
			 System.out.println("There is no warehouse with this ID.");
		 }else{
			 TreeNode node=searchR(head, nodeid);
			 BookInfo bookInfo=new BookInfo(isbn, copies);
			 if(node.booklist==null){
				 List list = new List();
				 list.insert(bookInfo);
				 node.booklist=list;
				 node.booklist.merging(node.booklist);
			 }else{
				 boolean flag=node.booklist.found2(isbn);
				 List list=node.booklist;
				 if(flag && node.booklist!=null){
					 BookInfo b=new BookInfo(list.find2(isbn).getIsbn(), list.find2(isbn).getCopies());
					 b.setCopies(b.getCopies()+copies);
					 list.find2(isbn).setIsbn(0);
					 list.checking(list);
					 list.insert(b);
					 list.merging(list); 
				 }
				 else{
					 BookInfo bookInfo2=new BookInfo(isbn, copies);
					 list.insert(bookInfo2);
					 list.merging(list);
				 } 
			 }	
			 System.out.println("Book added.");
	
		 }	 
		 System.out.println();
		 System.out.println();
	}
	//--------------------------------------------------

	/************************************************************************
    *				Remove Warehouse
	************************************************************************/
	 
	 private TreeNode joinLR(TreeNode a,TreeNode b){
		 int N=0;
		 if(a!=null && b!=null){
			 N=a.N+b.N;
		 }
		
		 if(a==null)return b;
		 if(b==null)return a;
		 if(Math.random()*N<1.0*a.N){
			 a.r=joinLR(a.r, b);
			 a.N--;
			 return a;
		 }else{
			 b.l=joinLR(a, b.l);
			 b.N--;
			 return b;
		 }
	 }
	 
	 private TreeNode removeR(TreeNode h, int nodeid){
		 if(h==null)return null;
		 
		 int w=h.getId();
		 if(less(nodeid, w))h.l=removeR(h.l, nodeid);
		 if(less(w, nodeid))h.r=removeR(h.r, nodeid);
		 if(equals(nodeid,w))h=joinLR(h.l, h.r);
		 
		 return h;
	 }
	 
	 public void removeWarehouse(int nodeid){
		 if(!found(nodeid)){
			 System.out.println("Error! No warehouse with this ID.");
		 }else{
			 head=removeR(head, nodeid);		
			 System.out.println("Warehouse deleted.");
		 }
		 System.out.println();
		 System.out.println();
	}
	//--------------------------------------------------
	 
	/************************************************************************
    *				Remove A Book
	************************************************************************/
	 public void removeBook(int nodeid, int isbn){
		 if(!found(nodeid)){
			 System.out.println("There is no warehouse with this ID.");
		 }else{
			 if(!searchR(head, nodeid).booklist.found2(isbn)){
				 System.out.println("This book is not in this warehouse.");
			 }else{
				List list=searchR(head, nodeid).booklist;
				BookInfo bookInfo=new BookInfo(list.find2(isbn).getIsbn(), list.find2(isbn).getCopies());
				bookInfo.setCopies(bookInfo.getCopies()-1);
				if(bookInfo.getCopies()==0){
					//deleting the book 
					list.find2(isbn).setIsbn(0);
					list.checking(list);
				}else{
					//updating the book
					list.find2(isbn).setIsbn(0);
					list.checking(list);
					list.insert(bookInfo);
					list.merging(list);
				}
				System.out.println("Book deleted.");
			 } 
		 }
		 System.out.println();
		 System.out.println();
	}
	//--------------------------------------------------
	 
	/************************************************************************
    *				Search By Warehouse
	************************************************************************/
	 public void searchByWarehouse(int nodeid){
		TreeNode node=searchR(head, nodeid);
		if(node!=null){
			System.out.println("Warehouse "+node.getId()+" located in "+node.getCity()+" :");
			node.booklist.display();
		}else{
			System.out.println("There is no warehouse with this ID.");
		}
		 System.out.println();
		 System.out.println();
	}
	//--------------------------------------------------
	 
	/************************************************************************
    *				Search A Book In A Warehouse
	************************************************************************/
	 public void searchBookInWarehouse(int nodeid, int isbn){
		TreeNode node=searchR(head, nodeid);
		if(node!=null){
			node.booklist.find2(isbn);
			if(node.booklist.find2(isbn)==null){
				System.out.println("Warehouse "+node.getId()+" does not have this book.");
			}else{
				System.out.println("Warehouse "+node.getId()+" has "+node.booklist.find2(isbn).getCopies()+" copies of this book.");
			}
		}else{
			System.out.println("There is no warehouse with this ID.");
		}
		 System.out.println();
		 System.out.println();
	}
	//--------------------------------------------------


	/************************************************************************
    *				Search A Book
	************************************************************************/ 
	 //preorder
	 private void traverseP(TreeNode node,int isbn){
		 if(node==null)return;
		 node.booklist.find2(isbn);
		 if(node.booklist.find2(isbn)!=null){
		 System.out.println("Warehouse "+node.getId()+" located in "+node.getCity()+", copies: "+node.booklist.find2(isbn).getCopies());
		 }
		 traverseP(node.l,isbn);
		 traverseP(node.r,isbn); 
	 }
	 
	
	 public void searchBook(int isbn){
		 traverseP(head, isbn);
		 System.out.println();
		 System.out.println();
	}
	//--------------------------------------------------

	/************************************************************************
    *				Print All The Warehouses
	************************************************************************/
	 //preorder
	 private void traverse(TreeNode node){
		 if(node==null)return;
		 System.out.println("Warehouse "+node.getId()+" at "+node.getCity()+" has the following books:");
		 
		 node.booklist.display();
		 System.out.println();
		 System.out.println();
		
		 traverse(node.l);
		 traverse(node.r);
	 }
	 
	 public void printΤree(PrintStream stream){
		traverse(head);	
	}
	//--------------------------------------------------
}
