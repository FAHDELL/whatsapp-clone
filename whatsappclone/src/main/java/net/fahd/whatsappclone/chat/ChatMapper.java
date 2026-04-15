package net.fahd.whatsappclone.chat;

import org.springframework.stereotype.Service;

@Service
public class ChatMapper {
    public ChatResponse toChatResponse(Chat c, String senderId) {
        return ChatResponse.builder()
                .id(c.getId())
                .name(c.getChatName(senderId))
                .unreadCount(c.getUnreadMessages(senderId))
                .lastMessage(c.getLatsMessage(senderId)).
                lastMessageTime(c.getLastMessageTime())
                .isRecipientOnline(c.getRecipient().isUserOnline())
                .senderId(c.getSender().getId())
                .receiverId(c.getRecipient().getId())
                .lastMessageTime(c.getLastMessageTime())
                .build();
    }
}
