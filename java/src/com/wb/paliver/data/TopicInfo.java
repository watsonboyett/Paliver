/******************************************************************************
*
* This class is a high-level data container for different search topics.
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

public class TopicInfo {
	
	public long topic_id = -1;
	public String topic = "";
	public String info = "";
	
	
	public boolean compareTo(TopicInfo other) {
		boolean isEqual = true;
		
		//isEqual &= (this.topic_id == other.topic_id);
		isEqual &= (this.topic == other.topic);
		isEqual &= (this.info == other.info);
		
		return isEqual;
	}
	
	public void randomData() {
		final Random random = new Random();
		String tester = new BigInteger(64, random).toString();
		
		//this.topic_id = random.nextLong();
		this.topic = tester;
		this.info = tester;
	}
}
