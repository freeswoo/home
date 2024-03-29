package com.biz.iolist.service;

import com.biz.iolist.domain.IolistVO;

public class IolistServiceImpV3 extends IolistServiceImpV2 {

	public void list() {
		
		this.list_header();
		
		int intITotalSum = 0;
		int intOTotalSum = 0;
		
		for(IolistVO vo : iolist) {
			System.out.print(vo.getStrDate()+"\t");
			System.out.print(vo.getStrPName()+"\t");
			
			/*
			 * 만약 vo 설계단계에서 io 구분자 저장 변수를
			 * int 형으로 만들었다면
			 * 파일에서 데이터를 읽어 들이는 부분에서
			 * Integer.valueOf() method로 한번만 변환을 시키면
			 * 이후 다른 부분에서는 부담없이 사용할수 있었을 것이다
			 * 
			 * 하지만, 지금은 io 구분자를 쉽게 쓰기위해
			 * int 형으로 변환시키는 코드가 곳곳에 산재하게 되었다
			 * 이런 경우 설계 미스로 인한 비용증가 라고 한다.
			 */
			int intIO = Integer.valueOf(vo.getStrIO());
			// if(vo.getStrIO().equals("1")) {  }
			int intIPrice = 0;
			int intOPrice = 0;
			String strIotitle = "매출";
			
			if(intIO == 1) {
				intIPrice = vo.getIntPrice();
				strIotitle = "매입";
				intITotalSum += intIPrice;
			} else {
				intOPrice = vo.getIntPrice();
				intOTotalSum += intOPrice;
			}
			System.out.print(strIotitle + "\t");
			System.out.print(vo.getIntPrice() + "\t");
			System.out.print(vo.getIntQty() + "\t");
			
			int intITotal = intIPrice * vo.getIntPrice();
			int intOTotal = intOPrice * vo.getIntPrice();
			System.out.printf("%8d\t",intITotal);
			System.out.printf("%8d\n",intOTotal);
			intITotalSum += intITotal;
			intOTotalSum += intOTotal;
			
		} // for end
		System.out.println("===============================================================================");
		System.out.printf("합계\t\t\t\t\t\t\t%8d\t%8d\n",intITotalSum,intOTotalSum);
		System.out.println("===============================================================================");
		
		
	}
	
	protected void list_header() {
		System.out.println("===============================================================================");
		System.out.println("거래일자\t상품명\t구분\t단가\t수량\t매입금액\t매출금액");
		System.out.println("-------------------------------------------------------------------------------");
	}

}
