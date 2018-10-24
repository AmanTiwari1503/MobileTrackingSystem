public class MobilePhone
{
	Integer id;
	Boolean state;
	Exchange em;
	MobilePhone(Integer number){
		id=number;
		state=false;
	}
	void setBase(Exchange base){
		em=base;
	}
	public Integer number(){
		return id;
	}
	public Boolean status(){
		return state;
	}
	public void switchOn(){
		state=true;
	}
	public void switchOff(){
		state=false;
	}
	public Exchange location()throws Exception{
		if(state==true)
			return em;
		else
			throw new Exception("Phone is switched off");
	}
}