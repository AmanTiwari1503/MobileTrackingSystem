public class MobilePhoneSet extends Myset
{
	Myset setofmobile;
	MobilePhoneSet(){
		setofmobile=new Myset();
	}
	public void insertmobile(Object o){
		setofmobile.Insert(o);
	}
	public void deletemobile(Object o)throws Exception{
		try{
			setofmobile.Delete(o);
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}
	public boolean isAMember(Object o){
		return setofmobile.IsMember(o);
	}
	public MobilePhoneSet unionmobile(MobilePhoneSet o){
		MobilePhoneSet c=new MobilePhoneSet();
		c.setofmobile=setofmobile.Union(o.setofmobile);
		return c;
	}
	public MobilePhoneSet intersectionmobile(MobilePhoneSet o){
		MobilePhoneSet c=new MobilePhoneSet();
		c.setofmobile=setofmobile.Intersection(o.setofmobile);
		return c;
	}
	public MobilePhone findMobile(int id)throws Exception
	{
		MobilePhone temp=new MobilePhone(0);
		setofmobile.aset.ptr=setofmobile.aset.head;
		int flag=0;
		while(setofmobile.aset.ptr.next!=null)
		{
			setofmobile.aset.ptr=setofmobile.aset.ptr.next;
			temp=(MobilePhone)setofmobile.aset.ptr.data;
			if(temp.id == id)
			{
				flag=1;
				break;
			}
		}
		if(flag==1)
			return (MobilePhone)setofmobile.aset.ptr.data;
		else
			throw new Exception("Error - No mobile phone with identifier "+id+" found in the network");
	}
	public boolean findMobileAlter(int id)
	{
		MobilePhone temp=new MobilePhone(0);
		setofmobile.aset.ptr=setofmobile.aset.head;
		int flag=0;
		while(setofmobile.aset.ptr.next!=null)
		{
			setofmobile.aset.ptr=setofmobile.aset.ptr.next;
			temp=(MobilePhone)setofmobile.aset.ptr.data;
			if(temp.id == id)
			{
				flag=1;
				break;
			}
		}
		if(flag==1)
			return true;
		else
			return false;
	}
	/*public void printAll(){
		setofmobile.aset.ptr=setofmobile.aset.head;
		while(setofmobile.aset.ptr.next!=null)
		{
			setofmobile.aset.ptr=setofmobile.aset.ptr.next;
			MobilePhone temp=(MobilePhone)setofmobile.aset.ptr.data;
			if(temp.status()==true)
			{
				System.out.println(temp.id);
			}
			else
				continue;
		}
	}*/
	/*public static void main(String args[]){
		MobilePhone k1=new MobilePhone(1234);
		MobilePhone k2=new MobilePhone(2345);
		MobilePhone k3=new MobilePhone(3456);
		MobilePhone k4=new MobilePhone(4567);
		MobilePhone k5=new MobilePhone(5678);
		MobilePhoneSet w=new MobilePhoneSet();
		w.insertmobile(k1.id);
		w.insertmobile(k2.id);
		w.insertmobile(k3.id);
		w.insertmobile(k4.id);
		w.insertmobile(k5.id);
		w.printAll();

	}*/
}