package com.pqy.test;

import org.junit.Test;

import com.pqy.management.*;
import com.pqy.dao.*;

public class UnitTest {
	@Test
	public void AddTest() {
		new AddStudent().add();
	}
	
	@Test
	public void ChangeTest() {
		new ChangePassword().ChangeUI("m200", "123456");
	}
	
	@Test
	public void QueryTest() {
		new QueryStudent().QueryStudentUI();
	}
	
}
