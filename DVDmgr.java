package cn.kgc.tangcco.zhongjiban.MiniDVD;

import java.text.ParseException;
import java.util.Scanner;

public class DVDmgr {
	DVD[] dvd = new DVD[10];
	Scanner input = new Scanner(System.in);
	//��ʼ��
	public void initial() {
		DVD dvd1 = new DVD("�������", true, "2013-7-1");
		DVD dvd2 = new DVD("�������", false, null);
		DVD dvd3 = new DVD("��������", false, null);
		dvd[0] = dvd1;
		dvd[1] = dvd2;
		dvd[2] = dvd3;
	}
	//չʾ����
	public void showAll() {
		System.out.println("���\t״̬\t����\t\t�������");
		int index = 1;
		for (int i = 0; i < dvd.length; i++) {
			if (dvd[i] != null) {
				System.out.print(index++ + "\t");
				dvd[i].show();
			}
		}
	}
	//���ܽ�����ʾ
	public void startMenu() {
		int choice;
		do {
			System.out.print("����0���أ�");
			choice = input.nextInt();
			if (choice == 0) {
				return;
			}
		} while (choice != 0);
	}
	//�˵�
	public void menu() throws ParseException {
		System.out.println("��ӭʹ������DVD������");
		System.out.println("-------------------");
		int choice;
		do {
			System.out.println("1.����DVD");
			System.out.println("2.�鿴DVD");
			System.out.println("3.ɾ��DVD");
			System.out.println("4.���DVD");
			System.out.println("5.�黹DVD");
			System.out.println("0.�˳�");
			System.out.println("6.�����ѽ��DVD");
			System.out.println("7.����δ���DVD");
			System.out.println("8.��DVD���Ƴ��ȵ���鿴");
			System.out.println();
			System.out.print("��ѡ��");
			choice = input.nextInt();
			switch (choice) {
			case 1:
				if (!addResult()) {
					System.out.println("���ʧ��");
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
					System.out.println("ɾ���ɹ���");
				} else {
					System.out.println("ɾ��ʧ��");
				}
				startMenu();
				break;
			case 4:
				showAll();
				if (!rent()) {
					System.out.println("���ʧ��");
				}
				startMenu();
				break;
			case 5:
				showLend();
				if (!gaveDVD()) {
					System.out.println("�黹ʧ��");
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
				System.out.println("�����������������");
				break;
			case 0:
				System.out.println("��ӭ�´�ʹ��");
				return;

			}
		} while (choice != 0);
	}
	//���
	public DVD addDVD() {
		System.out.print("������DVD���ƣ�");
		DVD dvd = new DVD();
		dvd.setName(input.next());
		if (add(dvd)) {
			System.out.println("������" + dvd.getName() + "���ɹ�");
			dvd.setDays(null);
			return dvd;
		}
		return null;
	}
	//�ж��Ƿ��������
	public boolean add(DVD dvd1) {
		for (int i = 0; i < dvd.length; i++) {
			if (dvd[i] != null && dvd[i].getName().equals(dvd1.getName())) {
				return false;
			}
		}
		return true;
	}
	//�ж��Ƿ���λ�� ���
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
	//�ж��Ƿ���ɾ���ɹ�
	public boolean queryByName() {
		System.out.print("������Ҫɾ����DVD���ƣ�");
		String name = input.next();
		for (int i = 0; i < dvd.length; i++) {
			if (dvd[i] != null && dvd[i].getName().equals(name)&&dvd[i].isHave()==false) {
				dvd[i] = null;
				return true;
			}
		}
		return false;
	}
	//�ж��Ƿ��и�DVD
	public boolean have(DVD dvd1) {
		for (int j = 0; j < dvd.length; j++) {
			if (dvd[j] != null && dvd[j].getName().equals(dvd1.getName())) {
				return true;
			}
		}
		return false;
	}
	//���
	public boolean rent() {
		System.out.print("������DVD���ƣ�");
		DVD dvd1 = new DVD();
		dvd1.setName(input.next());
		if (have(dvd1)) {
			for (int i = 0; i < dvd.length; i++) {
				if (dvd[i] != null
						&& (dvd[i].getName().equals(dvd1.getName()) && dvd[i]
								.isHave() == false)) {
					System.out.print("�����������ڣ�����-��-�գ�");
					dvd1.setDays(input.next());
					System.out.println("����ɹ���");
					dvd[i] = dvd1;
					dvd[i].setHave(true);
					return true;
				}
			}
		} else {
			System.out.println("���޴���");
		}

		return false;
	}
	// �黹
	public boolean gaveDVD() throws ParseException {
		System.out.print("������DVD���ƣ�");
		DVD dvd1 = new DVD();
		dvd1.setName(input.next());
		if (have(dvd1)) {
			for (int i = 0; i < dvd.length; i++) {
				if (dvd[i] != null
						&& (dvd[i].getName().equals(dvd1.getName()) && dvd[i]
								.isHave() == true)) {
					System.out.print("������黹���ڣ�����-��-�գ�");
					String dstr2 = input.next();
					dvd1.calcRent(dvd[i].getDays(), dstr2);
					System.out.println("�黹�ɹ���");
					dvd[i] = dvd1;
					dvd[i].setHave(false);
					return true;
				}
			}
		} else {
			System.out.println("���޴���");
		}
		return false;
	}
	// 6.�����ѽ��DVD
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
	// ��DVD���Ƴ��ȵ���鿴
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
