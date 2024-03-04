package server.spring.api.initial_api.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;
import server.spring.api.common.domain.AuditEntity;
import server.spring.api.initial_api.enums.InitialType;

@ToString(
    callSuper = true,
    exclude = {})
@Getter
@Setter
@RequiredArgsConstructor
@Audited
@AuditOverride(forClass = AuditEntity.class)
@Entity
public class InitialResource extends AuditEntity<String> {

  @Column(columnDefinition = "varchar(255) comment '내용'")
  private String content;

  @Column(columnDefinition = "datetime(6)")
  private LocalDateTime date;

  @Enumerated(EnumType.STRING)
  @Column(columnDefinition = "ENUM('TEST')", nullable = false)
  private InitialType type;

  @Column(columnDefinition = "bit not null")
  private boolean flag;

//  @Column(columnDefinition = "SMALLINT default 1 comment '정렬 순서'")
//  private Integer orders;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    InitialResource that = (InitialResource) o;
    return id != null && Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
