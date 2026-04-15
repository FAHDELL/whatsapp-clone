package net.fahd.whatsappclone.message;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageResponse {

    private Long  id;
    private String content;
    private MessageType type;
    private String senderId;
    private String receiverId;
    private LocalDateTime createdAt;
    //private MessageType messageType;
    private byte [] media;

}
