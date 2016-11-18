package org.zerock.domain;

import java.io.File;

import org.springframework.stereotype.Repository;

@Repository
public class ProductVO {
	private int pno;

	private String pname, pmaker;
	private String pimg;

	public int getPno() {
		return pno;
	}

	public void setPno(int pno) {
		this.pno = pno;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPmaker() {
		return pmaker;
	}

	public void setPmaker(String pmaker) {
		this.pmaker = pmaker;
	}

	public String getPimg() {
		return pimg;
	}

	public void setPimg(String pimg) {
		this.pimg = pimg;
	}

	@Override
	public String toString() {
		return "ProductVO [pname=" + pname + ", pmaker=" + pmaker + ", pimg=" + pimg + "]";
	}

}
