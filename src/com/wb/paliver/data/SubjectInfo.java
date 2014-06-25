/******************************************************************************
*
* This class is a high-level data container for each search subjects.
*
* Copyright (C) 2014  Paliver
* 
* This program is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 3 of the License, or
* (at your option) any later version.
*   
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
* 
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software Foundation,
* Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
******************************************************************************/

package com.wb.paliver.data;

import java.math.BigInteger;
import java.util.Random;

public class SubjectInfo {
	
	public long subject_id = -1;
	public String subject = "";
	public String info = "";
	
	public long topic_id = -1;
	
	
	public boolean compareTo(SubjectInfo other) {
		boolean isEqual = true;

		//isEqual &= (this.subject_id == other.subject_id);
		isEqual &= (this.subject == other.subject);
		isEqual &= (this.info == other.info);
		
		isEqual &= (this.topic_id == other.topic_id);
		
		return isEqual;
	}
	
	public void randomData() {
		final Random random = new Random();
		String tester = new BigInteger(64, random).toString();
		
		//this.subject_id = random.nextLong();
		this.subject = tester;
		this.info = tester;
		
		this.topic_id = random.nextLong();
	}
}
