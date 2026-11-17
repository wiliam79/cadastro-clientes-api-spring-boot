package br.com.exemplo.cadastro;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Map;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest @AutoConfigureMockMvc class CadastroIntegrationTest {
@Autowired MockMvc mvc;@Autowired ObjectMapper mapper;
@Test void fluxoCompleto() throws Exception {
String p=mvc.perform(post("/profissoes").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(Map.of("nome","Desenvolvedor")))).andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
long profissaoId=mapper.readTree(p).get("id").asLong();
String req=mapper.writeValueAsString(Map.of("nome","Maria Silva","email","maria@example.com","telefone","11999999999","documento","529.982.247-25","profissaoId",profissaoId));
String criado=mvc.perform(post("/clientes").contentType(MediaType.APPLICATION_JSON).content(req)).andExpect(status().isCreated()).andExpect(jsonPath("$.documento").value("52998224725")).andReturn().getResponse().getContentAsString();
long id=mapper.readTree(criado).get("id").asLong();
mvc.perform(post("/clientes").contentType(MediaType.APPLICATION_JSON).content(req)).andExpect(status().isConflict());
mvc.perform(get("/clientes/{id}",id)).andExpect(status().isOk());
mvc.perform(put("/clientes/{id}",id).contentType(MediaType.APPLICATION_JSON).content(req)).andExpect(status().isOk());
mvc.perform(delete("/clientes/{id}",id)).andExpect(status().isNoContent());
mvc.perform(get("/clientes/{id}",id)).andExpect(status().isNotFound());
}
}
