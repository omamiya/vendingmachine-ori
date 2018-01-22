package App;

public class Main {
    public static void main(String[] args) {

        CliOperator cli = new CliOperator(new MachineProvider());
        cli.runMachine();
    }
}
