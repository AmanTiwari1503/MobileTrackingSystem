import java.io.*;
import java.util.*;
public class RoutingMapTree{
	Exchange root;
	public RoutingMapTree() {
		root =new Exchange(0);
		root.parent=null;
	}
	public RoutingMapTree(Exchange Root)
	{
		root=Root;
	}
	public void switchOn(MobilePhone a,Exchange b)throws Exception
	{
		boolean k=a.status();
		if(k==false)
		{
		if(!this.root.registeredMobiles.findMobileAlter(a.id))
		{
			a.setBase(b);
			a.switchOn();
			while(b.isRoot() !=true){
				b.registeredMobiles.insertmobile(a);
				b=b.parent();
			}
			b.registeredMobiles.insertmobile(a);
		}
		else
		{
			a.switchOn();
			Exchange g=a.location();
			while(g.isRoot() !=true){
				g.registeredMobiles.deletemobile(a);
				g=g.parent();
			}
			g.registeredMobiles.deletemobile(a);
			a.setBase(b);
			while(b.isRoot() !=true){
				b.registeredMobiles.insertmobile(a);
				b=b.parent();
			}
			b.registeredMobiles.insertmobile(a);
		}
		}
		else
		{
			throw new Exception("Mobile Phone "+a.id+"already switched on");
		}

	}
	public void switchOff(MobilePhone a)throws Exception 
	{
		boolean k=a.status();
		if(k==true)
		{
			//Exchange g=a.location();
			a.switchOff();
			/*while(g.isRoot() !=true){
				g.registeredMobiles.deletemobile(a);
				g=g.parent();
			}
			g.registeredMobiles.deletemobile(a);*/
		}
		else
		{
			throw new Exception("Mobile Phone "+a.id+"already switched off");
		}
	}
	public Exchange getNode(RoutingMapTree d,int ide)throws Exception
	{
		try{
		Exchange p=new Exchange(-1);
		p=d.root;int i=0;
		int h=p.numChildren();
		for(i=0;i<h;i++)
		{
			p=getNode(p.subtree(i),ide);
			if(p == d.root)
				continue;
			else
				return p;
		}
		if(p.id==ide)
			return p;
		else
			return d.root.parent();
	}
	catch(Exception e)
	{
		throw new Exception(e.getMessage());
	}
	}
	public Exchange findPhone(MobilePhone m)throws Exception
	{
		boolean k=m.status();
		if(k==true)
		{
			return m.location();
		}
		else
			throw new Exception("Error - Mobile phone with identifier "+m.id+" is currently switched off");
	}
	public Exchange lowestRouter(Exchange a, Exchange b)
	{
		while(a!=b){
			a=a.parent();
			b=b.parent();
		}
		return a;
	}
	public ExchangeList routeCall(MobilePhone a, MobilePhone b)throws Exception
	{
		try{
		Exchange a1=findPhone(a);
		Exchange a2=findPhone(b);
		Exchange cmn=lowestRouter(a1,a2);
		ExchangeList rc1=new ExchangeList();
		ExchangeList rc2=new ExchangeList();
		while(a1!=cmn)
		{
			rc1.insertExchange(a1);
			a1=a1.parent();
		}
		rc1.insertExchange(a1);
		while(a2!=cmn)
		{
			rc2.insertExchangeFront(a2);
			a2=a2.parent();
		}
		rc1.eset.ptr=rc1.eset.ptr.next;
		rc1.eset.ptr.next=rc2.eset.head.next;
		return rc1;
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}
	public void movePhone(MobilePhone a, Exchange b)throws Exception
	{
		int h=b.numChildren();
		boolean k=a.status();
		if(h==0)
		{
		if(k==true)
		{
			this.switchOff(a);
			this.switchOn(a,b);
		}
		else
			throw new Exception("Mobile Phone "+a.id+" is switched off");
		}
		else
			throw new Exception(b.id+" is not a base station");
	}
	public String performAction(String actionMessage){	
		String s1[]=actionMessage.split("\\s+");
		String result="";
		if(s1[0].equals("addExchange"))
		{
			int a1=Integer.parseInt(s1[1]);
			int a2=Integer.parseInt(s1[2]);
			try{
				Exchange d=getNode(this,a1);
				if(d==null)
				System.out.println("Error- No exchange with identifier "+a1);
			else
			{
				Exchange newn=new Exchange(a2);
				d.children.insertExchange(newn);
				newn.setParent(d);
				return result;
			}
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
		else if(s1[0].equals("switchOnMobile"))
		{
			int a1=Integer.parseInt(s1[1]);
			int a2=Integer.parseInt(s1[2]);
			try{
			Exchange d=getNode(this,a2);
			if(d==null)
				System.out.println("Error- No exchange with identifier "+a2);
			else
			{
				if(this.root.registeredMobiles.findMobileAlter(a1))
					this.switchOn(this.root.registeredMobiles.findMobile(a1),d);
				else
				{
					MobilePhone newn=new MobilePhone(a1);
					this.switchOn(newn,d);
					return result;
				}
			}
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
		else if(s1[0].equals("switchOffMobile"))
		{
			int a1=Integer.parseInt(s1[1]);
			try{
			this.switchOff(this.root.registeredMobiles.findMobile(a1));
			return result;
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
		else if(s1[0].equals("queryNthChild"))
		{
			int a1=Integer.parseInt(s1[1]);
			int a2=Integer.parseInt(s1[2]);
			try{
			Exchange d=getNode(this,a1);
			if(d==null)
				System.out.println("Error- No exchange with identifier "+a1);
			else{
			result="queryNthChild "+a1+" "+a2+": "+d.child(a2).id;
			System.out.println(result);
			return result;
			}
			}
			catch(Exception e)
			{
				System.out.println("Error - No "+a2+" child of Exchange "+a1);
			}
		}
		else if(s1[0].equals("queryMobilePhoneSet"))
		{
			int a1=Integer.parseInt(s1[1]);
			try{
			Exchange d=getNode(this,a1);
			if(d==null)
				System.out.println("Error - No exchange with identifier "+a1);
			else
			{
			MobilePhoneSet a=d.registeredMobiles;
			result="queryMobilePhoneSet "+a1+": ";
			a.setofmobile.aset.ptr=a.setofmobile.aset.head;
			 a.setofmobile.aset.ptr=a.setofmobile.aset.ptr.next;
			while(a.setofmobile.aset.ptr.next!=null)
			{
			    MobilePhone r=(MobilePhone)a.setofmobile.aset.ptr.data;
			    if(r.status() == false)
			    {
			    	a.setofmobile.aset.ptr=a.setofmobile.aset.ptr.next;
			    	continue;
			    }
			    result=result+r.id+", ";
			     a.setofmobile.aset.ptr=a.setofmobile.aset.ptr.next;
			}
			MobilePhone r=(MobilePhone)a.setofmobile.aset.ptr.data;
			if(r.status() !=false)
			{
			result=result+r.id;
			}
			System.out.println(result);
			return result;	
			}
			}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		}
		else if(s1[0].equals("findPhone"))
		{
				int a1=Integer.parseInt(s1[1]);
				result="queryFindPhone "+a1+": ";
				try
				{
					MobilePhone p1=this.root.registeredMobiles.findMobile(a1);
					Exchange b=findPhone(p1);
					result=result+b.id;
					System.out.println(result);
					return result;
				}
				catch(Exception e)
				{
					result=result+e.getMessage();
					System.out.println(result);
					return result;
				}
		}
		else if(s1[0].equals("lowestRouter"))
		{
				int a1=Integer.parseInt(s1[1]);
				int a2=Integer.parseInt(s1[2]);
				try
				{
				Exchange e1=getNode(this,a1);
				Exchange e2=getNode(this,a2);
				if(e1==null)
					System.out.println("Error - No exchange with identifier "+a1);
				else if(e2==null)
					System.out.println("Error - No exchange with identifier "+a2);
				else
				{
				Exchange cmn=lowestRouter(e1,e2);
				result="queryLowestRouter "+a1+" "+a2+": "+cmn.id;
				System.out.println(result);
				return result;
				}
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
		}
		else if(s1[0].equals("findCallPath"))
		{
				int a1=Integer.parseInt(s1[1]);
				int a2=Integer.parseInt(s1[2]);
				result="queryFindCallPath "+a1+" "+a2+": ";
				try
				{
					ExchangeList listcall=routeCall(this.root.registeredMobiles.findMobile(a1),this.root.registeredMobiles.findMobile(a2));
					listcall.eset.ptr=listcall.eset.head;
			 		listcall.eset.ptr=listcall.eset.ptr.next;
					while(listcall.eset.ptr.next!=null)
					{
			    		Exchange r=(Exchange)listcall.eset.ptr.data;
			    		result=result+r.id+", ";
			     		listcall.eset.ptr=listcall.eset.ptr.next;
					}
					Exchange r=(Exchange)listcall.eset.ptr.data;
					result=result+r.id;
					System.out.println(result);
					return result;
				}
				catch(Exception e)
				{
					result=result+e.getMessage();
					System.out.println(result);
					return result;
				}
		}
		else if(s1[0].equals("movePhone"))
		{
			int a1=Integer.parseInt(s1[1]);
			int a2=Integer.parseInt(s1[2]);
			try{
			MobilePhone p1=this.root.registeredMobiles.findMobile(a1);
			Exchange d=getNode(this,a2);
			if(d==null)
				System.out.println("Error - No exchange with identifier "+a2);
			else
			{
				this.movePhone(p1,d);
			}
			return result;
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	/*public static void main(String args[]){
		RoutingMapTree s=new RoutingMapTree();
		Exchange p=new Exchange(45);
		p.setParent(s.root);
		MobilePhone k1=new MobilePhone(1234);
		MobilePhone k2=new MobilePhone(2345);
		MobilePhone k3=new MobilePhone(3456);
		MobilePhone k4=new MobilePhone(4567);
		MobilePhone k5=new MobilePhone(5678);
		p.registeredMobiles.insertmobile(k1);
		p.registeredMobiles.insertmobile(k2);
		Exchange q=new Exchange(46);
		q.setParent(s.root);
		Exchange r=new Exchange(47);
		r.setParent(s.root);
		r.registeredMobiles.insertmobile(k5);
		Exchange k=new Exchange(23);
		Exchange pq=new Exchange(24);
		pq.registeredMobiles.insertmobile(k3);
		pq.registeredMobiles.insertmobile(k4);
		Exchange pw=new Exchange(25);

		s.root.children.insertExchange(p);
		s.root.children.insertExchange(q);
		s.root.children.insertExchange(r);
		p.children.insertExchange(k);
		k.setParent(p);
		p.children.insertExchange(pq);
		pq.setParent(p);
		q.children.insertExchange(pw);
		pw.setParent(q);
		s.performAction("queryMobilePhoneSet 45");
		System.out.println("");
		s.performAction("queryMobilePhoneSet 47");
		System.out.println("");
		s.performAction("queryMobilePhoneSet 24");
		System.out.println("");
		s.performAction("queryNthChild 45 0");
	}*/
}
