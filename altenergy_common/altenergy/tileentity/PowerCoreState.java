
package altenergy.tileentity;

public enum PowerCoreState {
	Idle, Generate, Overload, Vent;

	static PowerCoreState[] VALUES = values();

	public static PowerCoreState valueOf(int i) {

		try {
			return VALUES[i];
		} catch (IndexOutOfBoundsException e) {
			return Idle;
		}
	}
}
