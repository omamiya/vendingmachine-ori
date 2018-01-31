package App.UserAction;

/**
 * Created by orymamia on 31/01/2018.
 */
public class UserLogout implements UserAction {
    String description = UserActionTypes.LOGOUT.getValue() + ". Logout";

    @Override
    public void invoke() {}

    @Override
    public void printDescription() {
        System.out.println(description);
    }
}
