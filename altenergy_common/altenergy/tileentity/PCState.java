
package altenergy.tileentity;

public enum PCState {
	Idle, Generate, Overload, Vent;

	static PCState[] VALUES = values();

	public static PCState valueOf(int i) {

		try {
			return VALUES[i];
		} catch (IndexOutOfBoundsException e) {
			return Idle;
		}
	}
}
