public class Exchange
{
	int id;
	Exchange parent;
	Exchange next;
	ExchangeList children;
	MobilePhoneSet registeredMobiles;
	Exchange(int number){
		id=number;
		next=null;
		children=new ExchangeList();
		registeredMobiles=new MobilePhoneSet();
	}
	public void setParent(Exchange l){
		this.parent=l;
	}
	public Exchange parent(){
		return parent;
	} 
	public int numChildren(){
		return children.countChildren();
	}
	public Boolean isRoot(){
		if(parent==null)
			return true;
		else
			return false;
	}
	public Exchange child(int i)throws Exception{
		if(i>this.numChildren())
			throw new Exception("Enter i within 0 to number of children");			
		else
			return children.specificChild(i);
	}
	public RoutingMapTree subtree(int i)throws Exception
	{
		if(i>this.numChildren())
			throw new Exception("Enter i within 0 to number of children");
		else
			return new RoutingMapTree(children.specificChild(i));
	}
	public MobilePhoneSet residentSet(){
		return registeredMobiles;
	}

}