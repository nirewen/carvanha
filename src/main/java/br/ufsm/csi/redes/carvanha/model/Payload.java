package br.ufsm.csi.redes.carvanha.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payload {
    public enum Type {
        PING,
        PONG,
        MESSAGE
    }

    @Builder.Default
    public Type type = Type.MESSAGE;
    @Builder.Default
    public Long time = System.nanoTime();
    public String name;
    public String content;

    @SneakyThrows
    public String toJSON() {
        return new ObjectMapper().writeValueAsString(this);
    }

    @SneakyThrows
    public static Payload fromJSON(String json) {
        return new ObjectMapper().readValue(json, Payload.class);
    }
}
