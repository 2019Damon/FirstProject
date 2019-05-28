package cn.kgc.tangcco.zhongjiban.MiniDVD;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DVD{
	private String name;
	private boolean have;//��û�� ״̬
	private String days;//����
	private double money=2;
	public double getMoney() {
		return 2;
	}
	public String getDays() {
		if(days==null){
			this.days=" ";
		}
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isHave() {
		return have;
	}
	public void setHave(boolean have) {
		this.have = have;
	}
	public DVD(String name, boolean have,String days) {
		super();
		this.name = name;
		this.have = have;
		if(days==null){
			this.days=" ";
		}else{
			this.days=days;
		}
	}
	public DVD() {
	}
	public void show(){
		if(have==true){
			System.out.println("�ѽ��\t"+name+"\t\t"+days);
		}else{
			if(days==null){
				this.days=" ";
			}
			System.out.println("�ɽ�\t"+name+"\t\t"+days);
		}
	}
	public void calcRent(String days,String dstr2) throws ParseException{
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
		Date d1=sd.parse(days);
		Date d2=sd.parse(dstr2);
		long charge=(d2.getTime()-d1.getTime())/(24*60*60*1000);//�õ����ڲ�
		System.out.println("���Ϊ��"+money*charge+"Ԫ");
	}
}
