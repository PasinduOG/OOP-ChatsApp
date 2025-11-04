package chatApp.controller;

import chatApp.model.LinkedList;
import chatApp.view.ChatWindow;

public class ChatController {

    private final LinkedList chatWindowList = new LinkedList();

    public void addChatWindow(ChatWindow chatWindow) {
        chatWindowList.add(chatWindow);
    }

    public void removeChatWindow(ChatWindow chatWindow) {
        chatWindowList.remove(chatWindow);
    }

    public void sendMessage(String message, ChatWindow user) {
        ChatWindow[] chatWindowList = this.chatWindowList.toArray();
        for (ChatWindow chatWindow : chatWindowList) {
            if(chatWindow==user){
                chatWindow.setMessage("Me : " + message);
            }else{
                chatWindow.setMessage(user.getUserName()+ " : " + message);
            }
        }
    }

    public int windowCount() {
        return chatWindowList.size();
    }
}
