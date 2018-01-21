package org.telerik.java.tasks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class UnitsOfWorkSolution {
	public void Solve() throws Exception {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

		String command = "";
		UnitsOfWork units = new UnitsOfWork();
		
		while (!command.equals("end")) {
			command = read.readLine();
			String result = ParseCommand(command, units);
			System.out.println(result);
		}

	}
	
	public static String ParseCommand(String command, UnitsOfWork units) {
		String[] parameters = command.trim().split(" ");
		
		if (parameters[0].equals("add")) {
			return (units.add(parameters[1], parameters[2], Short.parseShort(parameters[3])));
		}
		
		if (parameters[0].equals("remove")) {
			return (units.remove(parameters[1]));
		}
		
		if (parameters[0].equals("find")) {
			return (units.find(parameters[1]));
		}
		
		if (parameters[0].equals("power")) {
			return (units.power(Integer.parseInt(parameters[1])));
		}
		
		return "";
	}
	
	public static class UnitsOfWork { 
		private ArrayList<Unit> units;
		
		public UnitsOfWork() {
			this.units = new ArrayList<Unit>();
		}
		
		public String add(String unitName, String unitType, short unitAttack) {
			if (this.checkIfExists(unitName).contains(unitName)) {
				return "FAIL: " + unitName + " already exists!";
			}
			
			this.units.add(new Unit(unitName, unitType, unitAttack));
			return "SUCCESS: " + unitName + " added!";
		}
		
		public String remove(String unitName) {
			for (int i = 0; i < this.units.size(); i++) {
				if (units.get(i).getUnitName().equals(unitName)) {
					this.units.remove(i);
					return "SUCCESS: " + unitName + " removed!";
				}
			}
			
			return "FAIL: " + unitName + " cound not be found!";
		}
		
		public String checkIfExists(String unitName) {			
			for (int i = 0; i < this.units.size(); i++) {
				if (units.get(i).getUnitName().equals(unitName)) {
					return "FAIL: " + this.units.get(i).getUnitName() + " already exists!";
				}
			}
			
			return "RESULT: ";
		}
		
		public String outputUnit(int index) {
			StringBuilder sb = new StringBuilder();
			
			if (index >= this.units.size() || index < 0) { 
				return "";
			}
			
			sb.append(this.units.get(index).getUnitName() + "[" + this.units.get(index).getUnitType() + "](" + this.units.get(index).getUnitAttack() + ")");
	
			return sb.toString();
		}
		
		
		public String find(String unitType) {
			StringBuilder sb = new StringBuilder();
			sb.append("RESULT: ");
			
			List<Unit> temp = this.units;			
			Collections.sort(temp);
			Collections.reverse(temp);
			
			if (temp.size() > 10) { 
				for (int i = 0; i < 10; i++) {
					if (temp.get(i).getUnitType().equals(unitType)) {
						sb.append(this.outputUnit(i) + ", ");
					}
				}				
			}
			
			else {
				for (int i = 0; i < temp.size(); i++) {
					if (temp.get(i).getUnitType().equals(unitType)) {
						sb.append(this.outputUnit(i) + ", ");
					}
				}				
			}
			
			if (sb.toString().equals("RESULT: ")) {
				return sb.toString();
			}
			
			return sb.replace(sb.length() - 2, sb.length(), "").toString();
		}
		
		public String power(int count) {
			StringBuilder sb = new StringBuilder();
			sb.append("RESULT: ");
			
			ArrayList<Unit> temp = this.units;
			
			Collections.sort(temp);
			Collections.reverse(temp);
			
			for (int i = 0; i <= (count - 1); i++) {
				sb.append(this.outputUnit(i) + ", ");
			}
			
			
			if (sb.toString().equals("RESULT: ")) {
				return sb.toString();
			}
			
			return sb.replace(sb.length() - 2, sb.length(), "").toString();
			
		}
	}
	
	public static class Unit implements Comparator<Unit>, Comparable<Unit>{
		private String unitName;
		private String unitType;
		private short unitAttack;
		
		public Unit() {
			this.unitName = "";
			this.unitType = "";
			this.unitAttack = 0;
		}
		
		public Unit(String unitName, String unitType, short unitAttack) {
			this.unitName = unitName;
			this.unitType = unitType;
			this.unitAttack = unitAttack;
		}
		
		public String getUnitName() {
			return this.unitName;
		}
		
		public String getUnitType() {
			return this.unitType;
		}
		
		public short getUnitAttack() {
			return this.unitAttack;
		}

		@Override
		public int compare(Unit o1, Unit o2) {
			if (o1.getUnitAttack() > o2.getUnitAttack()) {
				return 1;
			}
			
			else if (o1.getUnitAttack() < o2.getUnitAttack()) {
				return -1;
			}
			
			else {
				if (o1.getUnitName().compareTo(o2.getUnitName()) > 0) {
					return 1;
				}
				
				else if (o1.getUnitName().compareTo(o2.getUnitName()) < 0) {
					return -1;
				}
				
				else return 0;
			}
		}

		@Override
		public int compareTo(Unit o) {
			if (this.getUnitAttack() > o.getUnitAttack()) {
				return 1;
			}
			
			else if (this.getUnitAttack() < o.getUnitAttack()) {
				return -1;
			}
			
			else {
				if (this.getUnitName().compareTo(o.getUnitName()) > 0) {
					return 1;
				}
				
				else if (this.getUnitName().compareTo(o.getUnitName()) < 0) {
					return -1;
				}
				
				else return 0;
			}
		}
	}
}
