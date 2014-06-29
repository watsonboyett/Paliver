/******************************************************************************
*
* This class is a low-level data container for each search subject.
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

import java.sql.Timestamp;
import java.util.Random;

public class SubjectData {

	public long subject_id = -1;

	public Timestamp time = null;
	public double assoc = -1;

	public double love = -1;
	public double hate = -1;
	public double ambiv = -1;

	public double want = -1;
	public double need = -1;
	public double subsist = -1;

	public double good = -1;
	public double evil = -1;
	public double demean = -1;

	private DataStrategy dataStrategy = new DataStrategy_Log();

	
	public void updateAmbiv() {
		ambiv = dataStrategy.execute(love, hate);
	}
	
	public void updateSubsist() {
		subsist = dataStrategy.execute(want, need);
	}
	
	public void updateDemean() {
		demean = dataStrategy.execute(good, evil);
	}

	
	public boolean compareTo(SubjectData other) {
		boolean isEqual = true;

		isEqual &= (this.subject_id == other.subject_id);

		isEqual &= (this.time == other.time);
		isEqual &= (this.assoc == other.assoc);

		isEqual &= (this.love == other.love);
		isEqual &= (this.hate == other.hate);
		isEqual &= (this.ambiv == other.ambiv);

		isEqual &= (this.want == other.want);
		isEqual &= (this.need == other.need);
		isEqual &= (this.subsist == other.subsist);

		isEqual &= (this.good == other.good);
		isEqual &= (this.evil == other.evil);
		isEqual &= (this.demean == other.demean);

		return isEqual;
	}

	public void randomData() {
		final Random random = new Random();

		this.subject_id = random.nextLong();

		this.time = new Timestamp(System.currentTimeMillis());
		this.assoc = Math.random();

		this.love = Math.random();
		this.hate = Math.random();
		this.ambiv = Math.random();

		this.want = Math.random();
		this.need = Math.random();
		this.subsist = Math.random();

		this.good = Math.random();
		this.evil = Math.random();
		this.demean = Math.random();
	}

}
