package com.gamaza.rest4cep.mysql.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

import static com.gamaza.rest4cep.config.constant.EntityConstants.AUDITABLE_FIELD_CREATED_DATE;
import static com.gamaza.rest4cep.config.constant.EntityConstants.AUDITABLE_FIELD_LAST_MODIFIED_DATE;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
public class Auditable {

    @CreatedDate
    @Column(name = AUDITABLE_FIELD_CREATED_DATE)
    private Timestamp createdDate;

    @LastModifiedDate
    @Column(name = AUDITABLE_FIELD_LAST_MODIFIED_DATE)
    private Timestamp lastModifiedDate;

}
