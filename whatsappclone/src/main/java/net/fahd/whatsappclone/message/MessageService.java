package net.fahd.whatsappclone.message;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.fahd.whatsappclone.chat.Chat;
import net.fahd.whatsappclone.chat.ChatRepository;
import net.fahd.whatsappclone.file.FileService;
import net.fahd.whatsappclone.file.FileUtils;
import net.fahd.whatsappclone.notification.Notification;
import net.fahd.whatsappclone.notification.NotificationService;
import net.fahd.whatsappclone.notification.NotificationType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;
    private final MessageMapper  mapper;
    private final FileService fileService;
    private final NotificationService notificationService;

    public void saveMessage(MessageRequest messageRequest){

        Chat chat = chatRepository.findById(messageRequest.getChatId())
                .orElseThrow(() -> new EntityNotFoundException("Chat with id " + messageRequest.getChatId() + " not found"));
        Message message = new Message();
        message.setChat(chat);
        message.setContent(messageRequest.getContent());
        message.setSendId(messageRequest.getSenderId());
        message.setReceiverId(messageRequest.getReceiverId());
        message.setType(messageRequest.getType());
        message.setState(MessageState.SENT);

        messageRepository.save(message);

        //todo notification

        Notification  notification = Notification.builder()
                .chatId(chat.getId())
                .messageType(messageRequest.getType())
                .content(messageRequest.getContent())
                .senderId(messageRequest.getSenderId())
                .receiverId(messageRequest.getReceiverId())
                .type(NotificationType.MESSAGE)
                .chatName(chat.getTargetChatName(messageRequest.getSenderId()))
                .build();

        notificationService.sendNotification(messageRequest.getReceiverId(), notification);



    }

    public List<MessageResponse> findChatMessage(String chatId){
        return messageRepository.findMessageByChatId(chatId)
                .stream()
                .map(mapper::toMessageResponse)
                .toList();

    }

    @Transactional
    public  void setMessagesToSeen(String chatId, Authentication authentication){
         Chat chat = chatRepository.findById(chatId).orElseThrow(() -> new EntityNotFoundException("Chat with id " + chatId + " not found"));
         final String recipientId = getRecipientId(chat , authentication);
         messageRepository.setMessagesToSeenByChatId(chatId, MessageState.SEEN);

         //todo notification

        Notification  notification = Notification.builder()
                .chatId(chat.getId())
                .senderId(getSenderId(chat , authentication))
                .receiverId(recipientId)
                .type(NotificationType.SEEN)
                .build();

        notificationService.sendNotification(recipientId, notification);

    }

    public void uploadMediaMessage(String chatId, MultipartFile file, Authentication authentication){
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new EntityNotFoundException("Chat with id " + chatId + " not found"));

        final String senderId = getSenderId(chat, authentication);
        final String receiverId = getRecipientId(chat, authentication);

       final String filepath = fileService.saveFile(file, senderId );

        Message message = new Message();
        message.setChat(chat);
        message.setSendId(senderId);
        message.setReceiverId(receiverId);
        message.setType(MessageType.IMAGE);
        message.setState(MessageState.SENT);
        message.setMediaFilePath(filepath);
        messageRepository.save(message);

        //todo notification

        Notification  notification = Notification.builder()
                .chatId(chat.getId())
                .messageType(MessageType.IMAGE)
                .senderId(senderId)
                .receiverId(receiverId)
                .type(NotificationType.IMAGE)
                .chatName(chat.getChatName(message.getSendId()))
                .media(FileUtils.readFileFromLocation(filepath))
                .build();

        notificationService.sendNotification(message.getReceiverId(), notification);



    }

    private String getSenderId(Chat chat, Authentication authentication) {
        if(chat.getSender().getId().equals(authentication.getName())){
            return chat.getSender().getId();
        }
            return chat.getRecipient().getId();
    }

    private String getRecipientId(Chat chat, Authentication authentication) {
        if(chat.getSender().getId().equals(authentication.getName())){
            return chat.getRecipient().getId();
        }
        return chat.getSender().getId();
    }

}
