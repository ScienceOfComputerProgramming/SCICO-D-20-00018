package com.gamaza.rest4cep.mysql.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static com.gamaza.rest4cep.config.constant.EntityConstants.RELATION_FIELD_EVENT_TYPES;
import static com.gamaza.rest4cep.config.constant.EntityConstants.TABLE_EVENT_PATTERN;

/**
 * Event Pattern Database Model
 */
@Entity
@Table(name = TABLE_EVENT_PATTERN)
@Getter @Setter
public class EventPattern extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "is_ready_to_deploy", nullable = false)
    private boolean readyToDeploy;

    @Column(name = "is_deployed", nullable = false)
    private boolean deployed;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = RELATION_FIELD_EVENT_TYPES)
    private List<EventType> eventTypes = new ArrayList<>();

}
