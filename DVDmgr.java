package cn.kgc.tangcco.zhongjiban.MiniDVD;

import java.text.ParseException;
import java.util.Scanner;

public class DVDmgr {
	DVD[] dvd = new DVD[10];
	Scanner input = new Scanner(System.in);
	//初始化
	public void initial() {
		DVD dvd1 = new DVD("罗马假日", true, "2013-7-1");
		DVD dvd2 = new DVD("风声鹤唳", false, null);
		DVD dvd3 = new DVD("浪漫满屋", false, null);
		dvd[0] = dvd1;
		dvd[1] = dvd2;
		dvd[2] = dvd3;
	}
	//展示所有
	public void showAll() {
		System.out.println("序号\t状态\t名称\t\t借出日期");
		int index = 1;
		for (int i = 0; i < dvd.length; i++) {
			if (dvd[i] != null) {
				System.out.print(index++ + "\t");
				dvd[i].show();
			}
		}
	}
	//功能结束提示
	public void startMenu() {
		int choice;
		do {
			System.out.print("输入0返回：");
			choice = input.nextInt();
			if (choice == 0) {
				return;
			}
		} while (choice != 0);
	}
	//菜单
	public void menu() throws ParseException {
		System.out.println("欢迎使用迷你DVD管理器");
		System.out.println("-------------------");
		int choice;
		do {
			System.out.println("1.新增DVD");
			System.out.println("2.查看DVD");
			System.out.println("3.删除DVD");
			System.out.println("4.借出DVD");
			System.out.println("5.归还DVD");
			System.out.println("0.退出");
			System.out.println("6.查找已借出DVD");
			System.out.println("7.查找未借出DVD");
			System.out.println("8.按DVD名称长度倒序查看");
			System.out.println();
			System.out.print("请选择");
			choice = input.nextInt();
			switch (choice) {
			case 1:
				if (!addResult()) {
					System.out.println("添加失败");
				}
				startMenu();
				break;
			case 2:
				showAll();
				startMenu();
				break;
			case 3:
				showAll();
				if (queryByName()) {
					System.out.println("删除成功！");
				} else {
					System.out.println("删除失败");
				}
				startMenu();
				break;
			case 4:
				showAll();
				if (!rent()) {
					System.out.println("借出失败");
				}
				startMenu();
				break;
			case 5:
				showLend();
				if (!gaveDVD()) {
					System.out.println("归还失败");
				}
				startMenu();
				break;
			case 6:
				showLend();
				startMenu();
				break;
			case 7:
				showExist();
				startMenu();
				break;
			case 8:
				showByLength();
				showAll();
				startMenu();
				break;
			default:
				System.out.println("输入错误，请重新输入");
				break;
			case 0:
				System.out.println("欢迎下次使用");
				return;

			}
		} while (choice != 0);
	}
	//添加
	public DVD addDVD() {
		System.out.print("请输入DVD名称：");
		DVD dvd = new DVD();
		dvd.setName(input.next());
		if (add(dvd)) {
			System.out.println("新增《" + dvd.getName() + "》成功");
			dvd.setDays(null);
			return dvd;
		}
		return null;
	}
	//判断是否添加重名
	public boolean add(DVD dvd1) {
		for (int i = 0; i < dvd.length; i++) {
			if (dvd[i] != null && dvd[i].getName().equals(dvd1.getName())) {
				return false;
			}
		}
		return true;
	}
	//判断是否有位置 添加
	public boolean addResult() {
		DVD dvd1=addDVD();
		for (int i = 0; i < dvd.length; i++) {
			if (dvd[i] == null&&dvd1!=null) {
				dvd[i] = dvd1;
				return true;
			}
		}
		return false;
	}
	//判断是否能删除成功
	public boolean queryByName() {
		System.out.print("请输入要删除的DVD名称：");
		String name = input.next();
		for (int i = 0; i < dvd.length; i++) {
			if (dvd[i] != null && dvd[i].getName().equals(name)&&dvd[i].isHave()==false) {
				dvd[i] = null;
				return true;
			}
		}
		return false;
	}
	//判断是否有该DVD
	public boolean have(DVD dvd1) {
		for (int j = 0; j < dvd.length; j++) {
			if (dvd[j] != null && dvd[j].getName().equals(dvd1.getName())) {
				return true;
			}
		}
		return false;
	}
	//借出
	public boolean rent() {
		System.out.print("请输入DVD名称：");
		DVD dvd1 = new DVD();
		dvd1.setName(input.next());
		if (have(dvd1)) {
			for (int i = 0; i < dvd.length; i++) {
				if (dvd[i] != null
						&& (dvd[i].getName().equals(dvd1.getName()) && dvd[i]
								.isHave() == false)) {
					System.out.print("请输入借出日期：（年-月-日）");
					dvd1.setDays(input.next());
					System.out.println("借出成功！");
					dvd[i] = dvd1;
					dvd[i].setHave(true);
					return true;
				}
			}
		} else {
			System.out.println("查无此项");
		}

		return false;
	}
	// 归还
	public boolean gaveDVD() throws ParseException {
		System.out.print("请输入DVD名称：");
		DVD dvd1 = new DVD();
		dvd1.setName(input.next());
		if (have(dvd1)) {
			for (int i = 0; i < dvd.length; i++) {
				if (dvd[i] != null
						&& (dvd[i].getName().equals(dvd1.getName()) && dvd[i]
								.isHave() == true)) {
					System.out.print("请输入归还日期：（年-月-日）");
					String dstr2 = input.next();
					dvd1.calcRent(dvd[i].getDays(), dstr2);
					System.out.println("归还成功！");
					dvd[i] = dvd1;
					dvd[i].setHave(false);
					return true;
				}
			}
		} else {
			System.out.println("查无此项");
		}
		return false;
	}
	// 6.查找已借出DVD
	public void showLend() {
		for (int i = 0; i < dvd.length; i++) {
			if (dvd[i] != null && dvd[i].isHave() == true) {
				dvd[i].show();
			}
		}
	}
	public void showExist() {
		for (int i = 0; i < dvd.length; i++) {
			if (dvd[i] != null && dvd[i].isHave() == false) {
				dvd[i].show();
			}
		}
	}
	// 按DVD名称长度倒序查看
	public void showByLength() {
		for (int i = 0; i < dvd.length - 1; i++) {
			if (dvd[i] != null) {
				for (int j = i + 1; j < dvd.length; j++) {
					if (dvd[j] != null) {
						if (dvd[i].getName().length() < dvd[j].getName()
								.length()) {
							DVD temp = dvd[i];
							dvd[i] = dvd[j];
							dvd[j] = temp;
						}
					}
				}
			}
		}
	}
}
