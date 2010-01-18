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
 * File: scr/SCChartLifespan.java
 */
package scr;

import eawag.chart.Chart;
import eawag.grid.Bug;

/**
 * Chart Diagram for average Age of Bugs
 * 
 * @author Stefan H., Waldemar S.
 */
public class SCChartLifespan extends Chart {

	/**
	 * Grid
	 */
	public SCGrid grid;

	/**
	 * Chart
	 */
	public SCChartLifespan() {
		setHTitle("Zeit");
		setVTitle("Lebenserwartung");
		setComment("Zeigt die durchschnittliche Lebenserwartung"
				+ " der Agenten über die Zeit an");
	}

	/**
	 * Condition
	 */
	public void condition() {

		super.condition();
		
		boolean rdy = false;
		int lifespan = 0;
//		int poor = 0;
//		int rich = 0;
		for (int x = 0; x < grid.xsize && !rdy; x++)
			for (int y = 0; y < grid.ysize && !rdy; y++) {
				Bug bug = grid.getBug(x, y, 1);
				if (bug != null && bug instanceof SCBug) {
					lifespan = ((SCBug) bug).getDeadAgents() == 0 ? 0
							: ((SCBug) bug).getAgeOfDeath()
									/ ((SCBug) bug).getDeadAgents();
//					poor = ((SCBug) bug).getD_Poor() == 0 ? 0
//							: ((SCBug) bug).getD_AgePoor()
//							/ ((SCBug) bug).getD_Poor();
//					rich = ((SCBug) bug).getD_Rich() == 0 ? 0
//							: ((SCBug) bug).getD_AgeRich()
//							/ ((SCBug) bug).getD_Rich();
					rdy = true;
					}
				}
			

		lineTo("Lebenserwartung", Chart.TYPE_LINE, grid.getTop().getTime(),
			lifespan);
//		lineTo("Arm", Chart.TYPE_LINE, grid.getTop().getTime(),
//				poor);
//		lineTo("Reich", Chart.TYPE_LINE, grid.getTop().getTime(),
//				rich);
	}

}
