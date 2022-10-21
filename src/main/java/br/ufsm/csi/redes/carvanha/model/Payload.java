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
        PING, PONG
    }

    public Type type;
    public Long time;
    public String name;

    @SneakyThrows
    public String toJSON() {
        return new ObjectMapper().writeValueAsString(this);
    }

    @SneakyThrows
    public static Payload fromJSON(String json) {
        return new ObjectMapper().readValue(json, Payload.class);
    }
}
