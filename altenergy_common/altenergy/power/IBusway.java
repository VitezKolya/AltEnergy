
package altenergy.power;

public interface IBusway {
	/**
	 * Gets the network currently in use by this busway segment.
	 * Will try to connect to adjacent networks or create a new one
	 * 
	 * @return EnergyBuswayNetwork this cable is using
	 */
	public EnergyBuswayNetwork getNetwork();

	/**
	 * Gets the network currently in use by this busway segment.
	 * 
	 * @param createIfNull
	 *            - If true, the busway will try and connect to an
	 *            adjacent network, merging several if necessary, or creating a
	 *            new one
	 *            if none is available
	 * @return EnergyBuswayNetwork this busway is using
	 */
	public EnergyBuswayNetwork getNetwork(boolean createIfNull);

	/**
	 * Sets this cables segment's network to a new value
	 * 
	 * @param network
	 *            - EnergyBuswayNetwork to set to
	 */
	public void setNetwork(EnergyBuswayNetwork network);

	/**
	 * Refreshes the busway's network
	 */
	public void refreshNetwork();

	/**
	 * Remove a cable from it's network.
	 */
	public void removeFromNetwork();

	/**
	 * Call this if you're worried a cable's network is messed up and you want
	 * it to try and fix itself
	 */
	public void fixNetwork();
}
