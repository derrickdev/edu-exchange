package org.project.backapi.dto.modelsDto;

import lombok.Getter;
import lombok.Setter;
import org.project.backapi.enums.VoteStatus;

@Getter
@Setter
public class VoteDto {
    private Long id;
    private VoteStatus status;
}
