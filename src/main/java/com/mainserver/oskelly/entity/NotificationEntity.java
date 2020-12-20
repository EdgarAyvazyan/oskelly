package com.mainserver.oskelly.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notification")
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "comment_id")
    private long comment_id;
    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime time;
    @Column(name = "delivered")
    private boolean delivered;
}
