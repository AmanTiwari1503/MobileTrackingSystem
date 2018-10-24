public class ExchangeList{
	LinkedList eset;
	ExchangeList(){
		eset=new LinkedList();
	}
	void insertExchange(Exchange o){
		Node a1=new Node(o);
		eset.insertNode(a1);
	}
	void insertExchangeFront(Exchange o){
		Node a1=new Node(o);
		eset.insertNodeFront(a1);
	}
	int countChildren(){
		return eset.countNodes();
	}
	Exchange specificChild(int i){
		Exchange p=new Exchange(0);
		eset.ptr=eset.head;
		while(i>=0)
		{
			eset.ptr=eset.ptr.next;
			i=i-1;
		}
		p=(Exchange)eset.ptr.data;
		return p;
	}
	void printAllid(){
		eset.printAll();
	}
}