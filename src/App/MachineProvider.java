package App;

/**
 * Created by orymamia on 21/01/2018.
 */
class MachineProvider {
    IMachine machine;
    IMachineAdmin admin;

    IMachine getMachine() {
        return this.machine;
    }

    IMachineAdmin getMachineAdmin() {
        return this.admin;
    }

    MachineProvider() {
        Machine m = new Machine();
        this.machine = m;
        this.admin = m;
    }
}
