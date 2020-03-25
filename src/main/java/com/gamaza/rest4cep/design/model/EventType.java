package com.gamaza.rest4cep.design.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static com.gamaza.rest4cep.config.constant.EntityConstants.*;

/**
 * Event Type Database Model
 */
@Entity
@Table(name = TABLE_EVENT_TYPE)
@Getter
@Setter
public class EventType extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "channel_id", nullable = false, unique = true)
    private Integer channel;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "is_enabled", nullable = false)
    private boolean enabled;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = TABLE_EVENT_TYPE_EVENT_PATTERN,
            joinColumns = @JoinColumn(name = JOIN_COLUMN_EVENT_TYPE_ID),
            inverseJoinColumns = @JoinColumn(name = JOIN_COLUMN_EVENT_PATTERN_ID)
    )
    private List<EventPattern> eventPatterns = new ArrayList<>();

}
