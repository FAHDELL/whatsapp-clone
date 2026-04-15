package net.fahd.whatsappclone.message;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.fahd.whatsappclone.chat.Chat;
import net.fahd.whatsappclone.commun.BaseAuditingEntity;
import net.fahd.whatsappclone.user.UserConstant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "messages")
@NamedQuery(name = MessageConstant.FIND_MESSAGE_BY_CHAT_ID,
        query = "SELECT m FROM Message m WHERE m.chat.id = :chatId ORDER BY m.createdDate")
@NamedQuery(name = MessageConstant.SET_MESSAGES_TO_SEEN_BY_CHAT,
        query = "UPDATE Message SET state = :newState WHERE chat.id = :chatId ")
public class Message extends BaseAuditingEntity {
    @Id
    @SequenceGenerator(name = "msg_seq", sequenceName = "msg_seq", allocationSize = 1 )
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    private MessageState state;
    @Enumerated(EnumType.STRING)
    private MessageType type;
    @ManyToOne()
    @JoinColumn(name = "chat_id")
    private Chat chat;
    @Column(name = "send_id", nullable = false)
    private String sendId;
    @Column(name = "receiver_id", nullable = false)
    private String receiverId;

    private String mediaFilePath;
}
