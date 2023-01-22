package com.todolist.todolist.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class RevisableEntity {
    @Column(name = "created_by", updatable = false)
    @CreatedBy
    private String createdBy;
    @Column(name = "created_date", updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;
    @Column (name = "last_modified_by")
    @LastModifiedBy
    private String lastModifiedBy;
    @Column (name = "last_modified_date")
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
