package server.spring.api.common.domain.vo;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class AbstractData {
	//@Schema(description = "식별자")
	private Long id;

	//@Schema(description = "생성일시")
	private LocalDateTime createdAt;

	//@Schema(description = "생성자")
	private String createdBy;

	//@Schema(description = "수정일시")
	private LocalDateTime updatedAt;

	//@Schema(description = "수정자")
	private String updatedBy;

	//@Schema(description = "사용중지 여부")
	private Boolean disabled;
}
