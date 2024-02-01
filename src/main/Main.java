package main;

import driver.Driver;
import gui.MainGui;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Driver.getInstance();//드라이버 로드
		new MainGui();//시작페이지 GUI
	}

}
