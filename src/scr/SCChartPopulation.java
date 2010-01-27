/**
 * Sugarscape
 * Copyright 2009-2010 Denis M., Stefan H., Waldemar S.
 * 
 * Author: Denis M., Stefan H., Waldemar S.
 * Website: http://github.com/CallToPower/Sugarscape
 * AG: Lecture "Regelbasierte Modelle" at the University of Osnabrueck (Germany)
 * 
 * The Sugarscape is free Software:
 * You can redistribute it and/or modify it under the Terms of the
 * GNU General Public License
 * as published by the Free Software Foundation, either version 3 of the
 * License,
 * or (at your Option) any later Version.
 * 
 * The Sugarscape Application is distributed WITHOUT ANY WARRANTY;
 * without even the implied Warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE.
 * See the GNU General Public License for more Details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with the Sugarscape Application.
 * If not, see <http://www.gnu.org/licenses/>.
 * 
 * File: scr/SCChartPopulation.java
 */
package scr;

import eawag.chart.Chart;
import eawag.grid.Bug;

/**
 * Chart Diagram for the Population
 * 
 * @author Waldemar S.
 */
public class SCChartPopulation extends Chart {

	/**
	 * The SCGrid
	 */
	public SCGrid scGrid;
	int time = 0; //counts the timesteps
	int count = 0;	//sum of all counted bugs

	/**
	 * Default Constructor
	 */
	public SCChartPopulation() {
		setHTitle("Zeit");
		setVTitle("Anzahl lebender Agenten");
		setComment("Stellt die Anzahl lebender Agenten dar");
	}

	/**
	 * Condition Method
	 */
	public void condition() {
		
		super.condition();
		
		if (scGrid == null)
			return;

		int bugCount = 0;
		int bugMaleCount = 0;
		int bugFemaleCount = 0;
		int average = 0;

		
		for (int x = 0; x < scGrid.xsize; x++)
			for (int y = 0; y < scGrid.ysize; y++) {
				Bug child = (Bug) scGrid.getBug(x, y, 1);
				if (child != null && child instanceof SCBug) {
					bugCount++;
					count++;
					if (((SCBug) child).getSex())
						bugMaleCount++;
					else
						bugFemaleCount++;
			}
		}
		time++;
		average = count/time;

		int time = getTop().getTime();
		lineTo("Agenten", Chart.TYPE_LINE, time, bugCount);
		lineTo("Agenten M", Chart.TYPE_LINE, time, bugMaleCount);
		lineTo("Agenten W", Chart.TYPE_LINE, time, bugFemaleCount);
		//lineTo("Durchschnitt", Chart.TYPE_LINE, time, average);
	}
}
