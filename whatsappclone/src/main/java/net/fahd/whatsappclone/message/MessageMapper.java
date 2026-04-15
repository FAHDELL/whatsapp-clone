package net.fahd.whatsappclone.message;

import net.fahd.whatsappclone.file.FileUtils;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {
    public MessageResponse toMessageResponse(Message message) {
            return MessageResponse.builder()
                    .id(message.getId())
                    .content(message.getContent())
                    .type(message.getType())
                    .senderId(message.getSendId())
                    .receiverId(message.getReceiverId())
                    .createdAt(message.getCreatedDate())
                    //.media()
                    .media(FileUtils.readFileFromLocation(message.getMediaFilePath()))
                    .build();
    }
}
