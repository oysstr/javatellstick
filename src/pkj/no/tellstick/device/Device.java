package pkj.no.tellstick.device;

import pkj.no.tellstick.JNA;


public class Device extends TellstickDevice implements pkj.no.tellstick.device.iface.Device{

	public Device(int deviceId) throws SupportedMethodsException {
		super(deviceId);
	}

	@Override
	public void on() throws TellstickException {
		int status = JNA.CLibrary.INSTANCE.tdTurnOn(getId());
		if (status != TELLSTICK_SUCCESS)throw new TellstickException(this, status);
	}

	@Override
	public void off() throws TellstickException {
		int status = JNA.CLibrary.INSTANCE.tdTurnOff(getId());
		if (status != TELLSTICK_SUCCESS)throw new TellstickException(this, status);
	}
	
	/**
	 * Returns true if latest command was turn on signal.
	 * This is a virtual 2-way communication, it does not really know if it's on. But it knows the latest command sent, so we can determine it this way.
	 * @return true if device is on.
	 */
	public boolean isOn(){
		if ((JNA.CLibrary.TELLSTICK_TURNON & this.getStatus()) > 0) return true;
		else return false;
	}

	
	public String getType(){
		return "On/Off device";
	}
}
