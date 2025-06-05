package discord.ws_project_discord.metier;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "message")
public class Message implements Serializable {
    @Id
    @Column(name = "id_msg", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "content", nullable = false, length = Integer.MAX_VALUE)
    private String content;

    @NotNull
    @Column(name = "send_date", nullable = false)
    private LocalDate sendDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_channel")
    private Channel idChannel;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_sender", nullable = false)
    private User sender;

    //reactions
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "msg", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<ReactTo> idReactTo;

    //responds to
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "response_to")
    private Message respondsTo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "respondsTo", cascade = CascadeType.ALL)
    private List<Message> responses;

    @Column(name = "id_receiver")
    private Integer idReceiver;

}