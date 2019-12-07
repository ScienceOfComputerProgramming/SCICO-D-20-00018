package com.gamaza.rest4cep.mysql.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * EPL Event Patterns Database Model
 */
@Entity @Table(name = "esper_epl_event_patterns")
@Getter @Setter @EqualsAndHashCode
public class EplEventPattern {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "is_deployed", nullable = false)
    private boolean isDeployed;

    @Column(name = "is_in_esper", nullable = false)
    private boolean isInEsper;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "eplEventPatterns")
    private List<EventType> eventTypes;

}
