package cn.kgc.tangcco.zhongjiban.MiniDVD;

import java.text.ParseException;

public class DVDStart {
	public static void main(String[] args) throws ParseException {
		DVDmgr dvd=new DVDmgr();
		dvd.initial();
		dvd.menu();
	}
}
