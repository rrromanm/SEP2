package sep.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sep.model.Model;

import java.rmi.RemoteException;

public class LoginViewModel
{
    private final Model model;
    private final StringProperty username;
    private final StringProperty password;

    public LoginViewModel(Model model)
    {
        this.model = model;
        this.username = new SimpleStringProperty("");
        this.password = new SimpleStringProperty("");
    }

    public boolean login(){
        try {
            if(model.login(username.get(), password.get())){
                reset();
                return true;
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void bindUsername(StringProperty property){
        this.username.bindBidirectional(property);
    }
    public void bindPassword(StringProperty property){
        this.password.bindBidirectional(property);
    }
    public void reset()
    {
        this.username.set("");
        this.password.set("");
    }
}
