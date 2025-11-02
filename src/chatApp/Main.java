package chatApp;

import chatApp.controller.ChatController;
import chatApp.view.ChatWindow;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

public class Main {
    public static void main(String[] args) {
        FlatMacLightLaf.setup();
        
        ChatController chatController = new ChatController();
        chatController.addChatWindow(new ChatWindow(chatController,"User "+(chatController.windowCount()+1)));
    }
}
