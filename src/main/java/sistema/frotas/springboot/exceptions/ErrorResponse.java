package sistema.frotas.springboot.exceptions;

public record ErrorResponse(
        Integer status,
        String message

) {
}
