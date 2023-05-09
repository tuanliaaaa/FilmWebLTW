package film.api.DTO;

import lombok.Data;

@Data
public class ExceptionResponse {
    private String error;
    private Integer status;
    private String messages;


    public ExceptionResponse(String messages, Integer status) {
        this.messages=messages;

        this.status = status;

    }
}
