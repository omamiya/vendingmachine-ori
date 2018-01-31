package App.AdminAction;

/**
 * Created by orymamia on 31/01/2018.
 */
public class AdminLogout implements AdminAction {
    String description = AdminActionTypes.LOGOUT.getValue() + ". Logout";

    @Override
    public void invoke() {
    }

    @Override
    public void printDescription() {
        System.out.println(description);
    }
}
