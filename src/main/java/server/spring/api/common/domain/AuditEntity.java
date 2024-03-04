package server.spring.api.common.domain;


import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Entity 들의 createdDate, modifiedDate 자동 관리 역할
 * **/
@Getter
//@AuditOverride(forClass = BaseEntity.class)
// Entity 클래스들이 AuditEntity 클래스를 상속할 경우 아래 필드들도 칼럼으로 인식하도록 한다.
@MappedSuperclass
//AuditEntity 클래스에 Auditing기능 포함
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditEntity<P> extends AbstractEntity {

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @PrePersist
    protected void onCreatedAt() {
        this.modifiedDate = this.createdDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdatedAt() {
        this.modifiedDate = LocalDateTime.now();
    }
}

