package App;

/**
 * Created by orymamia on 21/01/2018.
 */
public class MachineProvider {
    IMachine machine;
    IMachineAdmin admin;

    public IMachine getMachine() {
        return this.machine;
    }

    public IMachineAdmin getMachineAdmin() {
        return this.admin;
    }

    MachineProvider() {
        Machine m = new Machine();
        this.machine = m;
        this.admin = m;
    }
}