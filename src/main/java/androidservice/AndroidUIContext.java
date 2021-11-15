package androidservice;

import io.appium.java_client.android.AndroidDriver;

public abstract class AndroidUIContext {
    protected AndroidDriver driver;
    public static final String KEY_EVENT="adb shell input keyevent";
    public static final int SELECT_KEY_EVENT=66;

    public AndroidUIContext(AndroidDriver driver){
        this.driver=driver;
    }

    public String commandBuilder(int keyEvent){
        return String.format(KEY_EVENT,keyEvent);
    }

    public void executeCommand(String[] command){
        System.out.println("Execute command");
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        try {
            processBuilder.start();
        }
        catch (Exception ex){
            new RuntimeException("Unable to execute the command"+ex.getMessage());
        }
    }

    public void executeCommand(String command){
        executeCommand(command.split(" "));
    }

    public void select(){
        executeCommand(commandBuilder(SELECT_KEY_EVENT));
    }
}
