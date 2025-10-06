package utils.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonGenerator {

    private final Map<String, Object> map = new HashMap<>();

    public void add(String referencia, Object valor){
        if(map.get(referencia) != null){
            if(map.get(referencia) instanceof List){
                ((List<Object>) map.get(referencia)).add(valor);
            } else {
                List<Object> listaObjetos = new ArrayList<>();
                listaObjetos.add(map.get(referencia));
                listaObjetos.add(valor);
                map.put(referencia, listaObjetos);
            }
        } else {
            map.put(referencia, valor);
        }
    }

    public String montarJson(){

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(map);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}