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
 * File: scr/SCGrid.java
 */
package scr;

import eawag.grid.Bug;
import eawag.grid.Grid;

/**
 * Grid
 * 
 * @author Denis Meyer
 */
public class SCGrid extends Grid {

	private static final SCHelper helper = new SCHelper();

	/************************************************/
	// Private Variables
	/************************************************/

	private boolean init = true;
	
	private int avarageWealth = 0;
	private int _avarageWealth = 0;
	private int bugCount = 0;
	private int _bugCount = 0;
	
	private int deathCount = 0;
	private int deathAge = 0;
	private int d_poor = 0;
	private int d_AgePoor = 0;
	private int d_rich = 0;
	private int d_AgeRich = 0;

	/************************************************/
	// Other Functions
	/************************************************/
	
	public void condition() {
		
		avarageWealth = _avarageWealth;
		bugCount = _bugCount;
		_avarageWealth = _bugCount = 0;
		
		d_poor = 0;
		d_AgePoor = 0;
		d_rich = 0;
		d_AgeRich = 0;
		
		super.condition();
	}

	/**
	 * Action-Function 1. Put SCSugarAgents on every Cell
	 */
	public void action() {

		if (init) {

			Bug b;
			for (int x = 0; x < this.getXSize(); x++) {
				for (int y = 0; y < this.getYSize(); y++) {

					// assume only one level
					b = this.getBug(x, y, 0);
					// amount of sugar
					int r = -1;

					if (b != null) {
						// Bug has been initialized
						if (b instanceof SCSugarBug) {
							// get amount of sugar was set
							r = ((SCSugarBug) b).getCurrentAmountOfSugar();
						}
						b.leave();
					}

					b = newSCSugarBug(r);
					b.moveBug(x, y, 0);
					b.join(this);
					((SCSugarBug) b).updateDepiction();
				}
			}
			init = false;
		}
	}
	
	/**
	 * Adds
	 * @param currentBugWealth
	 */
	public void addAvarageWealth(int currentBugWealth) {
		_avarageWealth += currentBugWealth;
		_bugCount++;
	}
	
	/**
	 * Returns the Average Wealth
	 * @return the Average Wealth
	 */
	public int getAvarageWealth() {
		return (int) (bugCount > 0 ? avarageWealth / bugCount : 0);
	}
	
	/**
	 * Returns the number of died Agents
	 * 
	 * @return number of died Agents
	 */
	public int getDeadAgents() {
		return deathCount;
	}

	/**
	 * Returns the Age of Death
	 * 
	 * @return the Age of Death
	 */
	public int getAgeOfDeath() {
		return deathAge;
	}

	/**
	 * Returns number of poor dead Agents
	 * 
	 * @return d_poor
	 */
	public int getD_Poor() {
		return d_poor;
	}

	/**
	 * Returns Age of death from poor agents
	 * 
	 * @return d_AgePoor
	 */
	public int getD_AgePoor() {
		return d_AgePoor;
	}

	/**
	 * Returns number of rich dead Agents
	 * 
	 * @return d_rich
	 */
	public int getD_Rich() {
		return d_rich;
	}

	/**
	 * Returns Age of death from rich agents
	 * 
	 * @return d_AgeRich
	 */
	public int getD_AgeRich() {
		return d_AgeRich;
	}
	
	public void incDeathAgents() {
		this.deathCount++;
	}

	public void incAgeOfDeath(int deathAge) {
		this.deathAge += deathAge;
	}

	public void incD_Poor() {
		d_poor++;
	}

	public void incD_Rich() {
		d_rich++;
	}

	public void incD_AgePoor(int dAgePoor) {
		d_AgePoor += dAgePoor;
	}

	public void incD_AgeRich(int dAgeRich) {
		d_AgeRich += dAgeRich;
	}

	/************************************************/
	// Internal Helper-Functions
	/************************************************/

	/**
	 * Returns a new SCBug
	 * 
	 * @return SCBug a new SCBug
	 */
	private SCSugarBug newSCSugarBug(int amountSugar) {

		if (amountSugar < 0)
			amountSugar = helper.getRandomIntWithinLimits(0, helper
					.getMaxAmountOfSugarInSugarAgent());

		return new SCSugarBug(amountSugar);
	}
}
