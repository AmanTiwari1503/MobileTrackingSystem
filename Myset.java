class Node{
	Object data;
	Node next;
	Node(){
		data=new Object();
		next=null;
	}
	Node(Object temp){
		data=temp;
		next=null;
	}
}

class LinkedList
{
	Node head;
	Node ptr;
	LinkedList(){
		head=new Node();
		ptr=new Node();
	}
	Boolean isEmpty(){
		if(head.next == null)
			return true;
		else{
			return false;
		}
	}
	void insertNode(Node o){
		ptr=head;
		while(ptr.next!=null)
		{
			ptr=ptr.next;
		}
		ptr.next=o;
		o.next=null;
	}
	void insertNodeFront(Node o){
		o.next=head.next;
		head.next=o;
	}
	int countNodes(){
		ptr=head;int k=0;
		while(ptr.next!=null)
		{
			ptr=ptr.next;
			k=k+1;
		}
		return k;
	}
	void deleteNode(Node o)throws Exception{
		ptr=head;
		Node prev=new Node();
		prev=head;
		int flag=0;
		if(isEmpty())
		{
			throw new Exception("Set is empty");
		}
		else{
			while(ptr.next != null){
				ptr=ptr.next;
				if(ptr.data==o.data)
				{
					prev.next=ptr.next;
					flag=1;
				}
				else
				{
					prev=ptr;
				}
		}
		}
		if(flag==0){
			throw new Exception("No such node found");
		}
	}
	Boolean isNodeAMember(Node o){
		int flag=0;
		if(isEmpty())
			return false;
		ptr=head;
		while(ptr.next !=null)
		{
			ptr=ptr.next;
			if(ptr.data==o.data)
			{
				flag=1;
				break;
			}
		}
		if(flag==0)
			return false;
		else
			return true;
	}
	void printAll(){
		ptr=head;
		while(ptr.next!=null){
			ptr=ptr.next;
			System.out.println(ptr.data);
		}
	}
}
public class Myset{
	LinkedList aset=new LinkedList();
	public Boolean IsEmpty(){
		return aset.isEmpty();
	}
	public Boolean IsMember(Object o){
		Node a1=new Node(o);
		return aset.isNodeAMember(a1);
	}
	public void Insert(Object o){
		Node a1=new Node(o);
		aset.insertNode(a1);
	}
	public void Delete(Object o)throws Exception{
		try{
		Node a1=new Node(o);
		aset.deleteNode(a1);
	}
	catch(Exception e)
	{
		throw new Exception(e.getMessage());
	}
	}
	public void printSet(){
		aset.printAll();
	}
	public Myset Union(Myset a){
		Myset c=new Myset();
		aset.ptr=aset.head;
		while(aset.ptr.next !=null)
		{
			aset.ptr=aset.ptr.next;
			c.Insert(aset.ptr.data);
		}
		a.aset.ptr=a.aset.head;
		while(a.aset.ptr.next != null)
		{
			a.aset.ptr=a.aset.ptr.next;
			if(IsMember(a.aset.ptr.data)){
				continue;
			}
			else
			{
				c.Insert(a.aset.ptr.data);
			}
		}
		return c;
	}
	public Myset Intersection(Myset a){
		Myset c=new Myset();
		aset.ptr=aset.head;
		while(aset.ptr.next !=null){
			aset.ptr=aset.ptr.next;
			if(a.IsMember(aset.ptr.data))
			{
				c.Insert(aset.ptr.data);
			}
		}
		return c;
	}
	/*public static void main(String args[]){
		Myset a=new Myset();
		Myset b=new Myset();
		Myset c=new Myset();
		a.Insert(2);
		a.Insert(3);
		a.Insert(4);
		a.Insert(5);
		b.Insert(3);
		b.Insert(5);
		b.Insert(4);
		c=b.Union(a);
		c.printSet();
	}*/
}
